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
<%@ page import="app.controller.UserController"%>
<%@ page import="app.model.entity.User" %>
<%@ page import="app.model.helper.ProfileEnum" %>
<%@ page import="java.util.Vector" %>

<! ------------------------------------------------------------>
<!--   se for o request inicial, mostrar somente o formulario -->

<%     if ( null == request.getParameterValues("incluir")){
%>
<h3 class="container-fluid">Dados cadastrais.</h3>
<smal class="container-fluid">O mínimo para o acharmos.</smal>
<form action="./insertBuyer.jsp" method="post" class="form-horizontal container-fluid">
    <div class="row">
        <div class="form-group col-xs-12 col-md-6" id="div_nome">
            <label for="nome">* Nome Completo: </label>
            <input type="text" class="form-control" id="nome" name="nome" required="true" placeholder="Entre com seu nome completo...">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-12 col-md-6" id="div_email">
            <label for="email">* Email: </label>
            <input type="email" class="form-control" id="email" name="email" required="true" placeholder="Entre com seu email...">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-12 col-md-6" id="div_documento">
            <label for="documento" id="tipoDocumento">* CPF: </label>
            <input type="text" class="form-control" id="documento" name="documento" required="true">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-12 col-md-6">
            <label for="celPhone">Celular: </label>
            <input type="text" class="form-control" id="celPhone" name="celular">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-12 col-md-6">
            <label for="phone">Telefone: </label>
            <input type="text" class="form-control" id="phone" name="telefone">
        </div>
    </div>
    <div class="row">
        <a href="./index.jsp" class="btn btn-default col-xs-4 col-md-1" role="button"> Voltar</a>
        <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="incluir" id="incluir" value="incluir">Cadastrar</button>
    </div>
</form>

<%       } else {
%>
<! ------------------------------------------------------------------->
<!--   se nao for o request inicial, acionar a transacao de negocio -->


<%
    String nome = request.getParameter("nome");
    String email = request.getParameter("email");
    String dirtyDocumento = request.getParameter("documento");
    String dirtyCelular = request.getParameter("celular");
    String dirtyTelefone = request.getParameter("telefone");
    String telefone = dirtyTelefone.replaceAll("[^0-9]","");
    String celular = dirtyCelular.replaceAll("[^0-9]","");
    String documento = dirtyDocumento.replaceAll("[^0-9]","");
    ProfileEnum profile = ProfileEnum.FISICA;
    UserController tn = new UserController();
    User user = new User();
    user.setEmail(email);
    user.setName(nome);
    user.setProfile(profile);
    user.setDocument(documento);
    user.setPhone(telefone);
    user.setCelPhone(celular);

    Vector v = tn.incluir(user, "",false);
    if ( v.isEmpty() ) {
%>

<form action="./finalizarCompra.jsp" method="post">
    <input type="submit" name="prosseguir" value="Cadastrar Endereco" />
    <input type="hidden" name="user_id" value="<% out.println(user.getId()); %> "/>
</form>
<%     } else {%>
<h3 class="container-fluid" id="head">Cadastro com erro</h3>
<form action="./insertBuyer.jsp" method="post" class="form-horizontal container-fluid">
    <div class="row">
        <div class="form-group col-xs-12 col-md-6" id="div_nome">
            <label for="nome">* Nome Completo: </label>
            <input type="text" class="form-control" id="nome" name="nome" required="true" placeholder="Entre com seu nome completo...">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-12 col-md-6" id="div_email">
            <label for="email">* Email: </label>
            <input type="email" class="form-control" id="email" name="email" required="true" placeholder="Entre com seu email...">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-12 col-md-6" id="div_documento">
            <label for="documento" id="tipoDocumento">* CPF: </label>
            <input type="text" class="form-control" id="documento" name="documento" required="true">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-12 col-md-6">
            <label for="celPhone">Celular: </label>
            <input type="text" class="form-control" id="celPhone" name="celular">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-xs-12 col-md-6">
            <label for="phone">Telefone: </label>
            <input type="text" class="form-control" id="phone" name="telefone">
        </div>
    </div>
    <div class="row">
        <a href="./index.jsp" class="btn btn-default col-xs-4 col-md-1" role="button"> Voltar</a>
        <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="incluir" id="incluir" value="incluir">Cadastrar</button>
    </div>
</form>
<script>
    $("#nome").val("<%=nome%>");
    $("#email").val("<%=email%>");
    $("#phone").val("<%=dirtyTelefone%>");
    $("#documento").val("<%=dirtyDocumento%>");
    $("#celPhone").val("<%=dirtyCelular%>");
    <% for(int i = 0; i< v.size(); i++) {
            if(v.elementAt(i).equals("usuario")){ %>
    $("#head").html("Usuário já cadastrado no nosso sistema, use o esqueci minha senha");
    <%
        }
         else if(v.elementAt(i).equals("servidor")){ %>
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

        $('#phone').mask('(00) 0000-0000');
        $('#celPhone').mask('(00) 00000-0000');
        $("#documento").mask('000.000.000-00', {reverse: true});
        });
</script>
</html>