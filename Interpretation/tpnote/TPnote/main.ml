
let _ =

  (* Ouverture un flot de caractère ; ici à partir de l'entrée standard *)  
  let source = Lexing.from_channel stdin in

  (* Boucle infinie interompue par une exception correspondant à la fin de fichier *)
  let rec f () =
    try
      (* Récupération d'un programme à partir de la source puis affichage de l'évaluation *)
      let p = Parser.ansyn Lexer.anlex source in
      Printf.printf "%s\n    => %s\n" (Ast.string_of_program p) (Ast.string_of_state (Ast.eval Ast.empty_env p)); flush stdout;
      f ()
    with Lexer.Eof -> Printf.printf "Bye\n"
  in

  f ()

