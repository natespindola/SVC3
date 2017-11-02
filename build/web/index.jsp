
<%@page import="app.controller.UserController"%>
<%@page import="app.model.entity.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <style>
        #UserAccess {
            position: absolute;
            width: 300px;
            height: 200px;
            z-index: 15;
            top: 50%;
            left: 50%;
            margin: -100px 0 0 -150px;
        }
        
        form  { display: table;      }
        p     { display: table-row;  }
        label { display: table-cell; }
        input { display: table-cell; }
    </style> 
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>SVC - Sistema de verificação de carros</title>

</head>
<body>
<H2 align="center">Sistema de Verificação de Carros de Emergência</H2>

<%   
if ( (null == request.getParameterValues("entrar")) & (null == session.getAttribute("LoggedOn")) ){
%>
<div id ="UserAccess">
    <form class="form-inline" action="./index.jsp" method="post">
      <table align="center">
        <tr>
          <td align="right">ID:</td>
          <td align="left"><input type="text" id="userLogin" name="userLogin"></td>
        </tr>
        <tr>
          <td align="right">Senha:</td>
          <td align="left"><input type="password" id="userPassword" name="userPassword"></td>
        </tr>
        <tr>
            <td><button align="right" type="submit" name="entrar" value="entrar" onclick="return tstPopUpError()">Login</button></td>
        </tr>
      </table>
        
    </form>
    
</div>

<%     } else {
        //User controller
        UserController tn = new UserController();
        User userObj = new User();
        if (null == session.getAttribute("LoggedOn")){
            String user = request.getParameter("userLogin");
            String senha = request.getParameter("userPassword");
            //verifica se o usuário existe
            if (tn.authenticateUser(user,senha)){
                //Enfermeiro
                //Farmaceutico
                //Administrador
                String userType = tn.getUserType(user);
                //Seta session attribute para validacao de tipo de usuario
                session.setAttribute("LoggedOn", userType);

%>
<meta http-equiv="refresh" content="0; ./index.jsp">
<%              }else{
%>
<form action="./index.jsp" method="post">
    <input type="submit" name="retry" value="Repetir" />
</form>
Usuário ou senha incorretos.
<%                  }
                }else{
        
        // entrar para a página de admin
%>

<H1 align="center">Sistema de Verificação de Carros de Emergência</H2>
<H2 align="left">Índice</H2>

<% 
                    if (null != session.getAttribute("LoggedOn")){
                        String userType = session.getAttribute("LoggedOn").toString();
                        if(userType.compareTo("Administrador") == 0){
                            %>
                            <div id="adminDiv"> 
                                Administrador:<br/>
                                <a href="cadastrarUsuario.jsp">Cadastrar Usuário</a><br/>
                                <a href="consultarUsuario.jsp">Consultar Cadastro de Usuário</a><br/>
                                <a href="editarUsuario.jsp">Editar Cadastro de Usuário</a><br/>
                                <a href="excluirUsuario.jsp">Excluir Cadastro de Usuário</a><br/>
                                <a href="cadastrarGrupo.jsp">Cadastrar Grupo</a><br/>
                                <a href="excluirGrupo.jsp">Excluir Cadastro de Grupo</a><br/>
                                <a href="logout.jsp">Logout</a><br/>
                            </div>
                            <%
                        }
                        else if(userType.compareTo("Enfermeiro") == 0){
                        %>
                        <div id="enfermeiroDiv"> 
                            Enfermeiro:<br/>
                            <a href="consultarStatusCarro.jsp">Consultar Status de Carro</a><br/>
                            <a href="conferenciaItens.jsp">Conferência de Itens</a><br/>
                            <a href="logout.jsp">Logout</a><br/>
                        </div>
                        <%
                        }
                        else if(userType.compareTo("Farmaceutico") == 0){
                        
                        %>
                        <div id="farmaceuticoDiv"> 
                            Farmaceutico:<br/>
                            <a href="consultarStatusCarro.jsp">Consultar Status dos Carros</a><br/>
                            <a href="cadastrarEtiqueta.jsp">Cadastrar Etiqueta</a><br/>
                            <a href="cadastrarItem.jsp">Cadastrar Item</a><br/>
                            <a href="consultarItem.jsp">Consultar Cadastro de Item</a><br/>
                            <a href="editarTipoItem.jsp">Editar Cadastro de Item</a><br/>
                            <a href="excluirItem.jsp">Excluir Cadastro de Item</a><br/>
                            <a href="cadastrarCarro.jsp">Cadastrar Carro</a><br/>
                            <a href="editarCarro.jsp">Editar Cadastro de Carro</a><br/>
                            <a href="excluirCarro.jsp">Excluir Cadastro de Carro</a><br/>
                            <a href="cadastrarListaItens.jsp">Cadastrar Lista de Itens</a><br/>
                            <a href="editarListaItens.jsp">Editar Cadastro de Lista de Itens</a><br/>
                            <a href="excluirListaItens.jsp">Excluir Cadastro de Lista de Itens</a><br/>
                            <a href="separacaoItens.jsp">Confirmar Separação de Itens</a><br/>
                            <a href="logout.jsp">Logout</a><br/>
                        </div>
                        <%
                        }
                    }
                }
        }
%>
</body>
</html>

<script type="text/javascript">
//<![CDATA[
function tstPopUpError(){
    var userLogin = document.getElementById("userLogin");
    if(userLogin.value === "erro"){
        alert("Usuário/Senha incorreto(s). Favor tentar novamente.");
        return false;
    }
    return true;
}
//]]>
</script>
