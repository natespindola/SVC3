<%@page import="app.model.entity.User"%>
<%@page import="app.model.entity.Order"%>
<%@page import="java.util.Vector"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-br">
<header>
    <title>Pedidos</title>
</header>
    
<style>    
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 80%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>

<body bgcolor="white">
<%@ page import="app.controller.OrderController"%>

<! ------------------------------------------------------------>
<% OrderController oc = new OrderController();
    Vector v = oc.listarTodos();
%>

    <h1>Lista de Pedidos</h1> 
    
    <table BORDER = 2>
        <th>ID do pedido</th>
        <th>Pedido feito por</th>
        <th>Pedido feito em</th>
        <th>Pedido atualizado</th>
        <th>Detalhes</th>
        
        <% for (int i = 0; i<v.size(); i++) { 
            Vector v2 = (Vector) v.elementAt(i);
            Order o = (Order) v2.elementAt(0);
            User u = (User) v2.elementAt(1);
       %>
        <tr>
            <% out.println("<td>"+ o.getId() + "</td>");%>
            <% out.println("<td>"+ u.getName() + "</td>");%>
            <% out.println("<td>"+ o.getCreatedAt() + "</td>");%>
            <% out.println("<td>"+ o.getUpdatedAt() + "</td>");%>
            <th><a href="./detalhesPedidos.jsp?orderId=<%=o.getId()%>" class="btn btn-default btn-xs" role="button"><p class="text-center" style="color: #85bfac">detalhes</p></a></th>         
        </tr>
                <% } %> 
    </table>
    

    <form action="./adminindex.jsp" method="post">
        <input type="submit" name="voltar" value="voltar" />
    </form>

</body>

</html>

