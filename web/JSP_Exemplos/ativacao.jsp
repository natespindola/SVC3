<%@ page import="app.controller.UserController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Sign Up - Sebo Virtual</title>

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"><link rel="stylesheet" href="js/ui/jquery-ui.min.css">
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/jquery.mask.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>

<body bgcolor="white">
<%
    UserController user = new UserController();
    long userId = Long.parseLong(request.getParameter("userId"));
    String hash = request.getParameter("user");
    if(user.activateUser(userId,hash)){
%>
<h3>
    Parabéns clique aqui para acessar nosso site.
    <a href="./index.jsp">Página inicial</a>
</h3>
<% }else{ %>
    <h1>não somos tão inseguros como você imagianva né. </h1>
<% } %>
</body>
</html>
