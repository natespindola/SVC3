<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Desbloquear Usuario</title>

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
    
<body bgcolor="white">
<%@ page import="app.controller.BookController"%>
<%@ page import="app.controller.ProductController"%>
<%@ page import="app.controller.UserController"%>
<%@ page import="app.model.entity.Product" %>
<%@ page import="app.model.entity.User" %>
<%@ page import="app.model.entity.ProductPrice" %>
<%@ page import="app.model.entity.ProductInfos" %>
<%@ page import="app.model.helper.PeriodicityTypeEnum" %>
<%@ page import="app.model.helper.PriceTypeEnum" %>
<%@ page import="app.model.core.AbstractDateClass" %>
<%@ page import="java.util.ArrayList" %>


<%
    
    long userId = Long.parseLong(request.getParameter("userId"));
    User user = new User();
    UserController uc = new UserController();
    boolean desbloquear;
    desbloquear = uc.desbloquearUsuario(userId);
    if (desbloquear = true){
 %>
<CENTER><FONT COLOR=GREEN SIZE=4>Usuário Desbloqueado com Sucesso</FONT></CENTER>



<div class="row">
    <a href="./usuariosBloqueados.jsp" class="btn btn-primary col-xs-4 col-md-1 col-md-offset-4 col-xs-offset-4" role="button"> Voltar</a>
</div> 
 
 <% } else {
%>

<CENTER><FONT COLOR=RED SIZE=4>Falha ao Desbloquear o Usuario</FONT></CENTER>


<div class="row">
    <a href="./usuariosBloqueados.jsp" class="btn btn-primary col-xs-4 col-md-1 col-md-offset-4 col-xs-offset-4" role="button"> Voltar</a>
</div> 


<% }
%>





</body>
</html>

