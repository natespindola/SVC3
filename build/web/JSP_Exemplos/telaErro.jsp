<%-- 
    Document   : telaErro
    Created on : Nov 22, 2016, 11:51:22 AM
    Author     : Guilherme
--%>

<%@page import="app.model.entity.ErrorLog"%>
<%@page import="java.util.Vector"%>
<%@page import="app.controller.ErrorLogController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> Erros do Sistema:  </h1>
        
<%        ErrorLogController el = new ErrorLogController();
          Vector v = el.buscar();
          if(v.isEmpty()){
%>
        <h3>Nenhum erro foi encontrado</h3>
        <% } else{
%>
        <table border=1 style="width:100%">
  <tr>
    <th>ID</th>
    <th>ErrorTrace</th> 
    <th>Controller</th>
    <th>Action</th>
    <th>Criado em</th>
  </tr>
  <% for(int i=0;i<v.size();i++){
                ErrorLog er = (ErrorLog) v.get(i);
  %>
  <tr>
    <td align="center"><% out.println(er.getId());%></td>
    <td align="center" overflow="scroll"><% out.println(er.getErrorTrace());%></td>
    <td align="center"><% out.println(er.getController());%></td>
    <td align="center"><% out.println(er.getAction());%></td>
    <td align="center"><% out.println(er.getCreatedAt());%></td>
  </tr>
  <% }%>
</table>
        <% }%>
    </body>
</html>
