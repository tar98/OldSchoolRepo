<?php
$data = $_POST['var'];
include "database.php";
$array_code = array();
switch($data)
{
    case 1:
      $Query = "SELECT * FROM Film;";
      break;
    case 2:
      $Query = "SELECT * FROM Cinema";
      break;
    case 3:
      $Query = "SELECT * FROM (Film F INNER JOIN Programmato P ON F.CodFilm=P.CodiceFilm) INNER JOIN Cinema C ON P.CodiceCinema=C.CodCinema;";
      break;
    case 4:
      session_start();
      break;
    case 5:
      session_destroy();
      break;
}
try
{
  if($data == 4 || $data == 5)
  {
    $array_code[0] = "session_status";
    $array_code[1] = $data;
    echo json_encode($array_code);
  }
  else
  {
    $code = $db->prepare($Query);
    if($code->execute())
    {
      while($row = $code->fetch())
      {
        $array_code[] = $row;
      }
      echo json_encode($array_code);
    }
  }
}catch(PDOException $ex)
{
    echo "Error : " . $ex;
}
?>