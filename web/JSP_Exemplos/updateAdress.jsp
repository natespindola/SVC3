<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<header>
    <title>Modificar Endereco - Sebo Virtual</title>
</header>

<body bgcolor="white">
<%@ page import="app.controller.AdressController"%>
<%@ page import="app.model.entity.Adress" %>
<%@ page import="app.model.helper.ProfileEnum" %>

<! ------------------------------------------------------------>
<!--   se for o request inicial, mostrar somente o formulario -->

<%     if ( null == request.getParameterValues("modificar")){
%>
<form action="./updateAdress.jsp" method="post">

    <table>
        <tr>
            <td>*Id</td>
            <td><input type="text" name="id" required="true"/>
        </tr>
        <tr>
            <td>*Tipo</td>
            <td><input type="text" name="type" required="true"/>
        </tr>
        <tr>
            <td>*Código Zip</td>
            <td><input type="text" name="zipCode" required="true"/>
        </tr>
        <tr>
            <td>*Endereco</td>
            <td><input type="text" name="adress" required="true"/>
        </tr>
        <tr>
            <td>Número</td>
            <td><input type="text" name="number"/>
        </tr>
        <tr>
            <td>Complemento</td>
            <td><input type="text" name="complement"/>
        </tr>
        <tr>
            <td>Referencia</td>
            <td><input type="text" name="reference"/>
        </tr>
    </table>
    <input type="submit" name="modificar" value="modificar" />
</form>

<%       } else {
%>
<! ------------------------------------------------------------------->
<!--   se nao for o request inicial, acionar a transacao de negocio -->


<%
    String id = request.getParameter("id");
    String type = request.getParameter("type");
    String zipCode = request.getParameter("zipCode");
    String adress = request.getParameter("adress");
    String number = request.getParameter("number");;
    String complement = request.getParameter("complement");
    String reference = request.getParameter("reference");
    AdressController tn = new AdressController();
    Adress t_adress = new Adress();
    t_adress.setAdressType(type);
    t_adress.setZipCode(zipCode);
    t_adress.setAdress(adress);
    t_adress.setNumber(number);
    t_adress.setComplement(complement);
    t_adress.setReference(reference);

    if ( tn.modificar(t_adress, id)) {
        // avisar usuario que transacao foi feita com sucesso
%>
Endereço modificado com sucesso!
<form action="./updateAdress.jsp" method="post">
    <input type="submit" name="voltar" value="Voltar" />
</form>
<%     } else {
%>
Erro ao registrar endereco
<form action="./updateAdress.jsp" method="post">
    <input type="submit" name="retry" value="Repetir" />
</form>
<%     }
}
%>
</body>

</html>

