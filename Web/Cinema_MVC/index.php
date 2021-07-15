<!DOCTYPE html>
<html lang="it-IT">
  <head>
    <meta charset="Windows-1252">
    <title>Cinema</title>
    <script rel="stylesheet" type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script rel="stylesheet" type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script rel="stylesheet" type="text/javascript" src="javas.js"></script>
  </head>
  <body>
    <nav class="navbar navbar-light bg-light justify-content-between">
      <a class="navbar-brand"><h4><strong>Barra di Navigazione</strong></h4></a>
      <form class="form-inline">
        <span class='navbar-text'>Non hai effettuato l'accesso</span>
        <input class='btn btn-outline-success my-2 my-sm-0' id="session" type='button' value='Accedi'/>
      </form>
    </nav>
    <div class="container">
      <div class="panel panel-default" id="Elenco-Film">
        <div class="panel-heading" style="background:#0787f7;">
          <h4>Elenco Film</h4>
        </div>
        <div class="panel-body">
          <table class="table table-striped" id="El_Film">
            <thead>
              <tr>
                <strong>
                  <th>Titolo</th>
                  <th>Anno</th>
                  <th>Genere</th>
                  <th>Option</th>  
                 </strong>
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
        <div class="panel-footer">
          <button class="btn btn-primary mt-2 mt-sm-0" id="Elenco" type="button">Elenco Film <span class="glyphicon glyphicon-search"></button>
        </div>
      </div>
      <div class="panel panel-default" id="Film-Desc" style="display:none;">
        <div class="panel-heading" style="background:#0787f7;">
          <h4>Descrizione Film<button class="btn btn-default mt-2 mt-sm-0" id="CancDescFilm" type="button" style="float:right;"><span class="glyphicon glyphicon-remove"></span></button></h4>
        </div>
        <div class="panel-body">
          <table class="table table-striped" id="Desc_F">
            <thead>
              <tr>
                <strong>
                  <th>Titolo</th>
                  <th>Anno</th>
                  <th>Genere</th>
                  <th>CognomeRegista</th>
                  <th>LuogoProduzione</th>
                  <th id="FMod" style="display:none">Modifica</th>
                 </strong>
              </tr>
            </thead>
            <tbody>
            </tbody>
            <tfoot>
            </tfoot>
          </table>
        </div>
      </div>
      <!-- END FILM-->
      <!-- START CINEMA-->
      <div class="panel panel-default" id="Elenco-Cinema">
        <div class="panel-heading" style="background:#ff0400;">
          <h4>Elenco Cinema</h4>
        </div>
        <div class="panel-body">
          <table class="table table-striped" id="El_Cinema">
            <thead>
              <tr>
                <strong>
                  <th>Cinema</th>
                  <th>Option</th>
                 </strong>
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
        <div class="panel-footer">
          <button class="btn btn-primary mt-2 mt-sm-0" id="Elenco2" type="button">Elenco Cinema <span class="glyphicon glyphicon-search"></button>
        </div>
      </div>
       <div class="panel panel-default" id="Cine-Desc" style="display:none;">
        <div class="panel-heading" style="background:#ff2c28;">
          <h4>Descrizione Cinema<button class="btn btn-default mt-2 mt-sm-0" id="CancDescCine" type="button" style="float:right;"><span class="glyphicon glyphicon-remove"></span></button></h4>
        </div>
        <div class="panel-body">
          <table class="table table-striped" id="Desc_C">
            <thead>
              <tr>
                <strong>
                  <th>Cinema</th>
                  <th>Posti</th>
                  <th>Citta</th>
                  <th id="CMod" style="display:none">Modifica</th>
                 </strong>
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
       </div>
        <!--END CINEMA-->
        <!--START PROGRAMMATO-->
       <div class="panel panel-default" id="Elenco-Programmato">
        <div class="panel-heading" style="background:#ffe819;">
          <h4>Elenco Programmato</h4>
        </div>
        <div class="panel-body">
          <table class="table table-striped" id="El_Progr">
            <thead>
              <tr>
                <strong>
                  <th>Nome Cinema</th>
                  <th>Nome Film</th>
                  <th>Option</th>
                 </strong>
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
        <div class="panel-footer">
          <button class="btn btn-primary mt-2 mt-sm-0" id="Elenco3" type="button">Elenco Programmato <span class="glyphicon glyphicon-search"></button>
        </div>
      </div>
      <div class="panel panel-default" id="Prog-Desc" style="display:none;">
        <div class="panel-heading" style="background:#ffec42;">
          <h4>Descrizione Programmatico<button class="btn btn-default mt-2 mt-sm-0" id="CancDescProg" type="button" style="float:right;"><span class="glyphicon glyphicon-remove"></span></button></h4>
        </div>
        <div class="panel-body">
          <table class="table table-striped" id="Desc_P">
            <thead>
              <tr>
                <strong>
                  <th>Nome Cinema</th>
                  <th>Nome Film</th>
                  <th>Incasso</th>
                  <th>DataProiezione</th>
                  <th id="PMod" style="display:none">Modifica</th>
                 </strong>
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </body>
</html>