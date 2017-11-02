<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Minha Conta - Sebo Virtual</title>

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
<%@ page import="app.controller.UserController"%>
<%@ page import="app.model.entity.User" %>
<%@ page import="app.model.helper.ProfileEnum" %>
<%@ page import="java.util.Vector" %>

<! ------------------------------------------------------------>
<!--   se for o request inicial, mostrar somente o formulario -->

<%     if ( null == request.getParameterValues("incluir")){
        User userSession = (User) session.getAttribute("User");
%>
<h3 class="container-fluid">Seus dados</h3>
<form action="./updateUser.jsp" method="post" class="form-horizontal container-fluid">
    <div class="row">
        <div class="form-group has-feedback col-xs-12 col-md-6" id="div_nome">
            <label for="nome">* Nome Completo: </label>
            <input type="text" class="form-control" id="nome" name="nome" required="true" value="<%=userSession.getName()%>" placeholder="Entre com seu nome completo...">
        </div>
    </div>
    <div class="row">
        <div class="form-group has-feedback col-xs-12 col-md-6" id="div_documento">
            <label for="documento" id="tipoDocumento">* CPF: </label>
            <input type="text" class="form-control" value="<%=userSession.getDocument()%>" id="documento" name="documento" required="true">
        </div>
    </div>
    <div class="row">
        <div class="form-group has-feedback col-xs-12 col-md-6">
            <label for="celPhone">Celular: </label>
            <input type="text" class="form-control" id="celPhone" value="<%=userSession.getCelPhone()%>" name="celular">
        </div>
    </div>
    <div class="row">
        <div class="form-group has-feedback col-xs-12 col-md-6">
            <label for="phone">Telefone: </label>
            <input type="text" value="<%=userSession.getPhone()%>" class="form-control" id="phone" name="telefone">
        </div>
    </div>
    <div class="row">
        <div class="form-group has-feedback col-xs-12 col-md-6">
            <label for="password">Senha: </label>
            <input type="password" class="form-control" id="password" name="password">
            <span id="helpBlock" class="help-block">A senha deve conter 8 caracteres sendo pelo menos uma letra maíuscula uma letra minúscula e um número.</span>
        </div>
    </div>
    <div class="row">
        <div class="form-group has-feedback col-xs-12 col-md-6" id="confirm_password">
            <label for="confirm_password">Confirma Senha: </label>
            <input type="password" class="form-control" id="confirm_password" name="confirm_password">
        </div>
    </div>
    <div class="row">
        <a href="./index.jsp" class="btn btn-default col-xs-4 col-md-1" role="button"> Voltar</a>
        <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="incluir" id="incluir" value="incluir">Atualizar</button>
    </div>
</form>

<%       } else {
%>
<! ------------------------------------------------------------------->
<!--   se nao for o request inicial, acionar a transacao de negocio -->


<%
    User userSession = (User) session.getAttribute("User");

    String nome = request.getParameter("nome");
    String dirtyDocumento = request.getParameter("documento");
    String dirtyCelular = request.getParameter("celular");
    String dirtyTelefone = request.getParameter("telefone");
    String telefone = dirtyTelefone.replaceAll("[^0-9]","");
    String celular = dirtyCelular.replaceAll("[^0-9]","");
    String documento = dirtyDocumento.replaceAll("[^0-9]","");
    String senha = request.getParameter("password");
    String confirmasenha = request.getParameter("confirm_password");
    UserController tn = new UserController();
    User user = new User();
    user.setEmail(userSession.getEmail());
    user.setName(nome);
    user.setDocument(documento);
    user.setPhone(telefone);
    user.setCelPhone(celular);
    user.setPassword(senha);
    user.setProfile(userSession.getProfile() == ProfileEnum.FISICA.name()? ProfileEnum.FISICA: ProfileEnum.JURIDICA);

    Vector v = tn.atualizar(userSession.getId(), user, confirmasenha);
    if ( v.isEmpty()) {
        User updatedUser = tn.getUserFromEmail(user.getEmail());
        session.setAttribute("User", updatedUser);
        // avisar usuario que transacao foi feita com sucesso
%>
<div class="row">
    <div class="col-md-offset-4 col-xs-offset-4 col-md-4 colxs-4">
        <h2>Dados atualizados com sucesso</h2>
    </div>
</div>
<div class="row">
    <a href="./index.jsp" class="btn btn-primary col-xs-4 col-md-1 col-md-offset-4 col-xs-offset-4" role="button"> Voltar</a>
</div>
<%     } else {%>
<h3 class="container-fluid" id="head">Dados com erro</h3>
<form action="./updateUser.jsp" method="post" class="form-horizontal container-fluid">
    <div class="row">
        <div class="form-group has-feedback col-xs-12 col-md-6" id="div_nome">
            <label for="nome">* Nome Completo: </label>
            <input type="text" class="form-control" id="nome" name="nome" required="true" placeholder="Entre com seu nome completo...">
        </div>
    </div>
    <div class="row">
        <div class="form-group has-feedback col-xs-12 col-md-6" id="div_documento">
            <label for="documento" id="tipoDocumento">* CPF: </label>
            <input type="text" class="form-control" id="documento" name="documento" required="true">
        </div>
    </div>
    <div class="row">
        <div class="form-group has-feedback col-xs-12 col-md-6">
            <label for="celPhone">Celular: </label>
            <input type="text" class="form-control" id="celPhone" name="celular">
        </div>
    </div>
    <div class="row">
        <div class="form-group has-feedback col-xs-12 col-md-6">
            <label for="phone">Telefone: </label>
            <input type="text" class="form-control" id="phone" name="telefone">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-12 has-feedback col-md-6" id="div_password">
            <label for="password">Senha: </label>
            <input type="password" class="form-control" id="password" name="password">
            <span id="helpBlock" class="help-block">A senha deve conter 8 caracteres sendo pelo menos uma letra maíuscula uma letra minúscula e um número.</span>
        </div>
    </div>
    <div class="row">
        <div class="form-group has-feedback col-xs-12 col-md-6" id="div_confirm_password">
            <label for="confirm_password">Confirma Senha: </label>
            <input type="password" class="form-control" id="confirm_password" name="confirm_password">
        </div>
    </div>
    <div class="row">
        <a href="./index.jsp" class="btn btn-default col-xs-4 col-md-1" role="button"> Voltar</a>
        <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="incluir" id="incluir" value="incluir">Atualizar</button>
    </div>
</form>
<script>
    $("#nome").val("<%=nome%>");
    $("#phone").val("<%=dirtyTelefone%>");
    $("#documento").val("<%=dirtyDocumento%>");
    $("#celPhone").val("<%=dirtyCelular%>");
    $("#password").val("<%=senha%>");
    $("#confirm_password").val("<%=confirmasenha%>");
    <% for(int i = 0; i< v.size(); i++) {
         if(v.elementAt(i).equals("servidor")){ %>
    $("#head").html("Erro no servidor. :-(");
    <% } else { %>
    $("<%=v.elementAt(i)%>").addClass("has-error");
    $("<%=v.elementAt(i)%>").append('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
    <% }
        } %>
</script>
<%     } %>
<%}
%>
</body>
<script>
    $(document).ready(function () {
        <%
            User userSession = (User) session.getAttribute("User");
            int perfil = userSession.getProfile() == ProfileEnum.FISICA.name() ? 0 : 1;
            if(perfil ==0){
        %>
        var text = "*CPF";
        $("#documento").mask('000.000.000-00', {reverse: true});
        <%
            }else {
        %>
        $("#documento").mask('00.000.000/0000-00', {reverse: true});
        var text = "*CNPJ";
        <% } %>
        $('#phone').mask('(00) 0000-0000');
        $('#celPhone').mask('(00) 00000-0000');
        $("#tipoDocumento").html(text);
    });
</script>
</html>
