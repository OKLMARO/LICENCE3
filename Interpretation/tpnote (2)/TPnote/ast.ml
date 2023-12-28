(* Syntaxe abstraite *)
type program =
| Program of rule * string

and rule =
| RSimple of pattern * strexpr
| RSeq of rule * rule

and pattern =
| LChar of char

and strexpr =
| RStr of string


(* Conversion en chaîne de caractères pour affichage *)
let rec string_of_program p = match p with
| Program (r, s) -> Printf.sprintf "process { %s } on \"%s\"" (string_of_rule r) s

and string_of_rule r = match r with
| RSimple (l, r) -> Printf.sprintf "%s => %s" (string_of_pattern l) (string_of_strexpr r)
| RSeq (r1, r2) -> Printf.sprintf "%s ; %s" (string_of_rule r1) (string_of_rule r2)

and string_of_pattern l = match l with
| LChar c -> Printf.sprintf "'%c'" c

and string_of_strexpr r = match r with
| RStr s -> Printf.sprintf "\"%s\"" s


(* Evaluation *)
let rec string_fold f s a =
  let r = ref a in
  for i = String.length s - 1 downto 0 do
    r := f (s.[i]) (!r)
  done;
  !r

let rec eval_program gamma p = match p with
| Program (r, s) -> string_fold (fun c acc ->
    match eval_rule gamma r c with
    | None -> Printf.sprintf "%c%s" c acc
    | Some s -> s ^ acc
  ) s ""

and eval_rule gamma r c = match r with
| RSimple (l, r) -> 
  if eval_pattern l c then Some (eval_strexpr gamma r)
  else None
| RSeq (r1, r2) -> (
  match eval_rule gamma r1 c with
  | None -> eval_rule gamma r2 c
  | Some s -> Some s
)


and eval_pattern l c = match l with
| LChar c' -> c = c' 

and eval_strexpr gamma e = match e with
| RStr s -> s