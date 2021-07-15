<?php
if(isset($_POST['exit']))
{
	session_start();
	session_destroy();
	echo	"<script>
			alert('Logout eseguito');
			location.href = '../index.html';
			</script>";
}
else
{
	include 'start_db.php';
	$User = $_POST['user'];
	$Pass = $_POST['PS'];
	$Class = $_POST['choose']; //se autista o passegero
	if($Class == "Autista")
	{
		try
		{
			$Query = "SELECT ID_autista ,Email, Password FROM Autista;";
			$code = $db->prepare($Query);
			if($code->execute())
			{
				while($row = $code->fetch())
				{
					if($row['Email'] == $User && $row['Password'] == $Pass)
					{
						session_start();
						echo 
							"<script>
							alert('Utente Registrato');
							location.href = '../index.html';
							</script>";
						$_SESSION['user'] = $User;
						$_SESSION['pass'] = $Pass;
						$_SESSION['ID'] = $row['ID_autista'];
					}
				}
							echo
					"<script>
					alert('Credenziali Errate');
					location.href = '../Sign-in.html';
					</script>";
			}
		}catch(PDOException $ex) {echo $ex->getMessage();}
	}
	else
	{
		try
		{
			$Query = "SELECT ID_pas, Email, Password FROM Passegero;";
			$code = $db->prepare($Query);
			if($code->execute())
			{
				while($row = $code->fetch())
				{
					if($row['Email'] == $User && $row['Password'] == $Pass)
					{
						session_start();
						echo 
							"<script>
							alert('Utente Registrato');
							location.href = '../index.html';
							</script>";
						$_SESSION['user'] = $User;
						$_SESSION['pass'] = $Pass;
						$_SESSION['ID'] = $row['ID_pas'];
					}
				}
							echo
					"<script>
					alert('Credenziali Errate');
					location.href = '../Sign-in.html';
					</script>";
			}
		}catch(PDOException $ex) {echo $ex->getMessage();}
	}
}
?>