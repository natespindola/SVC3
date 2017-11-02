<%-- 
    Document   : addTipoItem
    Created on : 13/10/2017, 16:07:25
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
  <title>SVC - Adicionar Tipo de Item</title>

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
if ( null == request.getParameterValues("incluir")){
%>
<div style="margin-bottom: 10px; padding: 4px">
<form class="form-inline" action="./cadastrarItem.jsp" method="get" accept-charset="UTF-8">
    <div class="form-group">
        <label>Nome do item: </label>
        <input type="text" class="form-control" id="NomeItem" name="NomeItem" required="true" placeholder="Insira o nome do item a ser cadastrado (máx 255 caracteres)">
    </div>
    <div class="form-group">
        <label for="title">Quantidade na Lista de Adultos: </label>
        <input type="number" class="form-control" id="QtdAdulto" name="QtdAdulto" required="true" placeholder="Digite a quantidade mínima desse item na lista de carrinhos de adultos...">
    </div>
    <div class="form-group">
        <label for="title">Quantidade na Lista Infantil: </label>
        <input type="number" class="form-control" id="QtdInfantil" name="QtdInfantil" required="true" placeholder="Digite a quantidade mínima desse item na lista de carrinhos infantis...">
    </div>
    <div class="form-group">
        <label for="title">Volume/Massa:</label>
        <input type="text" class="form-control" name="VolumeMassa" id="VolumeMassa" required="true" placeholder="Digite o volume ou a massa do item a ser cadastrado...">
    </div>
    <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="incluir" id="incluir" value="incluir">OK</button>
</form>
    <button onclick="goBack()">Cancelar</button>
    
</div>
<%
    }else{
        TipoItemController TIController = new TipoItemController();
        String VolumeMassa = request.getParameter("VolumeMassa");
        int QtdAdulto = Integer.parseInt(request.getParameter("QtdAdulto"));
        int QtdInfantil = Integer.parseInt(request.getParameter("QtdInfantil"));
        String NomeItem = request.getParameter("NomeItem");

        TipoItem tipoItem = new TipoItem();
        
        tipoItem.setNomeItem(NomeItem);
        tipoItem.setQtdAdulto(QtdAdulto);
        tipoItem.setQtdInfantil(QtdInfantil);
        tipoItem.setVolumeMassa(VolumeMassa);

        TIController.inserir(tipoItem);

        %><div class="col-md-offset-4 col-xs-offset-4 col-md-4 colxs-4">
            <h2>Tipo de Item adicionado com sucesso!</h2>
            <button onclick="goBack2()">Voltar</button>
        </div>
<%
}
%>


    </tr>
</table>
</div> 

</html>

<script>
    function goBack() {
        window.history.go(-1);
    }
    function goBack2() {
        window.history.go(-2);
    }
</script>