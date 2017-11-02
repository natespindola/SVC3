<%@page import="app.model.entity.Book"%>
<%@page import="app.model.entity.Product"%>
<%@page import="app.model.entity.Adress"%>
<%@page import="app.model.entity.User"%>
<%@page import="app.model.entity.Order"%>
<%@page import="java.util.Vector"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-br">
<header>
    <title>Detalhes do pedido</title>
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


<%  
    String orderId = request.getParameter("orderId");
    Vector v = new Vector();
    
    if (orderId != null && !orderId.isEmpty()) {
        OrderController oc = new OrderController();
        v = oc.listarDetalhesPedidos(orderId);
    }
    else{
        out.println("O orderID " + orderId + " não existe.");
    }
%>

    <form class="form-inline" action="./detalhesPedidos.jsp" method="get">
        <input type="hidden" name="orderId" id="orderId" value="<%=(orderId == null) ? "" : orderId%>">  
    </form>
    
    <h1>Detalhes do pedido</h1> 
        
    <table BORDER = 2>
        <th>Usuario</th>
        <th>Endereço do usuario</th>
        <th>ID do produto</th>
        <th>Titulo do livro</th>
        <th>Autor do llivro</th>
              
         <% for (int i = 0; i<v.size(); i++) { 
            Vector v2 = (Vector) v.elementAt(i);
            User u = (User) v2.elementAt(0);
            Adress a = (Adress) v2.elementAt(1);
            Product p = (Product) v2.elementAt(2);
            Book b = (Book) v2.elementAt(3);
       %>
        <tr>
            <% out.println("<td>"+ u.getName() + "</td>");%>
            <% out.println("<td>"+ a.getAdress() + "</td>");%>
            <% out.println("<td>"+ p.getId() + "</td>");%>
            <% out.println("<td>"+ b.getTitle() + "</td>");%>
            <% out.println("<td>"+ b.getAuthor() + "</td>");%>
        </tr>
        <% } %>
        
    </table>
    
        
<form action="./verPedidos.jsp" method="post">
    <input type="submit" name="voltar" value="voltar" />
</form>

</body>

</html>