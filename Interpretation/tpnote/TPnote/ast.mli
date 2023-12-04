(* Domaine d'évaluation *)
type direction = North | South | East | West
type action = TurnLeft | TurnRight | Step
type state = int * int * direction

val string_of_direction: direction -> string
val string_of_action: action -> string
val string_of_state: state -> string

val move: state -> action -> state

(* Syntaxe abstraite *)
type program
type instruction

(* Constructeurs *)
val program: state -> instruction -> program
val action: action -> instruction
val seq: instruction -> instruction -> instruction


(* Conversion en chaîne de caractères pour affichage *)
val string_of_program: program -> string

(* Evaluateur *)
type 'a env
type 'a closure
val empty_env: 'a env
val add_env: string -> 'a -> 'a env -> 'a env
val eval: (instruction closure) env -> program -> state


