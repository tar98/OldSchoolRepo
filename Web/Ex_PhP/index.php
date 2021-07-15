<html>
  <head>
  </head>
  <body>
    <?php
    if(isset($_GET["textbar"]))
    {
      $r_num = $_GET["textbar"];
      if($_GET["rand"] == $_GET["textbar"]) echo "Bravo, hai indovinato!";
      else
          {
            echo "Riprova!";
            $count = $_GET['try'] + 1;
            if($count == 3) echo "Il Gioco è finito";
          }
    }
    else
    {
      echo "Inizia Partita!";
      $count = 0;
      $r_num = rand(1,100);
    }
    ?>
    
    <title>Gioco dell'indovina numero</title>
    <h1>Gioco dell'indovina numero</h1> 
    <form action="" method="get" >
      <p><input type="text" name="textbar"</p>
      <p><input type="hidden" name="try" value = <?php echo $count;?>></p>
      <p><input type="hidden" name="rand" value = "<?php echo $r_num;?>"></p>
      <p><input type="submit" name="button"></p>
    </form>
  </body>
</html>