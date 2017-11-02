<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<header>
    <title>Cadastrar Endereco - Sebo VIrtual</title>
</header>

<body bgcolor="white">
<%@ page import="app.controller.AdressController"%>
<%@ page import="app.model.entity.Adress" %>
<%@ page import="app.model.entity.User" %>
<%@ page import="app.model.helper.ProfileEnum" %>

<! ------------------------------------------------------------>
<!--   se for o request inicial, mostrar somente o formulario -->

<%     if ( null == request.getParameterValues("registro")){
%>
<form action="./RegisterAdress.jsp" method="post">

    <table>
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
    <input type="submit" name="registro" value="registro" />
</form>

<%       } else {
%>
<! ------------------------------------------------------------------->
<!--   se nao for o request inicial, acionar a transacao de negocio -->


<%
    String zipCode = request.getParameter("zipCode");
    String adress = request.getParameter("adress");
    String number = request.getParameter("number");;
    String complement = request.getParameter("complement");
    String reference = request.getParameter("reference");
    
    
    
    AdressController tn = new AdressController();
    
    Adress t_adress = new Adress();
    t_adress.setZipCode(zipCode);
    t_adress.setAdress(adress);
    t_adress.setNumber(number);
    t_adress.setComplement(complement);
    t_adress.setReference(reference);
    if(null != session.getAttribute("user")){
        User user = new User();
        user = (User) session.getAttribute("user");
        String userId = String.valueOf(user.getId());
        t_adress.setUserId(userId);
       }

    if ( tn.AddAdress(t_adress)) {
        session.setAttribute("adress", t_adress);
        // avisar usuario que transacao foi feita com sucesso
%>
Cadastro realizado com sucesso!
<form action="./finalizarCompra.jsp" method="post">
    <input type="submit" name="prosseguir" value="Prosseguir" />
    <input type="hidden" name="adress_id" value="<% out.println(t_adress.getId()); %> "/>
    <% User user = new User();
        user = (User) session.getAttribute("user");%>
    <input type="hidden" name="user_id" value="<% out.println(user.getId()); %> "/>
    <input type="hidden" name="inicio" value="true"/>
</form>
<%     } else {
%>
Erro ao registrar endereco
<form action="./RegisterAdress.jsp" method="post">
    <input type="submit" name="retry" value="Repetir" />
</form>
<%     }
}
%>
</body>

</html>

