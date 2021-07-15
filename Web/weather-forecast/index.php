<!DOCTYPE html>
<html>
  <head>
    <meta charset = "Windows-1252">
    <title> Weather-Forecast </title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <!-- Latest compiled and minified JavaScript -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  </head>
  <script type="text/javascript" language ="javascript">
    $(document).ready(function()
      {
         var date = new Date();
         date.toUTCString();
         document.getElementById("dat").innerHTML = date;
        $("#search-weather").click(function()
        {
          var city = $("#Location-weather").val();
           $.getJSON("https://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=613db5729072ebdbc0112470065943d0", function(data)
            {
              $("#Main-weather").text(data.weather[0].main);
              $("#descr-weather").text(data.weather[0].description);
              $("#temp-weather").text(data.main.temp + " Celsious");
              $("#pressure-weather").text(data.main.pressure + " hpa");
              $("#humidity-weather").text(data.main.humidity + "%");
              $("#wind-Speed-weather").text(data.wind.speed + " m/s");
              $("#wind-deg-weather").text(data.wind.deg);
              $("#visibility-weather").text(data.visibility);
            });
        });
      
        $("#reset-weather").click(function()
        {
            $("#Main-weather").text("None");
            $("#descr-weather").text("None");
            $("#temp-weather").text("None");
            $("#pressure-weather").text("None");
            $("#humidity-weather").text("None");
            $("#wind-Speed-weather").text("None");
            $("#wind-deg-weather").text("None");
            $("#visibility").text("None");
        });
      
        $("#search-forecast").click(function()
        {
           var city = $("#Location-forecast").val();
           $.getJSON("https://api.openweathermap.org/data/2.5/forecast?q="+city+"&APPID=613db5729072ebdbc0112470065943d0", function(data)
            {
             for(var i in data.list)
               {
                 $("#date-time").append("<option value="+i+">"+data.list[i].dt_txt+"</option>");
               }
            $(".lblcls").show();
            $("#Main-forecast").text(data.list[0].weather[0].main);
            $("#descr-forecast").text(data.list[0].weather[0].description);
            $("#temp-forecast").text(data.list[0].main.temp + " Celsious");
            $("#pressure-forecast").text(data.list[0].main.pressure + " hpa");
            $("#humidity-forecast").text(data.list[0].main.humidity + "%");
            $("#wind-Speed-forecast").text(data.list[0].wind.speed + " m/s");
            $("#wind-deg-forecast").text(data.list[0].wind.deg);
           });
        });
      
         $("#date-time").change(function()
         {
            var city = $("#Location-forecast").val();
            $.getJSON("https://api.openweathermap.org/data/2.5/forecast?q="+city+"&APPID=613db5729072ebdbc0112470065943d0", function(data)
            {
                var i = document.getElementById("date-time").value;
                $("#Main-forecast").text(data.list[i].weather[0].main);
                $("#descr-forecast").text(data.list[i].weather[0].description);
                $("#temp-forecast").text(data.list[i].main.temp + " Celsious");
                $("#pressure-forecast").text(data.list[i].main.pressure + " hpa");
                $("#humidity-forecast").text(data.list[i].main.humidity + "%");
                $("#wind-Speed-forecast").text(data.list[i].wind.speed + " m/s");
                $("#wind-deg-forecast").text(data.list[i].wind.deg);
            });
         });
      
        $("#reset-forecast").click(function()
        {
            $("#Main-forecast").text("None");
            $("#descr-forecast").text("None");
            $("#temp-forecast").text("None");
            $("#pressure-forecast").text("None");
            $("#humidity-forecast").text("None");
            $("#wind-Speed-forecast").text("None");
            $("#wind-deg-forecast").text("None");
            $(".lblcls").hide();
            $("#date-time").html('');
        });
      
      });
  </script>
  <body>
    <div class="container">
     <div id="Weather" class="panel panel-default">
       <div class="panel-heading" style="background:#fa6b79;">
         <h4>Weather</h4>
         <h5 id="dat"></h5>
       </div>
       <div class = "panel-body">
         <table class="table table-striped">
          <thead></thead>
          <tbody>
            <tr>
              <td>Main</td>
              <td id="Main-weather">None</td>
            </tr>
            <tr>
              <td>Description</td>
              <td id="descr-weather">None</td>
            </tr>
            <tr>
              <td>Temp</td>
              <td id="temp-weather">None</td>
            </tr>
            <tr >
              <td>Pressure</td>
              <td id="pressure-weather">None</td>
            </tr>
            <tr >
              <td>Humidity</td>
              <td id="humidity-weather">None</td>
            </tr>
            <tr>
              <td>Wind Speed</td>
              <td id="wind-Speed-weather">None</td>
            </tr>
            <tr>
              <td>Wind deg</td>
              <td id="wind-deg-weather">None</td>
            </tr>
            <tr>
              <td>Visibilty</td>
              <td id="visibility">None</td>
            </tr>
          </tbody>
        </table>
       </div>
       <div class="panel-footer">
         <div class="form-inline">
           <label class="mr-sm-2 mb-0">Location search by name</label>
           <input id="Location-weather" type="text" class="form-control mr-sm-2 mb-2 mb-sm-0">
           <button id="search-weather" type="button" class="btn btn-default mt-2 mt-sm-0">Search</button>
           <button id="reset-weather" type="button" class="btn btn-default mt-2 mt-sm-0"><i class="fa fa-close"></i></button>
         </div>
       </div>
      </div>
      <div id="Forecast" class="panel panel-default">
       <div class="panel-heading" style="background:#0094ff;">
         <h4>Forecast</h4>
       </div>
       <div id="body-forecast" class="panel-body">
         <label class = "lblcls" style = "display:none;"><strong>Select a date</strong>
           <select id = "date-time">
           </select>
         </label>
         <table class="table table-striped">
          <thead></thead>
          <tbody>
            <tr>
              <td>Main</td>
              <td id="Main-forecast">None</td>
            </tr>
            <tr>
              <td>Description</td>
              <td id="descr-forecast">None</td>
            </tr>
            <tr>
              <td>Temp</td>
              <td id="temp-forecast">None</td>
            </tr>
            <tr >
              <td>Pressure</td>
              <td id="pressure-forecast">None</td>
            </tr>
            <tr >
              <td>Humidity</td>
              <td id="humidity-forecast">None</td>
            </tr>
            <tr>
              <td>Wind Speed</td>
              <td id="wind-Speed-forecast">None</td>
            </tr>
            <tr>
              <td>Wind deg</td>
              <td id="wind-deg-forecast">None</td>
            </tr>
          </tbody>
        </table>
       </div>
       <div class="panel-footer">
         <div class="form-inline">
           <label class="mr-sm-2 mb-0">Location search by name</label>
           <input id="Location-forecast" type="text" class="form-control mr-sm-2 mb-2 mb-sm-0" >
           <button id="search-forecast" type="button" class="btn btn-default mt-2 mt-sm-0">Search</button>
           <button id="reset-forecast" type="button" class="btn btn-default mt-2 mt-sm-0"><i class="fa fa-close"></i></button>
         </div>
       </div>
      </div>
    </div>
  </body>
</html>