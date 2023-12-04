(* Syntaxe abstraite *)
type value =
  | VInt of int
  | VFloat of float
  | VBool of bool
  | VStr of string

and expr =
  | ECst of value
  | EAdd of expr * expr
  | EMul of expr * expr
  | ENeg of expr
  | EAnd of expr * expr
  | EOr of expr * expr
  | ENot of expr
  | EEq of expr * expr
  | ELt of expr * expr
  | EIf of expr * expr * expr
  | 

(* Conversion en chaîne de caractères pour affichage *)
let rec string_of_value v = match v with
  | VInt i -> string_of_int i
  | VFloat f -> string_of_float f
  | VBool b -> string_of_bool b
  | VStr s -> Printf.sprintf "\"%s\"" s (* FIXME: transformer les caractères spéciaux '\n', '\r' et '\t' *)

and string_of_expr e = match e with
  | ECst v -> string_of_value v
  | EAdd (e1, e2) -> Printf.sprintf "(%s) + (%s)" (string_of_expr e1) (string_of_expr e2)
  | EMul (e1, e2) -> Printf.sprintf "(%s) * (%s)" (string_of_expr e1) (string_of_expr e2)
  | EAnd (e1, e2) -> Printf.sprintf "(%s) && (%s)" (string_of_expr e1) (string_of_expr e2)
  | EOr (e1, e2) -> Printf.sprintf "(%s) || (%s)" (string_of_expr e1) (string_of_expr e2)
  | ENot e -> Printf.sprintf "not (%s)" (string_of_expr e)
  | ENeg e -> Printf.sprintf "- (%s)" (string_of_expr e)
  | EEq (e1, e2) -> Printf.sprintf "(%s) = (%s)" (string_of_expr e1) (string_of_expr e2)  
  | ELt (e1, e2) -> Printf.sprintf "(%s) < (%s)" (string_of_expr e1) (string_of_expr e2)
  | EIf (e1, e2, e3) -> Printf.sprintf "if (%s) then (%s) else (%s)" (string_of_expr e1) (string_of_expr e2) (string_of_expr e3)

(* Arithmétique du type value *)
let add_value v1 v2 = match (v1, v2) with
  | VInt i1, VInt i2 -> VInt (i1 + i2)
  | VInt i1, VFloat f2 -> VFloat (float_of_int i1 +. f2)
  | VFloat f1, VInt i2 -> VFloat (f1 +. float_of_int i2)
  | VFloat f1, VFloat f2 -> VFloat (f1 +. f2)
  | VStr s1, VStr s2 -> VStr (s1 ^ s2)
  | VStr s1, v2 -> VStr (s1 ^ (string_of_value v2))
  | v1, VStr s2 -> VStr ((string_of_value v1) ^ s2)
  | _, _ -> failwith (Printf.sprintf "Type mismatch: cannot add '%s' and '%s'" (string_of_value v1) (string_of_value v2))

(* Evaluation des expressions *)

let neg_value v = match v with
  | VInt i -> VInt (-i)
  | VFloat f -> VFloat (-.f)
  | _ -> failwith "neg_value"

let mul_value v1 v2 = match v1, v2 with
  | VInt i1, VInt i2 -> VInt (i1 * i2)
  | VInt i1, VFloat f2 -> VFloat (float_of_int i1 *. f2)
  | VFloat f1, VInt i2 -> VFloat (f1 *. float_of_int i2)
  | VFloat f1, VFloat f2 -> VFloat (f1 *. f2)
  | VStr s1, VInt i2 -> VStr (String.concat "" (Array.to_list (Array.make i2 s1)))
  | VInt i1, VStr s2 -> VStr (String.concat "" (Array.to_list (Array.make i1 s2)))
  | _, _ -> failwith (Printf.sprintf "Type mismatch: cannot multiply '%s' and '%s'" (string_of_value v1) (string_of_value v2))


let and_value v1 v2 = match (v1, v2) with
  | VBool b1, VBool b2 -> VBool (b1 && b2)
  | _, _ -> failwith (Printf.sprintf "Type mismatch: cannot and '%s' and '%s'" (string_of_value v1) (string_of_value v2))

let or_value v1 v2 = match (v1, v2) with
  | VBool b1, VBool b2 -> VBool (b1 || b2)
  | _, _ -> failwith (Printf.sprintf "Type mismatch: cannot or '%s' and '%s'" (string_of_value v1) (string_of_value v2))

