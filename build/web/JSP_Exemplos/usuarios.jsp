<%-- 
    Document   : usuarios
    Created on : 22/11/2016, 12:28:00
    Author     : Sebastian
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="app.model.dao.UserDao"%>
<%@ page import="app.model.entity.User"%>
<%@ page import="app.controller.UserController"%>
<%@ page import= "java.util.ArrayList" %>
<%@ page import="core.Transacao"%>
<!DOCTYPE html>
<html lang="pt-br" >
<header>
    <title>Lista de Usuários</title>
</header>

<body>
    
<% if (session.getAttribute("Admin") != null ){ %>
<div class="row" style="height: 30px; background-color:#85bfac">
    <div class="col-sm-12"></div>
</div>


<CENTER><FONT COLOR=BLUE SIZE=6>Usuários</FONT></CENTER>
<form action="./adminindex.jsp" method="post">
<input type="submit" name="voltar" value="voltar" />
</form> 

<BR>

<%
    UserController controller = new UserController();
    ArrayList<User> UserArray = controller.EncontrarTodos();    
    if (UserArray.isEmpty()){
%>        
        <CENTER><FONT COLOR=RED SIZE=4>A lista de usuários esta vazia!</FONT></CENTER>
      
<%      }else{
%>        
    <TABLE BORDER=1> <! Inicia a tabela e coloca uma borda de espessura igual a 1>
        <thead>        
            <TR> <! Cria a primeira linha da tabela>
                <th>Id:</th>
                <th>Nome:</th> <! Aqui foi criada uma célula>
                <th>Email:</th>
                <th>Perfil: </th>
                <th>Documento:</th>
                <th>Celular: </th>
                <th>Telefone: </th>
                <th>Bloqueado: </th>
                <th>Ativado: </th>
                <th>Excluido: </th>
                <th>Criado em: </th>
                <th>Atualizado em: </th>
            </TR> <! Fecha a primeira linha da tabela>
        </thead>
        <tbody>
            <TR> <! Abre a segunda linha da tabela>
    <%      for (User user : UserArray){
                out.println("<td>" + user.getId() + "</td>");
                out.println("<td>" + user.getName() + "</td>");
                out.println("<td>" + user.getEmail() + "</td>");
                out.println("<td>" + user.getProfile() + "</td>");
                out.println("<td>" + user.getDocument() + "</td>");
                out.println("<td>" + user.getCelPhone() + "</td>");
                out.println("<td>" + user.getPhone() + "</td>");
                out.println("<td>" + user.isBlocked() + "</td>" );
                out.println("<td>" + user.isActivated() + "</td>");
                out.println("<td>" + user.isExcluded() + "</td>");
                out.println("<td>" + user.getCreatedAt() + "</td>");
                out.println("<td>" + user.getUpdatedAt() + "</td>");
                }// for
    %>      
            </TR> <! Encerra a Segunda linha da tabela>
    <%
            } //else
    } else{//sessão        
%>
<meta http-equiv="refresh" content="1;./adminindex.jsp">
<%    } %>
    </tbody>

</TABLE> <! Encerra a tabela>

</body>
</html>
