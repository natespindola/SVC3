<%-- 
    Document   : MyProducts
    Created on : 27/11/2016, 17:46:17
    Author     : Cauê Muriano
--%>
<%@ page import="app.controller.UserController"%>
<%@ page import="app.model.dao.UserDao"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="core.Transacao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MyProducts</title>
    </head>
    <form action="./MyProducts.jsp" method="post">
</form>
    <body>
        <h1>Bem vindo, <% out.print(session.getAttribute("User"));%> </h1>
        
        <%  UserController myproducts = new UserController();
    String[] MyProductsArray;
    UserController UC = new UserController();
    MyProductsArray = UC.myproducts();
%>
       
<TABLE BORDER=1>
    <thead>
    <tr>
        <th>Nome:</th>
        <th>Quantidade:</th>
        <th>Título:</th>
        <th>Preço:</th>
        <th>Criado em:</th>
        <th>Última alteração:</th>
    </tr>
    </thead>
    <tbody>
        <%  
            int i = 0;
            while (MyProductsArray[i] != null) {
        %>
    <TR> <! Abre a segunda linha da tabela>
    <%      if (MyProductsArray[i] != null){
                out.println("<td>" + MyProductsArray[i] + "</td>");
                out.println("<td>" + MyProductsArray[i+=1] + "</td>");
                out.println("<td>" + MyProductsArray[i+=1] + "</td>");
                out.println("<td>" + MyProductsArray[i+=1] + "</td>");
                out.println("<td>" + MyProductsArray[i+=1] + "</td>");
                out.println("<td>" + MyProductsArray[i+=1] + "</td>");
                i+=1;
                }// if
    %>      
            </TR> <! Encerra a Segunda linha da tabela>
<%          }
        
%>
        
    </tbody>   
</TABLE>
    </body>
</html>
