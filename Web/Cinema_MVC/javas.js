$(document).ready(function()
{
  function getQueryWithAJAX(num)
  {
    var json = 
        $.ajax
        ({
         method: "POST",
         async: false, // richiamare la funzione in casi sincroni (false), come premere di un bottone.
         url: "query.php",
         data: {var: num},
         dataType: "json"
        }).done(function(data){
          return data;
        }).responseJSON; // cio che ritorna ovvero json = data 
    return json;
  }
  
  function CloseElenDesc()  // "Ripristina" la pagina eliminando elenchi e descrzioni
  {
    $("#El_Film > tbody").html('');
    $("#Desc_F > tbody").html('');
    $("#Film-Desc").hide();
    $("#El_Cinema > tbody").html('');
    $("#Desc_C > tbody").html('');
    $("#Cine-Desc").hide();
    $("#El_Progr > tbody").html('');
    $("#Desc_P > tbody").html('');
    $("#Prog-Desc").hide();
  }
  
  //-- Film --
  
  $("#Elenco").click(function()
  {
      var result = getQueryWithAJAX(1);
      $("#El_Film > tbody").html('');
      for (var i in result)
        {
          $("#El_Film > tbody").append("<tr><td>"+result[i].Titolo+"</td><td>"+result[i].AnnoProduzione+"</td><td>"+result[i].Genere+"</td><td><button class='btn btn-info mt-2 mt-sm-0' id='FilmOpt' value='"+i+"'>Info</button></td></tr>");
        }
   });
  
  $("#El_Film").on("click", "#FilmOpt", function()
  {
    var result = getQueryWithAJAX(1);
    $("#Desc_F > tbody").html('');
    var i = $(this).val();
    if($("#session").val().localeCompare("Accedi") === 0)
    {
      $("#Desc_F > tbody").append("<tr><td>"+result[i].Titolo+"</td><td>"+result[i].AnnoProduzione+"</td><td>"+result[i].Genere+"</td><td>"+result[i].CognomeRegista+"</td><td>"+result[i].LuogoProduzione+"</td></tr>");
    }
    else
    {
      $("#Desc_F > tbody").append("<tr><td>"+result[i].Titolo+"</td><td>"+result[i].AnnoProduzione+"</td><td>"+result[i].Genere+"</td><td>"+result[i].CognomeRegista+"</td><td>"+result[i].LuogoProduzione+"</td><td><button class='btn btn-outline-secondary mt-2 mt-sm-0' id='ModF' value='"+i+"'>Modifica</button></td></tr>");
    }
    
    $("#Film-Desc").show();
  });
  
  $("#CancDescFilm").on("click", function()
  {
    $("#Desc_F > tbody").html('');
    $("#Film-Desc").hide();
    $("#Desc_F > tfoot").html('');
  });
  
  //-- Cinema --
   
  $("#Elenco2").click(function()
  {
      var result = getQueryWithAJAX(2);
      $("#El_Cinema > tbody").html('');
      for (var i in result)
        $("#El_Cinema > tbody").append("<tr><td>"+result[i].Nome+"</td><td><button class='btn btn-info mt-2 mt-sm-0' id='CineOpt' value='"+i+"'>Info</button></td></tr>");
   });
  
  $("#El_Cinema").on("click", "#CineOpt", function()
  {
    var result = getQueryWithAJAX(2);
    $("#Desc_C > tbody").html('');
    var i = $(this).val();
    $("#Desc_C > tbody").append("<tr><td>"+result[i].Nome+"</td><td>"+result[i].Posti+"</td><td>"+result[i].Citta+"</td></tr>");
    $("#Cine-Desc").show();
  });
  
  $("#CancDescCine").on("click", function()
  {
    $("#Desc_C > tbody").html('');
    $("#Cine-Desc").hide();
  });
  
  //-- Programmato --
  
  $("#Elenco3").click(function()
  {
      var result = getQueryWithAJAX(3);
      $("#El_Progr > tbody").html('');
      for (var i in result)
        $("#El_Progr > tbody").append("<tr><td>"+result[i].Nome+"</td><td>"+result[i].Titolo+"</td><td><button class='btn btn-info mt-2 mt-sm-0' id='ProgOpt' value='"+i+"'>Info</button></td></tr>");
   });
  
  $("#El_Progr").on("click", "#ProgOpt", function()
  {
    var result = getQueryWithAJAX(3);
    $("#Desc_P > tbody").html('');
    var i = $(this).val();
    $("#Desc_P > tbody").append("<tr><td>"+result[i].Nome+"</td><td>"+result[i].Titolo+"</td><td>"+result[i].Incasso+"</td><td>"+result[i].DataProiezione+"</td></tr>");
    $("#Prog-Desc").show();
  });
  
  $("#CancDescProg").on("click", function()
  {
    $("#Desc_P > tbody").html('');
    $("#Prog-Desc").hide();
  });
  
  //Sessione
  
  $("#session").on("click", function()
  {
    CloseElenDesc();
    if($(this).val().localeCompare("Accedi") === 0)
      {
         $("#FMod").show();
         $("#CMod").show();
         $("#PMod").show();
         $(".navbar-text").text("Hai Effetuato l'accesso!");
         $(this).attr("class","btn btn-outline-danger my-2 my-sm-0");
         $(this).val("Esci");
      }
    else
      {
         $(".navbar-text").text("Non hai Effetuato l'accesso!");
         $(this).attr("class","btn btn-outline-success my-2 my-sm-0");
         $(this).val("Accedi");
         $("#FMod").hide();
         $("#CMod").hide();
         $("#PMod").hide();
      }
  });
  
  $("#Desc_F > tbody").on("click", "#ModF", function()
  {
      $("#Desc_F > tfoot").append("<tr><td><input id='1' type='text' required></td><td><input id='2' type='text' required></td><td><input id='3' type='text' required></td><td><input id='4' type='text' required></td><td><input id='5' type='text' required></td><td><input class='btn btn-dark' type='submit' id='apply' value='Applica'></td></tr>");
  });
  
  $("#Desc_F > tfoot").on("click", "#apply", function()
  {
      var i = 1;
      var arr = new Array(5);
      while (i < 6) 
      {
        arr[i-1] = $("#Desc_F > tfoot > tr > td > input#"+i).attr("value");
        ++i;
      }
    /*
      $.ajax
      ({
         method: "POST",
         async: true, // richiamare la funzione in casi sincroni (false), come premere di un bottone.
         url: "query.php",
         data: {var : 4, Titolo : $('')},
         dataType: "json"
      });*/
  });
  
});

// -- http://api.jquery.com/jquery.ajax/ --