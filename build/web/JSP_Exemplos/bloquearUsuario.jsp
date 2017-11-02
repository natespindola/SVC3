<%-- 
    Document   : bloquearUsuario
    Created on : Nov 28, 2016, 8:18:17 PM
    Author     : natalia
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bloquear Usuário</title>

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

<%     if ( null != session.getAttribute("Admin")){
%>

<body bgcolor="white">
<%@ page import="app.controller.UserController"%>
<%@ page import="app.model.entity.User" %>
<%@ page import="app.model.helper.ProfileEnum" %>
<%@ page import="java.util.Vector" %>

<! ------------------------------------------------------------>
<!--   se for o request inicial, mostrar somente o formulario -->

<%     if ( null == request.getParameterValues("bloquear")){
%>
<h3 class="container-fluid">Página de bloqueio de usuários.</h3>
    <smal class="container-fluid">Página de bloqueio de usuários</smal>
<form action="./bloquearUsuario.jsp" method="post" class="form-horizontal container-fluid">
    <div class="row">
    <div class="form-group col-xs-12 col-md-6" id="div_nome">
        <label for="nome">* Nome Completo: </label>
        <input type="text" class="form-control" id="nome" name="nome" required="true" placeholder="Entre com o nome completo do usuário a ser bloqueado...">
    </div>
    </div>
    <div class="row">
    <div class="form-group col-xs-12 col-md-6" id="div_documento">
        <label for="documento" id="tipoDocumento">* CPF: </label>
        <input type="text" class="form-control" id="documento" name="documento" required="true">
    </div>
    </div>
    </div>
    <div class="row">
    <a href="adminindex.jsp" class="btn btn-default col-xs-4 col-md-1" role="button"> Voltar</a>
    <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="bloquear" id="bloquear" value="bloquear">bloquear</button>
    </div>
</form>

<%       } else {
%>
<! ------------------------------------------------------------------->
<!--   se nao for o request inicial, acionar a transacao de negocio -->


<%
    String nome = request.getParameter("nome");
    String dirtyDocumento = request.getParameter("documento");
    String documento = dirtyDocumento.replaceAll("[^0-9]","");
    UserController tn = new UserController();
    User user = new User();
    user.setName(nome);
    user.setDocument(documento);

    Vector v = tn.bloquear(user);
    if ( v.isEmpty()) {
        // avisar usuario que transacao foi feita com sucesso
%>
<div class="col-md-offset-4 col-xs-offset-4 col-md-4 colxs-4">
    <h2>Usuário Bloqueado com Sucesso</h2>
</div>
<div class="row">
    <a href="./adminindex.jsp" class="btn btn-primary col-xs-4 col-md-1 col-md-offset-4 col-xs-offset-4" role="button"> Voltar</a>
</div>
<%     } else {%>
<h3 class="container-fluid" id="head">Usuário não encontrado</h3>
<form action="./bloquearUsuario.jsp" method="post" class="form-horizontal container-fluid">
    <div class="row">
    <div class="form-group col-xs-12 col-md-6" id="div_nome">
        <label for="nome">* Nome Completo: </label>
        <input type="text" class="form-control" id="nome" name="nome" required="true" placeholder="Entre com o nome completo do usuário a ser removido...">
    </div>
    </div>
    <div class="row">
    <div class="form-group col-xs-12 col-md-6" id="div_documento">
        <label for="documento" id="tipoDocumento">* CPF: </label>
        <input type="text" class="form-control" id="documento" name="documento" required="true">
    </div>
    </div>
    </div>
    <div class="row">
    <a href="./adminindex.jsp" class="btn btn-default col-xs-4 col-md-1" role="button"> Voltar</a>
    <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="bloquear" id="bloquear" value="bloquear">Bloquear</button>
    </div>
</form>
<%     } %>
<%}

}
else{
%>    
<meta http-equiv="refresh" content="1;./adminindex.jsp">
<%
}

%>
</body>
</html>

