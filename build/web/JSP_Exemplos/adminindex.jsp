<%-- 
    Document   : adminindex
    Created on : 13/11/2016, 20:29:47
    Author     : Sebastian
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br" >
<header>
    <title>Sebo Virtual - Administrador</title>
</header>
<body>
<%@ page import="app.model.entity.AdminUser" %>
<%@ page import="app.controller.AdminController" %>

<%   
if ( (null == request.getParameterValues("entrar")) & (null == session.getAttribute("Admin")) ){
%>
<form action="./adminindex.jsp" method="post">

<div class="container-fluid">
<div class="row" style="height: 30px; background-color:#85bfac">
    <div class="col-sm-12"></div>
</div>
    <div class="row" style="background-color:#85bfac;">
        <div class="col-sm-8">
            <form class="form-inline" action="./adminindex.jsp" method="post">
                <div class="form-group">
                    <label class="sr-only" for="AdminUserLogin">User</label>
                    <input type="user" class="form-control input-sm" id="User" placeholder="Admin User" name="user">
                </div>
                <div class="form-group">
                    <label class="sr-only" for="passwordLogin">Password</label>
                    <input type="password" class="form-control input-sm" id="passwordLogin" placeholder="Password" name="password">
                </div>
            <input type="submit" name="entrar" value="entrar" />
            
            <div class="row" style="height: 30px; background-color:#85bfac">
                <div class="col-sm-12"></div>
            </div>
            </form>
            <form action="./index.jsp" method="post">
            <input type="submit" name="sair" value="Home" />
            </form>  
<%     } else {

        AdminController tn = new AdminController();
        AdminUser adminuser = new AdminUser();
        if (null == session.getAttribute( "Admin")){
            String user = request.getParameter("user");
            String senha = request.getParameter("password");        
            adminuser.setUser(user);
            adminuser.setPassword(senha);
            if (tn.isAdminUserRegistered(adminuser)){
                session.setAttribute("Admin", adminuser);

%>
<meta http-equiv="refresh" content="0; ./adminindex.jsp">

<%              }else{
%>
<form action="./adminindex.jsp" method="post">
    <input type="submit" name="retry" value="Repetir" />
</form>
Usuário ou senha incorretos.
<%                  }
                } else{
        
        // entrar para a página de admin
%>

Bem vindo!
    <form action="./adminlogout.jsp" method="post">
    <input type="submit" name="sair" value="Sair" />
    
    </form>

    <div class="row" style="height: 30px; background-color:#85bfac">
        <div class="col-sm-12"></div>
    </div>

        <div class="form-group">   
            
            <form action="./logs.jsp" method="post">
                <input type="submit" name="logs" value="Ver Logs" />
            </form>
        </div>
        <div class="form-group">
            <form action="./telaErro.jsp" method="post">
                <input type="submit" name="errologs" value="Ver logs de erro" />
            </form>
        </div>
        <div class="form-group">
            <form action="./usuarios.jsp" method="post">
                <input type="submit" name="usuarios" value="Ver Usuários registrados" />
            </form>
        </div>
        <div class="form-group">
            <form action="./searchBook.jsp" method="post">
                <input type="submit" name="livros" value="Ver Livros registrados" />
            </form>
        </div>
        <div class="form-group">
            <form action="./searchProduct.jsp" method="post">
                <input type="submit" name="produtos" value="Ver Produtos registrados" />
            </form>
        </div>
        <div class="form-group">
            <form action="./verPedidos.jsp" method="post">
                <input type="submit" name="pedidos" value="Ver Pedidos" />
            </form>
        </div>
        <div class="form-group">
            <form action="./removerUsuario.jsp" method="post">
                <input type="submit" name="remover_usuario" value="Remover Usuario" />
            </form>
        </div>
        <div class="form-group">
            <form action="./bloquearUsuario.jsp" method="post">
                <input type="submit" name="bloquear_usuario" value="Bloquear Usuario" />
            </form>
        </div>
        <div class="form-group">
            <form action="./usuariosBloqueados.jsp" method="post">
                <input type="submit" name="usuarios_bloqueados" value="Ver Usuarios Bloqueados" />
            </form>
        </div>


<%
 
                }
      
    }
%>

    </div>
</div>
    <div class="row" style="height: 30px; background-color:#85bfac;">
        <div class="col-sm-12"></div>
    </div>
</div>   
</body>
</html>
