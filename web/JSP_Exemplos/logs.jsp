

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="app.controller.LogController"%>
<%@ page import="app.model.entity.Log" %>
<%@ page import="app.model.dao.LogDao"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="core.Transacao"%>
<html lang="pt-br">
<header>
    <title>Ver Log</title>
</header>
<form action="./logs.jsp" method="post">
</form>
<body bgcolor="white">

<% if (session.getAttribute("Admin") != null ){ %>
<div class="row" style="height: 30px; background-color:#85bfac">
    <div class="col-sm-12"></div>
</div>


<CENTER><FONT COLOR=BLUE SIZE=6>Logs</FONT></CENTER>
<form action="./adminindex.jsp" method="post">
<input type="submit" name="voltar" value="voltar" />
</form> 


<! ------------------------------------------------------------>
<!--   se for o request inicial, mostrar somente o formulario -->

<%  Transacao tr = new Transacao();

    LogController log = new LogController();
    Log[] LogArray;
    tr.beginReadOnly();
    LogArray = log.buscarLog();
    tr.commit();
    if (LogArray[0] == null){
%>
    <CENTER><FONT COLOR=RED SIZE=4>A lista de logs esta vazia!</FONT></CENTER>
      
<%       } else {
%>

<! ------------------------------------------------------------------->
<!--   se nao for o request inicial, acionar a transacao de negocio -->
<TABLE BORDER=1>
    <thead>
    <tr>
        <th>LogType</th>
        <th>RelashionshipId</th>
        <th>Message</th>
        <th>CreatedAt</th>
    </tr>
    </thead>
    <tbody>
        <%  
            int i = 0;
            while (LogArray[i] != null) {
        %>
    <TR> <! Abre a segunda linha da tabela>
    <%      if (LogArray[i] != null){
                out.println("<td>" + LogArray[i].getLogType() + "</td>");
                out.println("<td>" + LogArray[i].getRelationshipId() + "</td>");
                out.println("<td>" + LogArray[i].getMessage() + "</td>");
                out.println("<td>" + LogArray[i].getCreatedAt() + "</td>");
                i+=1;
                }// if
    %>      
            </TR> <! Encerra a Segunda linha da tabela>
<%          }
        }
    }  
%>
        
    </tbody>   
</TABLE>
</body>





    
</html>

