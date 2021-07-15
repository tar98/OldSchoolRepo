<?php
  $conn_db = "mysql:host=localhost;dbname=Pool-Car;charset:utf8";
  $user = "root";
  $pass = "root";
  try
  {
      $db = new PDO($conn_db, $user, $pass);
      $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
  }catch(PDOException $ex) {echo 'Connection Failed : ' . $ex->getMessage();}
?>