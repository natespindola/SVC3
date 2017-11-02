<%-- 
    Document   : consultarTipoItem
    Created on : 13/10/2017, 16:07:25
    Author     : Natalia
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>SVC - Consultar Tipo de Item</title>

</head>
    

<%@ page import="app.controller.TipoItemController"%>
<%@ page import="app.model.entity.TipoItem" %>
<%@ page import="app.model.helper.PeriodicityTypeEnum" %>
<%@ page import="app.model.helper.PriceTypeEnum" %>
<%@ page import="app.model.core.AbstractDateClass" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.NumberFormatException" %>
<%@ page import="java.math.BigInteger" %>

<%
if ( null == request.getParameterValues("consultar")){
%>
<div style="margin-bottom: 10px; padding: 4px">
<form class="form-inline" action="./consultarItem.jsp" method="get" accept-charset="UTF-8">
    <div class="form-group">
        <label>Nome do item: </label>
        <input type="text" class="form-control" id="NomeItem" name="NomeItem" required="true" placeholder="Insira o nome do item.">
    </div>
    <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="consultar" id="consultar" value="consultar">OK</button>
</form>
    <button onclick="goBack()">Cancelar</button>
    <script>
    function goBack() {
        window.history.go(-1);
    }
    </script>
</div>
<%
    }else{
        TipoItemController TIController = new TipoItemController();
        String NomeItem = request.getParameter("NomeItem");

        ArrayList<TipoItem> tipoItens = TIController.consultar(NomeItem);
    %>
<div class="table-responsive">
<table class="table table-bordered table-striped">
    <thead class="thead-default">
    <tr>
        <th>Nome</th>
        <th>Qtd Carrinho Adulto</th>
        <th>Qtd Carrinho Infantil</th>
        <th>Volume/Massa</th>
    </tr>
    </thead>
<%
    for (TipoItem tipoItem : tipoItens) {
                int TipoItemID = tipoItem.getTipoItemID();
                String NomeItemAtual =tipoItem.getNomeItem();
                int QtdAdultoAtual = tipoItem.getQtdAdulto();
                int QtdInfantilAtual = tipoItem.getQtdInfantil();
                String VolumeMassaAtual = tipoItem.getVolumeMassa();
    
%>
    <tbody>
    <tr>
        <th><%=NomeItemAtual%></th>
        <th><%=QtdAdultoAtual%></th>
        <th><%=QtdInfantilAtual%></th>
        <th><%=VolumeMassaAtual%></th>
        <th><form class="form-inline" action="./editarTipoItem.jsp" method="get" accept-charset="UTF-8">
            <div class="form-group" style="display:none;">
                <input type="text" class="form-control" id="TipoItemID" name="TipoItemID" required="true" value="<%=TipoItemID%>" readonly>
            </div>
            <div class="form-group" style="display:none;">
                <input type="text" class="form-control" id="NomeItem" name="NomeItem" required="true" value="<%=NomeItemAtual%>" readonly>
            </div>
            <div class="form-group" style="display:none;">
                <input type="text" class="form-control" id="QtdAdulto" name="QtdAdulto" required="true" value="<%=QtdAdultoAtual%>" readonly>
            </div>
            <div class="form-group" style="display:none;">
                <input type="text" class="form-control" id="QtdInfantil" name="QtdInfantil" required="true" value="<%=QtdInfantilAtual%>" readonly>
            </div>
            <div class="form-group" style="display:none;">
                <input type="text" class="form-control" id="VolumeMassa" name="VolumeMassa" required="true" value="<%=VolumeMassaAtual%>" readonly>
            </div>
            <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="editarVal" id="editarVal" value="editarVal">Editar</button>
        </form>
        </th>
        

        <th><button onclick="goBack()">Excluir</button></th>
    </tr>
    <% } %>
    </tbody>
    </table>
    <button onclick="goBack()">Nova Consulta</button>
    <button onclick="goBack2()">Índice</button>
    <% } %>

    <script>
    function goBack() {
        window.history.go(-1);
    }
    function test(tst){
        alert(tst);
    }
    function goBack2() {
        window.history.go(-2);
    }
    </script>
        
</div>

</html>

