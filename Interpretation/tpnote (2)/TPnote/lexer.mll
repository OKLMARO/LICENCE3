{
open Parser
exception Eof
}


(* Déclaration du dictionnaire (regexp -> terminal/token) *)

rule anlex = parse
  | [' ' '\t' '\n' '\r']              { anlex lexbuf (* Oubli des espacements et passages à la ligne *) }
  | "'" _ "'" as lxm                  { CHAR (lxm.[1]) }
  | '"'                               { str "" lexbuf }
  | "{"                               { AO }
  | "}"                               { AF }
  | "=>"                              { ARR }
  | ";"                               { SEMICOL }
  | "process"                         { PROCESS }
  | "on"                              { ON }
  | eof                               { raise Eof }
  | _  as lxm                         { (* Pour tout autre caractère : message sur la sortie erreur + oubli *)
                                        Printf.eprintf "Unknown character '%c': ignored\n" lxm; flush stderr;
                                        anlex lexbuf
                                      }

and str s = parse
  | '"'                               { STR s }
  | _ as c                            { str (Printf.sprintf "%s%c" s c) lexbuf }