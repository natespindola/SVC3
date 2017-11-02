

<%@page import="app.controller.UserController"%>
<%@page import="app.model.entity.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="app.controller.ProductController"%>
<%@ page import="app.model.entity.Pedido" %>
<%@ page import="app.model.dao.ProductDao"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="core.Transacao"%>
<html lang="pt-br">
<header>
    <title>Usuarios Bloqueados</title>
</header>
<body bgcolor="white">
    
<CENTER><FONT COLOR=BLUE SIZE=6>Lista de Usuarios Bloqueados</FONT></CENTER>

<form action="./usuariosBloqueados.jsp" method="post">
</form> 



<%  if (session.getAttribute("Admin") != null ){ 
%>
<! ------------------------------------------------------------>

<%  
    UserController u = new UserController();

    ArrayList<User> user_array;
    user_array = u. buscarBloqueados();
    if (user_array.isEmpty()){
%>
    <CENTER><FONT COLOR=RED SIZE=4>Não há Usuários Bloqueados</FONT></CENTER>
      
<%       } else {
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
                <th>Desbloquear: </th>
            </TR> <! Fecha a primeira linha da tabela>
        </thead>
        <tbody>
        <%  
            for (User usuario :
         user_array ) {
        %>
    <TR> <! Abre a segunda linha da tabela>
    
                <th><%=usuario.getId()%></th>
                <th><%=usuario.getName()%></th>
                <th><%=usuario.getEmail()%></th>
                <th><%=usuario.getProfile()%></th>
                <th><%=usuario.getDocument()%></th>
                <th><%=usuario.getCelPhone()%></th>
                <th><%=usuario.getPhone()%></th>
                <th><%=usuario.isBlocked()%></th>
                <th><%=usuario.isActivated()%></th>
                <th><%=usuario.isExcluded()%></th>
                <th><%=usuario.getCreatedAt()%></th>
                <th><%=usuario.getUpdatedAt()%></th>
                <th><a href="./desbloquearUsuario.jsp?userId=<%=usuario.getId()%>" class="btn btn-default btn-xs" role="button"><p class="text-center" style="color: #85bfac">+</p></a></th>
        
            </TR> <! Encerra a Segunda linha da tabela>
<%         } //while
        } //else
%>
<div class="row">
    <a href="./adminindex.jsp" class="btn btn-primary col-xs-4 col-md-1 col-md-offset-4 col-xs-offset-4" role="button"> Voltar</a>
</div>

<%
    } 
%>
        
    </tbody>
</TABLE>

</body>





    
</html>

