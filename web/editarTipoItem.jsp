<%-- 
    Document   : editarTipoItem
    Created on : 14/10/2017
    Author     : Natalia
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>SVC - Editar Tipo de Item</title>

  <!-- Bootstrap -->
<!--  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"><link rel="stylesheet" href="js/ui/jquery-ui.min.css">
  <script src="js/jquery-3.1.1.min.js"></script>
  <script src="js/autocomplete/jquery.autocomplete.js"></script>
  <script src="js/ui/jquery-ui.min.js"></script>-->

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->
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
if ( null == request.getParameterValues("editar")){
    String TipoItemID = request.getParameter("TipoItemID");
    String NomeItem = request.getParameter("NomeItem");
    String QtdAdulto = request.getParameter("QtdAdulto");
    String QtdInfantil = request.getParameter("QtdInfantil");
    String VolumeMassa = request.getParameter("VolumeMassa");
    
    NomeItem = NomeItem.replace("Ã¡","á").replace("Ã©","é").replace("Ã­","í").replace("Ã³","ó").replace("Ãº","ú").replace("Ã§","ç").replace("Ã£","ã").replace("Âº","º");
    VolumeMassa = VolumeMassa.replace("Ã¡","á").replace("Ã©","é").replace("Ã­","í").replace("Ã³","ó").replace("Ãº","ú").replace("Ã§","ç").replace("Ã£","ã").replace("Âº","º");
%>
<div style="margin-bottom: 10px; padding: 4px">
<form class="form-inline" action="./editarTipoItem.jsp" method="get" accept-charset="UTF-8">
    <div class="form-group" style="display:none;">
        <input type="text" class="form-control" id="TipoItemID" name="TipoItemID" required="true" value="<%=TipoItemID%>" readonly>
    </div>
    <div class="form-group">
        <label>Nome do item: </label>
        <input type="text" class="form-control" id="NomeItem" name="NomeItem" required="true" value="<%=NomeItem%>">
    </div>
    <div class="form-group">
        <label for="title">Quantidade na Lista de Adultos: </label>
        <input type="number" class="form-control" id="QtdAdulto" name="QtdAdulto" required="false" placeholder="Digite a quantidade mínima desse item na lista de carrinhos de adultos..." value="<%=QtdAdulto%>">
    </div>
    <div class="form-group">
        <label for="title">Quantidade na Lista Infantil: </label>
        <input type="number" class="form-control" id="QtdInfantil" name="QtdInfantil" required="false" placeholder="Digite a quantidade mínima desse item na lista de carrinhos infantis..." value="<%=QtdInfantil%>">
    </div>
    <div class="form-group">
        <label for="title">Volume/Massa:</label>
        <input type="text" class="form-control" name="VolumeMassa" id="VolumeMassa" required="false" placeholder="Digite o volume ou a massa do item a ser cadastrado..." value="<%=VolumeMassa%>">
    </div>
    <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="editar" id="editar" value="editar">OK</button>
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
        int TipoItemID = Integer.parseInt(request.getParameter("TipoItemID"));
        String VolumeMassa = request.getParameter("VolumeMassa");
        int QtdAdulto = Integer.parseInt(request.getParameter("QtdAdulto"));
        int QtdInfantil = Integer.parseInt(request.getParameter("QtdInfantil"));
        String NomeItem = request.getParameter("NomeItem");

        NomeItem = NomeItem.replace("Ã¡","á").replace("Ã©","é").replace("Ã­","í").replace("Ã³","ó").replace("Ãº","ú").replace("Ã§","ç").replace("Ã£","ã").replace("Âº","º");
        VolumeMassa = VolumeMassa.replace("Ã¡","á").replace("Ã©","é").replace("Ã­","í").replace("Ã³","ó").replace("Ãº","ú").replace("Ã§","ç").replace("Ã£","ã").replace("Âº","º");

        TipoItem tipoItem = new TipoItem();
        tipoItem.setTipoItemID(TipoItemID);
        tipoItem.setNomeItem(NomeItem);
        tipoItem.setQtdAdulto(QtdAdulto);
        tipoItem.setQtdInfantil(QtdInfantil);
        tipoItem.setVolumeMassa(VolumeMassa);

        TIController.atualizar(tipoItem);
%>
<div class="col-md-offset-4 col-xs-offset-4 col-md-4 colxs-4">
    <h2>Tipo de Item alterado com sucesso!</h2>
</div>
<%}%>
    <button onclick="goBack()">Cancelar</button>
    <script>
    function goBack() {
        window.history.go(-3);
    }
    </tr>
</table>
</div> 

</html>

