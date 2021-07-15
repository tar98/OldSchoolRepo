<?php
	include 'start_db.php';
	$bool = true;
	if(isset($_GET['Pat'])) //Autisti
	{
		$Scadenza = $_GET['scadenza'];
		$Pat = $_GET['Pat'];
	}

	$surname = $_GET['cognome'];
	$name = $_GET['nome'];
	$email = $_GET['e-mail'];
	$password = $_GET['password'];
	$cel = $_GET['telefono'];
	$year = $_GET['yyyymmgg'];
	$Gender = $_GET['gender'];
	$Country = $_GET['COMBO'];
	$Individuo = $_GET['ind'];

	if($Individuo == "Passegero")
	{
		try
		{
			$Query = "SELECT Email FROM Passegero;";
			$code = $db->prepare($Query);
			if($code->execute())
				while($row = $code->fetch())
				{
					if($row['Email'] == $email)
					{
						$msg = "Email gia' esistente!";
						$bool = false;
					}
				}
		}catch(PDOException $ex) {echo $ex->getMessage();}
		if($bool)
		{
			try
			{
				$Query = "INSERT INTO Passegero(Cognome, Nome, Email, Password, Telefono, Anno_nascita, Sesso, Nazionalita) VALUES (:cognome, :nome, :email, :password, :cel, :anno, :sesso, :nazionalita);";	
				$code = $db->prepare($Query);
				$code->bindValue(':cognome',$surname);
				$code->bindValue(':nome',$name);
				$code->bindValue(':email', $email);
				$code->bindValue(':password', $password);
				$code->bindValue(':cel', $cel);
				$code->bindValue(':anno', $year);
				$code->bindValue(':sesso', $Gender);
				$code->bindValue(':nazionalita',$Country);

				if($code->execute())
					$msg = "Dati correttamenti registrati!";
			}catch(PDOException $ex) {echo 'Error Bind : ' . $ex->getMessage();}
		}
	}
	else
	{
		try
		{
			$Query = "SELECT Email FROM Autista;";
			$code = $db->prepare($Query);
			if($code->execute())
				while($row = $code->fetch())
				{
					if($row['Email'] == $email)
					{
						$msg = "Email gia' esistente!";
						$bool = false;
					}
				}
		}catch(PDOException $ex) {echo $ex->getMessage();}
		if($bool)
		{
			try
			{
				$Query = "INSERT INTO Autista(Cognome, Nome, Email, Password, Telefono, data_nascita, sesso, nazionalita, Numero_patente, Scadenza_Patente) VALUES (:cognome, :nome, :email, :password, :cel, :anno, :sesso, :nazionalita, :patente, :scad);";	
				$code = $db->prepare($Query);
				$code->bindValue(':cognome',$surname);
				$code->bindValue(':nome',$name);
				$code->bindValue(':email', $email);
				$code->bindValue(':password', $password);
				$code->bindValue(':cel', $cel);
				$code->bindValue(':anno', $year);
				$code->bindValue(':sesso',$Gender);
				$code->bindValue(':nazionalita',$Country);
				$code->bindValue(':patente', $Pat);
				$code->bindValue(':scad', $Scadenza);

				if($code->execute())
					$msg = "Dati correttamenti registrati!";
			}catch(PDOException $ex) {echo 'Error Bind : ' . $ex->getMessage();}
		}
	}

?>
<!DOCTYPE html>
<html>
  <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">	
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
		  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
      <link rel="stylesheet" type="text/css" href="../css/style_sign.css"/>
  </head>
  <body>
    <div class = "Conteiner" name = "Sign-up">
        <div class="panel panel-default">
         <div class = "panel-heading">
            <center>
              <h2><center>Esito dell'iscrizione</center></h2>
            </center>  
         </div>
         <div class="panel-body">
          <center>
						<p><strong><?php echo $msg; ?></strong></p>
					</center>
         </div>
         <div class = "panel-footer">
					 		<center>
                <form action = "../index.html">
              		<p>
                		<button id = "Close" type = "submit" value = "Chiudi">Chiudi</button>
              		</p>
                </form>
						 	</center>
         </div>    
        </div>
    </div>
  </body>
</html>