(* Syntaxe abstraite *)
type value = 
  | Int of int
  | Float of float
  | Bool of bool
  | String of string

type expr =
  | ECst of value
  | EAdd of expr * expr
  | EMul of expr * expr
  | ENeg of expr
  | EId of string

(* Conversion en chaîne de caractères pour affichage *)
let rec string_of_value e = match e with
  | Int i -> string_of_int i
  | Float f -> string_of_float f
  | Bool b -> string_of_bool b
  | String s -> s

let rec string_of_expr e = match e with
  | ECst i -> string_of_value i
  | EAdd (e1, e2) -> Printf.sprintf "(%s) + (%s)" (string_of_expr e1) (string_of_expr e2)
  | EMul (e1, e2) -> Printf.sprintf "(%s) * (%s)" (string_of_expr e1) (string_of_expr e2)
  | ENeg e -> Printf.sprintf "-(%s)" (string_of_expr e)
  | EId e -> e
