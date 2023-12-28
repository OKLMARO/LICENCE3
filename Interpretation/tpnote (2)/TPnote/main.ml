
let _ =

  (* Ouverture un flot de caractère ; ici à partir de l'entrée standard *)  
  let source = Lexing.from_channel stdin in

  (* Boucle infinie interompue par une exception correspondant à la fin de fichier *)
  let rec f () =
    try
      (* Récupération d'une expression à partir de la source puis affichage de l'évaluation *)
      let p = Parser.program Lexer.anlex source in
      let s = Ast.eval_program [] p in
      Printf.printf "%s\n\t=> %s\n" (Ast.string_of_program p) s; flush stdout;
      f ()
    with Lexer.Eof -> Printf.printf "Bye\n"
  in

  f ()

