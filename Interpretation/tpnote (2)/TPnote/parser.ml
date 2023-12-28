type token =
  | STR of (string)
  | CHAR of (char)
  | SEMICOL
  | PROCESS
  | ON
  | AO
  | AF
  | ARR

open Parsing;;
let _ = parse_error;;
# 2 "parser.mly"

open Ast

# 18 "parser.ml"
let yytransl_const = [|
  259 (* SEMICOL *);
  260 (* PROCESS *);
  261 (* ON *);
  262 (* AO *);
  263 (* AF *);
  264 (* ARR *);
    0|]

let yytransl_block = [|
  257 (* STR *);
  258 (* CHAR *);
    0|]

let yylhs = "\255\255\
\001\000\002\000\002\000\003\000\004\000\000\000"

let yylen = "\002\000\
\006\000\003\000\003\000\001\000\001\000\002\000"

let yydefred = "\000\000\
\000\000\000\000\000\000\006\000\000\000\004\000\000\000\000\000\
\000\000\000\000\000\000\002\000\000\000\005\000\003\000\001\000"

let yydgoto = "\002\000\
\004\000\007\000\008\000\015\000"

let yysindex = "\001\000\
\255\254\000\000\251\254\000\000\003\255\000\000\253\254\254\254\
\003\255\002\255\007\255\000\000\008\255\000\000\000\000\000\000"

let yyrindex = "\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000"

let yygindex = "\000\000\
\000\000\002\000\000\000\000\000"

let yytablesize = 11
let yytable = "\009\000\
\005\000\001\000\003\000\010\000\006\000\011\000\013\000\014\000\
\016\000\000\000\012\000"

let yycheck = "\003\001\
\006\001\001\000\004\001\007\001\002\001\008\001\005\001\001\001\
\001\001\255\255\009\000"

let yynames_const = "\
  SEMICOL\000\
  PROCESS\000\
  ON\000\
  AO\000\
  AF\000\
  ARR\000\
  "

let yynames_block = "\
  STR\000\
  CHAR\000\
  "

let yyact = [|
  (fun _ -> failwith "parser")
; (fun __caml_parser_env ->
    let _3 = (Parsing.peek_val __caml_parser_env 3 : 'rule) in
    let _6 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 32 "parser.mly"
                                 ( Program (_3, _6) )
# 88 "parser.ml"
               : Ast.program))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'rule) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'rule) in
    Obj.repr(
# 36 "parser.mly"
                                 ( RSeq (_1, _3) )
# 96 "parser.ml"
               : 'rule))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'pattern) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'expr) in
    Obj.repr(
# 37 "parser.mly"
                                 ( RSimple (_1, _3) )
# 104 "parser.ml"
               : 'rule))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : char) in
    Obj.repr(
# 41 "parser.mly"
                                 ( LChar _1 )
# 111 "parser.ml"
               : 'pattern))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 45 "parser.mly"
                                 ( RStr _1 )
# 118 "parser.ml"
               : 'expr))
(* Entry program *)
; (fun __caml_parser_env -> raise (Parsing.YYexit (Parsing.peek_val __caml_parser_env 0)))
|]
let yytables =
  { Parsing.actions=yyact;
    Parsing.transl_const=yytransl_const;
    Parsing.transl_block=yytransl_block;
    Parsing.lhs=yylhs;
    Parsing.len=yylen;
    Parsing.defred=yydefred;
    Parsing.dgoto=yydgoto;
    Parsing.sindex=yysindex;
    Parsing.rindex=yyrindex;
    Parsing.gindex=yygindex;
    Parsing.tablesize=yytablesize;
    Parsing.table=yytable;
    Parsing.check=yycheck;
    Parsing.error_function=parse_error;
    Parsing.names_const=yynames_const;
    Parsing.names_block=yynames_block }
let program (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 1 lexfun lexbuf : Ast.program)
