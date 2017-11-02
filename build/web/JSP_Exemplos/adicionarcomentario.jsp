<%-- 
    Document   : adicionarcomentario
    Created on : 04/12/2016, 16:46:10
    Author     : Cauê Muriano
--%>

<%@page import="app.model.entity.Order"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Comentar Compra</title>
  <style>
      .my_text
            {
                font-family:    Calibri;
                font-size:     20px;
                font-weight:    bold;
            }
  </style>
  <!-- Bootstrap -->
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"><link rel="stylesheet" href="js/ui/jquery-ui.min.css">
  <script src="js/jquery-3.1.1.min.js"></script>
  <script src="js/autocomplete/jquery.autocomplete.js"></script>
  <script src="js/ui/jquery-ui.min.js"></script>


</head>
    
<body bgcolor="white">
<%@ page import="app.controller.BookController"%>
<%@ page import="app.controller.ProductController"%>
<%@ page import="app.controller.OrderController"%>
<%@ page import="app.model.entity.Product" %>
<%@ page import="app.model.entity.Order" %>
<%@ page import="app.model.core.AbstractDateClass" %>
<%@ page import="java.util.ArrayList" %>
<% 
     %>
    
<div class="form-group col-xs-12 col-md-6" id="div_nome">
    <form method="post">
        <div class="row" style="height: 30px; background-color:#85bfac"></div>
        <label for="nome">Adicione aqui sua avaliação da compra: </label>
        
        <input type="text" class="form-control" id="comentario" name="comentario" required="true" placeholder="Escreva aqui o seu comentario...">
        <div>
            <div class="row" style="height: 30px; background-color:#85bfac"></div>
            <div class="row">
        <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="adicionarcomentario" id="adicionarcomentario" value="adicionarcomentario">Avaliar</button>
        <a href="./pedidos.jsp" class="btn btn-primary col-xs-4 col-md-1 col-md-offset-4 col-xs-offset-4" role="button"> Voltar</a>
            </div>
       <div class="row" style="height: 30px; background-color:#85bfac"></div>

<%
    String comentario = request.getParameter("comentario");
    long orderId = Long.parseLong(request.getParameter("orderId"));
    Order order = new Order();
    OrderController oc = new OrderController();
    
    //out.println("<h1> '" + orderId+ "' </h1>"); 
    
    if(null == request.getParameterValues("adicionarcomentario")){}
    else{    
            oc.adicionarcomentario(comentario,orderId);
            out.println("Comentário postado com sucesso");
    }
    

    //oc.adicionarcomentario("TOP",orderId);
    // Criar função que busca todas as informações da tabela tb_order pelo Id e retorna uma entidade Order
    //ex.:
    // order = oc.buscarOrder (orderId);
    
    // Criar função que adiciona um comentario na tabela tb_order pela entidade order e pelo String comentario e 
    // retorna um boolean que indica true caso o comentario tenha sido adicionado com sucesso.
    // IMPORTANTE: nessa mesma função, ela terá que criar um log! (por isso a função vai ter que receber uma entidade
    // e não somente o Id, porque desse jeito você cria o log facilmente)
    // ex.:
    // b = oc.adcionarComentario(order, comentario);
   
%>
 </div>
</div>
</form>
</body>
</html>
