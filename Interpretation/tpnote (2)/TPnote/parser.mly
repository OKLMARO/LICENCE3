%{

open Ast

%}

/* Déclaration des terminaux */
%token <string> STR
%token <char> CHAR
%token SEMICOL
%token PROCESS ON
%token AO AF
%token ARR

/* Précédences (priorité + associativité) des terminaux */
%left SEMICOL
%left PROCESS
%left ON
%left AO AF
%left ARR


/* Déclaration du non-terminal axiome (ici, ansyn) et du type de son attribut */
%type <Ast.program> program
%start program

%%

/* Déclaration de la grammaire avec les actions sémantiques */

program:
  | PROCESS AO rule AF ON STR    { Program ($3, $6) }
;

rule:
  |rule SEMICOL rule             { RSeq ($1, $3) }
  |pattern ARR expr              { RSimple ($1, $3) }
;

pattern:
  | CHAR                         { LChar $1 }
;

expr:
  | STR                          { RStr $1 }
;