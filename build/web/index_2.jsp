<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<% 
    String userLogin = request.getParameter("userLogin");
    pageContext.setAttribute("userLogin", userLogin);
%>

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
<H1 align="center">Sistema de Verificação de Carros de Emergência</H2>
<H2 align="left">Índice</H2>

<%--<c:set var = "userLogin2" scope = "session" value = "${userLogin}"/>--%>
<c:if test="${userLogin == 'adm'}">
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
</c:if>
<c:if test="${userLogin == 'enf'}">
    <div id="enfermeiroDiv"> 
        Enfermeiro:<br/>
        <a href="consultarStatusCarro.jsp">Consultar Status de Carro</a><br/>
        <a href="conferenciaItens.jsp">Conferência de Itens</a><br/>
        <a href="logout.jsp">Logout</a><br/>
    </div>
</c:if>
<c:if test="${userLogin == 'far'}">
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
</c:if>

</body>
</html>


<!--<script>
alert("oi Natalha, tudo bem?");
</script>-->

<!--<script type="text/javascript">
//<![CDATA[
function test(){
    alert("oi natalha 2!");
}
//]]>
</script>

<script>
test();
</script>-->