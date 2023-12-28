(* Types et fonctions pour représenter les déplacements en OCaml *)

type direction = North | South | East | West

type action = TurnLeft | TurnRight | Step

type state = int * int * direction

let move (x,y,d) a = match a with
  | TurnLeft ->
    (x, y, match d with
      | North -> West
      | West  -> South
      | South -> East
      | East  -> North
    )
  | TurnRight ->
    (x, y, match d with
      | North -> East
      | East  -> South
      | South -> West
      | West  -> North
    )
  | Step -> match d with
    | North -> (x,y+1,d)
    | West  -> (x-1,y,d)
    | South -> (x,y-1,d)
    | East  -> (x+1,y,d)

let string_of_direction d = match d with North -> "North" | South -> "South" | West -> "West" | East -> "East"

let string_of_action d = match d with Step -> "step" | TurnRight -> "turn right" | TurnLeft -> "turn left"

let string_of_state (x, y, d) = Printf.sprintf "(%i, %i, %s)" x y (string_of_direction d)

(* Syntaxe abstraite *)

type program = state * instruction

and instruction = (* TODO: A compléter *)
  | IAction of action
  | ISequence of instruction * instruction

(* Constructeurs d'expressions *)

let program s i = (s, i)

let action a = IAction a

let seq i1 i2 = ISequence (i1, i2)

(* Conversion en chaîne de caractères pour affichage *)
let rec string_of_program ((x, y, d), i) =
  Printf.sprintf "from (%i, %i, %s)\n\n%s" x y
    (string_of_direction d)
    (string_of_instruction i)

and string_of_instruction i = match i with (* TODO: A compléter *)
  | IAction a -> string_of_action a
  | ISequence (i1, i2) -> Printf.sprintf "%s\n%s" (string_of_instruction i1) (string_of_instruction i2)

(* Evaluateur *)

type 'a env = Empty | Add of string * 'a * 'a env

let empty_env = Empty

let add_env id v gamma = Add (id, v, gamma)

let rec find_env id gamma = match gamma with
  | Empty -> None
  | Add (id', v, _) when id' = id -> Some v
  | Add (_, _, gamma') -> find_env id gamma'

type 'a closure = Closure of 'a * ('a closure) env

let rec eval gamma (s, i) = eval_instruction gamma s i

and eval_instruction gamma (x, y, d) i = match i with (* TODO: A compléter *)
  | IAction a -> move (x, y, d) a
  | ISequence (i1, i2) -> eval_instruction gamma (eval_instruction gamma (x, y, d) i1) i2


















