%{

open Ast

%}

/* Déclaration des terminaux */
%token <int> INT
%token <float> FLOAT
%token <string> STR
%token TRUE FALSE
%token PLUS MINUS TIMES
%token LPAR RPAR
%token TERM
%token AND OR NOT
%token EQUAL DIFF
%token IF THEN ELSE

/* Précédences (priorité + associativité) des terminaux */
%nonassoc IF THEN ELSE
%left AND OR NOT 
%nonassoc EQUAL DIFF
%left PLUS MINUS
%left TIMES
%nonassoc NEG


/* Déclaration du non-terminal axiome (ici, ansyn) et du type de son attribut */
%type <Ast.expr> ansyn
%start ansyn

%%

/* Déclaration de la grammaire avec les actions sémantiques */

ansyn:
  | TERM ansyn              { $2 }
  | expr TERM               { $1 }  
;

expr:
  | value                   { ECst $1 }
  | expr PLUS expr          { EAdd ($1, $3) }
  | expr TIMES expr         { EMul ($1, $3) }
  | expr MINUS expr         { EAdd ($1, ENeg $3) }
  | MINUS expr  %prec NEG   { ENeg $2 }
  | LPAR expr RPAR          { $2 }
  | expr AND expr           { EAnd ($1, $3) }
  | expr OR expr            { EOr ($1, $3) }
  | NOT expr                { ENot $2 }
  | expr EQUAL expr         { EEq ($1, $3) }
  | expr DIFF expr          { ELt ($1, $3) }
  | IF expr THEN expr ELSE expr { EIf ($2, $4, $6) }
;

value:
  | INT                     { VInt $1 }
  | FLOAT                   { VFloat $1 }
  | TRUE                    { VBool true }
  | FALSE                   { VBool false }
  | STR                     { VStr $1 }
;