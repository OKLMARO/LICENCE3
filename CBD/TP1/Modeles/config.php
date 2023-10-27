<?php
define('SQLServer', 'localhost');
define('SQLLogin', 'u32112661');
define('SQLPwd', 'f97oVOdrrgbU');
define('SQLDB', 'u32112661');

function dbConnect() {
  global $sqlLogin, $sqlPwd, $sqlDB;
  try {
    $db = new PDO('mysql:host='.SQLServer.';dbname='.SQLDB, SQLLogin, SQLPwd);
    return $db;
  } catch (PDOException $e) {
    die('Connexion échouée : ' . $e->getMessage());
  }
}

