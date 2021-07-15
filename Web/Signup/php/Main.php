<?php
  session_start();
  if(isset($_SESSION['user']))
  {
		//$body;
    $msg = "Ha Fatto l'accesso come utente!";
    $bool = true;
  }
  else
  {
    $msg = "Non ha fatto l'accesso!";
    $bool = false;
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
  </head>
  <body>
    <div class = "Conteiner" name = "Sign-up">
        <div class="panel panel-default">
         <div class = "panel-heading">
            <center>
              <h2><center>Menu Principale</center></h2>
            </center>  
         </div>
         <div class="panel-body">
					 <table class = "Main">
						 <table id = "Viaggio">
							<left>
								<tr>
									<th>Viaggio</th>
								</tr>
								<tr>
									<td>
										Destinazione : <input type = "text" name = "dest" required><br>
									</td>
								</tr>
								<tr>
									<td>
										Data : <input type = "date" name = "date_viaggio" required><br>
									</td>
								</tr>
								<tr>
									<td> Ora arrivo:
										<select>
											<option value="0">00</option>
											<option value="1">01</option>
											<option value="2">02</option>
											<option value="3">03</option>
											<option value="4">04</option>
											<option value="5">05</option>
											<option value="6">06</option>
											<option value="7">07</option>
											<option value="8">08</option>
											<option value="9">09</option>
											<option value="10">10</option>
											<option value="11">11</option>
											<option value="12">12</option>
											<option value="13">13</option>
											<option value="14">14</option>
											<option value="15">15</option>
											<option value="16">16</option>
											<option value="17">17</option>
											<option value="18">18</option>
											<option value="19">19</option>
											<option value="20">20</option>
											<option value="21">21</option>
											<option value="22">22</option>
											<option value="23">23</option>
										</select>
									</td>
								</tr>
							</left>
						 </table>
						<table id = "Prenota">
							<center>
								<strong><?php echo $msg; ?></strong>
								<input type = "hidden" name = 'user' value = "<?php if($bool) echo $_SESSION['user']; ?>">
								<input type = "hidden" name = 'pass 'value = "<?php if($bool) echo md5($_SESSION['pass']); ?>">
								<input type = "hidden" name = 'ID' value = "<?php if($bool) echo $_SESSION['ID']; ?>">
							</center>
						</table>
					</table>
         </div>
         <div class = "panel-footer">
					 		<center>
                <?php
                if($bool)
                {
                  echo "<form action = 'sign_in_out.php' method = 'POST'>
                          <input id = 'exit' type = 'submit' name = 'but' value = 'Logout'>
													<input type = 'hidden' name = 'exit' value = ".$bool.">
                        </form>";
                }
                else
                {
                  echo "<input id = 'exit' type = 'button' onclick = 'back_index()' value = 'Indietro'>";
                }
                ?>
						 	</center>
         </div>
        </div>
			<script>
				function back_index()
				{
					var Win = window.self;
					Win.location.href = "../index.html";
				}
			</script>
    </div>
  </body>
</html>