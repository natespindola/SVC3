<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Sebo Virtual</title>

  <!-- Bootstrap -->
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"><link rel="stylesheet" href="js/ui/jquery-ui.min.css">
  <script src="js/jquery-3.1.1.min.js"></script>
  <script src="js/autocomplete/jquery.autocomplete.js"></script>
  <script src="js/ui/jquery-ui.min.js"></script>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>
<body>
<div class="container-fluid">
<div class="row" style="height: 30px; background-color:#85bfac">
    <div class="col-sm-12"></div>
</div>
    <div class="row" style="background-color:#85bfac;">
        <div class="col-sm-1" ><a href="./insertUser.jsp" class="btn btn-default btn-xs" role="button"><p class="text-center" style="color: #85bfac">Sign Up</p></a></div>
        <div class="col-sm-8">
            <form class="form-inline" action="./index.jsp" method="post">
                <div class="form-group">
                    <label class="sr-only" for="emailLogin">Email address</label>
                    <input type="email" class="form-control input-sm" id="emailLogin" placeholder="Email" name="email">
                </div>
                <div class="form-group">
                    <label class="sr-only" for="passwordLogin">Password</label>
                    <input type="password" class="form-control input-sm" id="passwordLogin" placeholder="Password" name="password">
                </div>
                <button type="submit" class="btn btn-default btn-sm">Sign in</button>
            </form>
    </div>
    <div class="col-sm-1" ><button class="btn btn-default btn-sm"><i class="glyphicon glyphicon-shopping-cart text-center" style="font-size: 26px; color: #85bfac;"></i></button></div>
</div>
    <div class="row" style="height: 30px; background-color:#85bfac;">
        <div class="col-sm-12"></div>
    </div>
</div>
<%--<form action="javascript:;" method="get">--%>
<%--<h1>Hellô, world!</h1>--%>
<%--<p>hello</p>--%>
    <%--<div class="ui-widget">--%>
        <%--&lt;%&ndash;<label for="book">Your book: </label>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<input id="book">&ndash;%&gt;--%>
        <%--<input type="text" name="books" id="getWork"/>--%>
    <%--</div>--%>

    <%--<div class="ui-widget" style="margin-top:2em; font-family:Arial">--%>
        <%--Result:--%>
        <%--<div id="log" style="height: 200px; width: 300px; overflow: auto;" class="ui-widget-content"></div>--%>
    <%--</div>--%>
<%--<div><p>Click here to submit</p><input type="submit" value="Submit" id="btn"> </div>--%>
    <%--<div id = here></div>--%>
<%--</form >--%>
<%--<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->--%>
<%--<script >--%>


    <%--$(document).ready(function(){--%>

        <%--$("#getWork").devbridgeAutocomplete({--%>
            <%--serviceUrl: function () {--%>
                <%--$.ajax({--%>
                    <%--method: "GET",--%>
                    <%--url: "https://www.googleapis.com/books/v1/volumes",--%>
                    <%--data: {--%>
                        <%--q: $("#getWork").val(),--%>
                        <%--maxResults: 10,--%>
                        <%--orderBy: "relevance",--%>
                        <%--projection: "lite"--%>
                    <%--},--%>
                    <%--dataType: "json",--%>
                    <%--success: function (data) {--%>
                        <%--var suggestions = {value: '', data: ''};--%>
                        <%--var result = [];--%>
                        <%--data.items.forEach(function (va) {--%>
                            <%--suggestions.value = va.volumeInfo.title;--%>
                            <%--suggestions.data = va.id;--%>
                            <%--result.push({value: va.volumeInfo.title, data: va.id});--%>
                        <%--});--%>
                        <%--return result;--%>
                    <%--}--%>
                <%--}).fail(function () {--%>
                    <%--alert("fail");--%>
                <%--});--%>
            <%--},--%>
            <%--preventBadQueries: false,--%>
<%--//            ajaxSettings: {--%>
<%--//                method: "GET",--%>
<%--//                url: "https://www.googleapis.com/books/v1/volumes",--%>
<%--//                data: {--%>
<%--//                    q: request.term,--%>
<%--//                    maxResults: 10,--%>
<%--//                    orderBy: "relevance",--%>
<%--//                    projection: "lite"--%>
<%--////                },--%>
<%--//                dataType: "json"--%>
<%--//            },--%>
            <%--minChars: 5,--%>
<%--//            paramName: 'q',--%>
            <%--onSearchComplete: function (query, suggestions) {--%>
                <%--console.log(suggestions);--%>
            <%--},--%>
            <%--lookup:--%>

        <%--});--%>
        <%--$(function() {--%>
            <%--function log( message ) {--%>
                <%--$( "<div>" ).text( message ).prependTo( "#log" );--%>
                <%--$( "#log" ).scrollTop( 0 );--%>
            <%--}--%>

            <%--$( "#book" ).autocomplete({--%>
                <%--source: function( request, response ) {--%>
                    <%--$.ajax({--%>
                        <%--method: "GET",--%>
                        <%--url: "https://www.googleapis.com/books/v1/volumes",--%>
                        <%--data: {q: request.term,--%>
                               <%--maxResults: 10,--%>
                            <%--orderBy: "relevance",--%>
                            <%--projection: "lite"},--%>
                        <%--dataType: "json",--%>
                        <%--success: function( data ) {--%>
                            <%--var retorno = '';--%>
                            <%--var id = '';--%>
                            <%--var result = [];--%>
                            <%--data.items.forEach (function(va){--%>
                                <%--retorno = va.volumeInfo.title;--%>
                                <%--result.push(retorno);--%>
                            <%--});--%>
                            <%--response(result);--%>
                        <%--}}).fail(function () {--%>
                        <%--alert(request);--%>
                    <%--});--%>
                <%--},--%>
                <%--minLength: 5,--%>
                <%--select: function( event, ui ) {--%>
                    <%--console.log(data);--%>
                    <%--log( ui.item ?--%>
                    <%--"Selected: " + ui.item.label :--%>
                    <%--"Nothing selected, input was " + this.value);--%>
                <%--},--%>
                <%--open: function() {--%>
                    <%--$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );--%>
                <%--},--%>
                <%--close: function() {--%>
                    <%--$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>