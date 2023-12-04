{
open Parser
exception Eof
}


(* Déclaration du dictionnaire (regexp -> terminal/token) *)

rule anlex = parse
  | [' ' '\t' '\n' '\r']      { anlex lexbuf (* Oubli des espacements et passages à la ligne *) }
  | ";;"                      { TERM }
  | "North"                   { NORTH }
  | "South"                   { SOUTH }
  | "East"                    { EAST }
  | "West"                    { WEST }
  | "X"                       { X }
  | "Y"                       { Y }
  | "D"                       { D }
  | "from"                    { FROM }

  (* TODO: A compléter *)
  ...

  | eof                       { raise Eof }
  | _  as lxm                 { (* Pour tout autre caractère : message sur la sortie erreur + oubli *)
                                Printf.eprintf "Unknown character '%c': ignored\n" lxm; flush stderr;
                                anlex lexbuf
                              }


