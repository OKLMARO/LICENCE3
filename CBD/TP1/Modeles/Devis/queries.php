<?php

function getAllDevisInfo($db) {
  $nums = array();
  $req = $db->prepare('SELECT * FROM Devis ORDER BY dateCreation');
  $req->execute();
  while($tuple = $req->fetch()) {
    $info = array();
    $info['devis']  = $tuple['num'];
    $info['date']   = strftime("%d/%m/%Y",strtotime($tuple['dateCreation']));
    $info['client'] = $tuple['client'];
    $info['status'] = $tuple['status'];
    $info['sign']   = strftime("%d/%m/%Y",strtotime($tuple['dateSign']));
		$nums[$tuple['num']] = $info;
  }
  return $nums;
}

function getLines($db, $devis) {
  $req = $db->prepare('SELECT * FROM LigneDevis l, Produit p
                       WHERE l.numDevis = :devis
                       AND p.modele = l.modele');
  $req->bindValue(':devis', $devis, PDO::PARAM_STR);
  $req->execute();
  $lines = array();
  while($tuple = $req->fetch())
    $lines[$tuple['modele']] = $tuple;
  return $lines;
}

function createDevis($db, $client) {
  // Calcul du numéro de devis
	$req = $db->prepare('SELECT COUNT(DISTINCT num) AS cpt,
                              EXTRACT(YEAR FROM CURRENT_DATE) as curyear,
                              CURRENT_DATE as curdate
                       FROM Devis
                       WHERE EXTRACT(YEAR FROM dateCreation) = EXTRACT(YEAR FROM CURRENT_DATE);');
	$req->execute();
  $tuple = $req->fetchAll()[0];
	$date = $tuple['curdate'];	
	$num = sprintf("D%s%2$05d",$tuple['curyear'],$tuple['cpt']+1);

	// On insère un nouveau tuple dans la base Devis
	$req = $db->prepare('INSERT INTO Devis(num,dateCreation,client,status) VALUES (:num,:date,:client,\'encours\');');
  $req->bindValue(':num', $num, PDO::PARAM_STR);
  $req->bindValue(':date', $date, PDO::PARAM_STR);
  $req->bindValue(':client', $client, PDO::PARAM_STR);
	$req->execute();
  return $num;
}

function updateClient($db, $devis, $client) {
  $req = $db->prepare('UPDATE Devis
                       SET client = :client
                       WHERE num = :devis;');
  $req->bindValue(':devis', $devis, PDO::PARAM_STR);
  $req->bindValue(':client', $client, PDO::PARAM_STR);
	$req->execute();
}

function signDevis($db, $devis) {
  // Signe le devis, cad
  //   - fait passer l'attribut dateSign de NULL à CURRENT_DATE dans la BD
  //   - fait passer le status de 'encours' à 'signe'
  // Pre-condition : $devis est modifiable
  $req = $db->prepare('UPDATE Devis SET dateSign = CURRENT_DATE, status = \'signe\' WHERE num = :devis;');
  $req->bindValue(':devis', $devis, PDO::PARAM_STR);
  $req->execute();
}

function cancelDevis($db, $devis) {
  // Annule le devis, cad
  //   - fait passer le status de 'encours' à 'annule'
  // Pre-condition : $devis est modifiable
  $req = $db->prepare('UPDATE Devis SET status = \'annule\' WHERE num = :devis;');
  $req->bindValue(':devis', $devis, PDO::PARAM_STR);
  $req->execute();
}

function deleteDevisLine($db, $devis, $modele) {
  // Supprime une ligne d'un devis
  // Pre-condition :
  //   - $devis est modifiable
  //   - $modele est une ligne de $devis  
  $req = $db->prepare('DELETE FROM LigneDevis WHERE numDevis = :devis AND modele = :modele;');
  $req->bindValue(':devis', $devis, PDO::PARAM_STR);
  $req->bindValue(':modele', $modele, PDO::PARAM_STR);
  $req->execute();
}

function updateDevisLine($db, $devis, $modele, $qt) {
  // Met-à-jour la quantité d'une ligne dans un devis
  // Pre-condition :
  //   - $devis est modifiable
  //   - $modele est une ligne de $devis
  //   - $qt est une valeur entière strictement positive
  $req = $db->prepare('UPDATE LigneDevis SET quantite = :qt WHERE numDevis = :devis AND modele = :modele;');
  $req->bindValue(':devis', $devis, PDO::PARAM_STR);
  $req->bindValue(':modele', $modele, PDO::PARAM_STR);
  $req->bindValue(':qt', $qt, PDO::PARAM_INT);
  $req->execute();
}

function addDevisLine($db, $devis, $modele, $qt) {
  // Ajoute la ligne (modele, qt) aux lignes d'un devis
  // Pre-condition :
  //   - $devis est modifiable
  //   - $modele n'est pas une ligne de $devis
  //   - $qt est une valeur entière strictement positive
  $req = $db->prepare('INSERT INTO LigneDevis(numDevis,modele,quantite) VALUES (:devis,:modele,:qt);');
  $req->bindValue(':devis', $devis, PDO::PARAM_STR);
  $req->bindValue(':modele', $modele, PDO::PARAM_STR);
  $req->bindValue(':qt', $qt, PDO::PARAM_INT);
  $req->execute();
}

function isModel($db, $modele) {
  // Retourne s'il existe un produit dont le modele est $modele
  $req = $db->prepare('SELECT * FROM Produit WHERE modele = :modele;');
  $req->bindValue(':modele', $modele, PDO::PARAM_STR);
  $req->execute();
  if($req->fetch())
    return true;
  return false;
}