let not_value v = match v with 
  | VBool b -> VBool (not b)
  | _ -> failwith (Printf.sprintf "Type mismatch: cannot not '%s'" (string_of_value v))

let eq_value v1 v2 = match (v1, v2) with
  | VBool b1, VBool b2 -> VBool (b1 = b2)
  | VInt i1, VInt i2 -> VBool (i1 = i2)
  | VFloat f1, VFloat f2 -> VBool (f1 = f2)
  | VStr s1, VStr s2 -> VBool (s1 = s2)
  | _, _ -> failwith (Printf.sprintf "Type mismatch: cannot eq '%s' and '%s'" (string_of_value v1) (string_of_value v2))

let lt_value v1 v2 = match (v1, v2) with
  | VInt i1, VInt i2 -> VBool (i1 < i2)
  | VFloat f1, VFloat f2 -> VBool (f1 < f2)
  | VStr s1, VStr s2 -> VBool (s1 < s2)
  | _, _ -> failwith (Printf.sprintf "Type mismatch: cannot lt '%s' and '%s'" (string_of_value v1) (string_of_value v2))

let if_value v1 v2 v3 = match v1 with
  | VBool b -> if b then v2 else v3
  | _ -> failwith (Printf.sprintf "Type mismatch: cannot if '%s'" (string_of_value v1))

  (*let rec eval_expr e = match e with
  | ECst v -> v
  | EAdd (e1, e2) ->
    let v1 = eval_expr e1 in
    let v2 = eval_expr e2 in
    add_value v1 v2
  
    | EMul (e1, e2) ->
    let v1 = eval_expr e1 in
    let v2 = eval_expr e2 in
    mul_value v1 v2
  
  | ENeg e -> 
    let v = eval_expr e in
    neg_value v
  
  | EAnd (e1, e2) ->
    let v1 = eval_expr e1 in
    let v2 = eval_expr e2 in
    and_value v1 v2

  | EOr (e1, e2) ->
    let v1 = eval_expr e1 in
    let v2 = eval_expr e2 in
    or_value v1 v2

  | ENot e ->
    let v = eval_expr e in
    not_value v

  | EEq (e1, e2) ->
    let v1 = eval_expr e1 in
    let v2 = eval_expr e2 in
    eq_value v1 v2
  
  | ELt (e1, e2) ->
    let v1 = eval_expr e1 in
    let v2 = eval_expr e2 in
    lt_value v1 v2

  | EIf (e1, e2, e3) ->
    let v1 = eval_expr e1 in
    let v2 = eval_expr e2 in
    let v3 = eval_expr e3 in
    if_value v1 v2 v3

  | _ -> failwith (Printf.sprintf "Evaluation of '%s' not yet implemented" (string_of_expr e))*)

let rec eval_expr gamma e = match e with
  | ECst v -> v
  | EAdd (e1, e2) ->
    let v1 = eval_expr gamma e1 in
    let v2 = eval_expr gamma e2 in
    add_value v1 v2
  
  | EMul (e1, e2) ->
    let v1 = eval_expr gamma e1 in
    let v2 = eval_expr gamma e2 in
    mul_value v1 v2
  
  | ENeg e ->
    let v = eval_expr gamma e in
    neg_value v
  
  | EAnd (e1, e2) ->
    let v1 = eval_expr gamma e1 in
    let v2 = eval_expr gamma e2 in
    and_value v1 v2

  | EOr (e1, e2) ->
    let v1 = eval_expr gamma e1 in
    let v2 = eval_expr gamma e2 in
    or_value v1 v2
  
  | ENot e ->
    let v = eval_expr gamma e in
    not_value v

  | EEq (e1, e2) ->
    let v1 = eval_expr gamma e1 in
    let v2 = eval_expr gamma e2 in
    eq_value v1 v2
  
  | ELt (e1, e2) ->
    let v1 = eval_expr gamma e1 in
    let v2 = eval_expr gamma e2 in
    lt_value v1 v2

  | EIf (e1, e2, e3) ->
    let v1 = eval_expr gamma e1 in
    let v2 = eval_expr gamma e2 in
    let v3 = eval_expr gamma e3 in
    if_value v1 v2 v3
  
  | _ -> failwith (Printf.sprintf "Evaluation of '%s' not yet implemented" (string_of_expr e))
