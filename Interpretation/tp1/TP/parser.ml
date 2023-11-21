type token =
  | INT of (int)
  | PLUS
  | LPAR
  | RPAR
  | TERM
  | TIME
  | MINUS
  | ID of (string)

open Parsing;;
let _ = parse_error;;
# 2 "parser.mly"

open Ast

# 18 "parser.ml"
let yytransl_const = [|
  258 (* PLUS *);
  259 (* LPAR *);
  260 (* RPAR *);
  261 (* TERM *);
  262 (* TIME *);
  263 (* MINUS *);
    0|]

let yytransl_block = [|
  257 (* INT *);
  264 (* ID *);
    0|]

let yylhs = "\255\255\
\001\000\001\000\002\000\002\000\002\000\002\000\002\000\002\000\
\002\000\000\000"

let yylen = "\002\000\
\002\000\002\000\001\000\001\000\003\000\003\000\002\000\003\000\
\003\000\002\000"

let yydefred = "\000\000\
\000\000\000\000\004\000\000\000\000\000\000\000\003\000\010\000\
\000\000\000\000\001\000\007\000\000\000\002\000\000\000\000\000\
\009\000\000\000\006\000\000\000"

let yydgoto = "\002\000\
\008\000\009\000"

let yysindex = "\003\000\
\000\255\000\000\000\000\012\255\000\255\012\255\000\000\000\000\
\016\255\022\255\000\000\000\000\012\255\000\000\012\255\012\255\
\000\000\004\255\000\000\004\255"

let yyrindex = "\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\028\255\000\000\032\255"

let yygindex = "\000\000\
\001\000\252\255"

let yytablesize = 39
let yytable = "\010\000\
\003\000\012\000\004\000\001\000\005\000\011\000\006\000\007\000\
\018\000\015\000\019\000\020\000\003\000\000\000\004\000\000\000\
\000\000\013\000\006\000\007\000\014\000\015\000\016\000\013\000\
\000\000\017\000\000\000\015\000\016\000\005\000\000\000\005\000\
\005\000\008\000\005\000\008\000\008\000\000\000\008\000"

let yycheck = "\004\000\
\001\001\006\000\003\001\001\000\005\001\005\000\007\001\008\001\
\013\000\006\001\015\000\016\000\001\001\255\255\003\001\255\255\
\255\255\002\001\007\001\008\001\005\001\006\001\007\001\002\001\
\255\255\004\001\255\255\006\001\007\001\002\001\255\255\004\001\
\005\001\002\001\007\001\004\001\005\001\255\255\007\001"

let yynames_const = "\
  PLUS\000\
  LPAR\000\
  RPAR\000\
  TERM\000\
  TIME\000\
  MINUS\000\
  "

let yynames_block = "\
  INT\000\
  ID\000\
  "

let yyact = [|
  (fun _ -> failwith "parser")
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : Ast.expr) in
    Obj.repr(
# 30 "parser.mly"
                            ( _2 )
# 98 "parser.ml"
               : Ast.expr))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 1 : 'expr) in
    Obj.repr(
# 31 "parser.mly"
                            ( _1 )
# 105 "parser.ml"
               : Ast.expr))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : string) in
    Obj.repr(
# 35 "parser.mly"
                                  ( EId _1 )
# 112 "parser.ml"
               : 'expr))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 0 : int) in
    Obj.repr(
# 36 "parser.mly"
                                  ( ECst _1 )
# 119 "parser.ml"
               : 'expr))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'expr) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'expr) in
    Obj.repr(
# 37 "parser.mly"
                                  ( EAdd (_1, _3) )
# 127 "parser.ml"
               : 'expr))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'expr) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'expr) in
    Obj.repr(
# 38 "parser.mly"
                                  ( EMul (_1, _3) )
# 135 "parser.ml"
               : 'expr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 0 : 'expr) in
    Obj.repr(
# 39 "parser.mly"
                                  ( ENeg _2 )
# 142 "parser.ml"
               : 'expr))
; (fun __caml_parser_env ->
    let _1 = (Parsing.peek_val __caml_parser_env 2 : 'expr) in
    let _3 = (Parsing.peek_val __caml_parser_env 0 : 'expr) in
    Obj.repr(
# 40 "parser.mly"
                                  ( EAdd (_1, ENeg _3) )
# 150 "parser.ml"
               : 'expr))
; (fun __caml_parser_env ->
    let _2 = (Parsing.peek_val __caml_parser_env 1 : 'expr) in
    Obj.repr(
# 41 "parser.mly"
                                  ( _2 )
# 157 "parser.ml"
               : 'expr))
(* Entry ansyn *)
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
let ansyn (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (Parsing.yyparse yytables 1 lexfun lexbuf : Ast.expr)
