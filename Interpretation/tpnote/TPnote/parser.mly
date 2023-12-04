%{
open Ast
%}

/* Déclaration des terminaux */
/* TODO: A compléter */
%token TERM
%token NORTH SOUTH EAST WEST
%token X Y D FROM
...

/* Précédences (priorité + associativité) des terminaux */
/* TODO: A compléter */
...

/* Déclaration du non-terminal axiome (ici, ansyn) et du type de son attribut */
%type <Ast.program> ansyn
%start ansyn

%%

/* Déclaration de la grammaire avec les actions sémantiques */

ansyn:
  | TERM ansyn              { $2 }
  | program TERM            { $1 }  
;

program:
  | FROM LPAR X EQ INT COMMA Y EQ INT COMMA D EQ direction RPAR instr { program ($5, $9, $13) $15 }
;

direction:
  | NORTH   { North }
  | SOUTH   { South }
  | EAST    { East  }
  | WEST    { West  }
;

/* TODO: A compléter */

instr:
  | ...  { ... }
  | ...  { ... }
  | ...  { ... }
;





