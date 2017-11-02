<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<header>
    <title>Inserir Usuário</title>
</header>

<body bgcolor="white">
<%@ page import="app.controller.UserController"%>
<%@ page import="app.model.entity.User" %>
<%@ page import="app.model.helper.ProfileEnum" %>

<! ------------------------------------------------------------>
<!--   se for o request inicial, mostrar somente o formulario -->

<%     if ( null == request.getParameterValues("incluir")){
%>
<form action="./insertUser.jsp" method="post">

    <select name="perfil" id="perfil">
        <option value="0" selected="true">Física
        </option>
        <option value="1">Jurídica
        </option>

    </select>
    <table>
        <tr>
            <td>*Nome</td>
            <td><input type="text" name="nome" required="true"/>
        </tr>
        <tr>
            <td>*Email</td>
            <td><input type="text" name="email" required="true"/>
        </tr>
        <tr>
            <td id="tipoDocumento">*CPF</td>
            <td><input type="text" name="documento" required="true" id="documento"/>
        </tr>
        <tr>
            <td>Celular</td>
            <td><input type="text" name="celular" id="celPhone"/>
        </tr>
        <tr>
            <td>Telefone</td>
            <td><input type="text" name="telefone" id="phone"/>
        </tr>
        <tr>
            <td>Senha</td>
            <td><input type="password" name="senha" />
        </tr>
    </table>
    <input type="submit" name="incluir" value="incluir" />
</form>

<%       } else {
%>
<! ------------------------------------------------------------------->
<!--   se nao for o request inicial, acionar a transacao de negocio -->


<%
    String nome = request.getParameter("nome");
    String email = request.getParameter("email");
    String documento = request.getParameter("documento");
    String celular = request.getParameter("celular");
    String telefone = request.getParameter("telefone");
    String senha = request.getParameter("senha");
    int perfil = Integer.parseInt(request.getParameter("perfil"));
    ProfileEnum profile;
    profile = perfil == 0 ? ProfileEnum.FISICA : ProfileEnum.JURIDICA;
    UserController tn = new UserController();
    User user = new User();
    user.setEmail(email);
    user.setName(nome);
    user.setProfile(profile);
    user.setDocument(documento);
    user.setPhone(telefone);
    user.setCelPhone(celular);
    user.setPassword(senha);

    if ( tn.incluir(user)) {
        // avisar usuario que transacao foi feita com sucesso
%>
Transação realizada com sucesso!
<form action="./index.jsp" method="post">
    <input type="submit" name="voltar" value="Voltar" />
</form>
<%     } else {
%>
Erro ao incluir usuário
<form action="./insertUser.jsp" method="post">
    <input type="submit" name="retry" value="Repetir" />
</form>
<%     }
}
%>
</body>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.mask.min.js"></script>
<script>
    $(document).ready(function () {

        $('#phone').mask('(00) 0000-0000');
        $('#celPhone').mask('(00) 00000-0000');
            $("#perfil").change(function () {
                var text;
                $("#documento").unmask();
                $("#documento").mask('000.000.000-00', {reverse: true});
                if ($("#perfil").val() == '0') {
                    text = "*CPF";
                    $("#documento").mask('000.000.000-00', {reverse: true});
                } else {
                    $("#documento").mask('00.000.000/0000-00', {reverse: true});
                    text = "*CNPJ";
                }
                $("#tipoDocumento").html(text);
            });


    });
</script>
</html>

