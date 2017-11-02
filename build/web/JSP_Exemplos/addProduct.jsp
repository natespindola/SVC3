<%-- 
    Document   : addProduct
    Created on : 27/11/2016, 17:20:49
    Author     : Sebastian
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Adicionar produto - Sebo Virtual</title>

  <!-- Bootstrap -->
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"><link rel="stylesheet" href="js/ui/jquery-ui.min.css">
  <script src="js/jquery-3.1.1.min.js"></script>
  <script src="js/autocomplete/jquery.autocomplete.js"></script>
  <script src="js/ui/jquery-ui.min.js"></script>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>
    

<%@ page import="app.controller.ProductController"%>
<%@ page import="app.controller.BookController"%>
<%@ page import="app.controller.ProductPriceController"%>
<%@ page import="app.model.entity.Product" %>
<%@ page import="app.model.entity.User" %>
<%@ page import="app.model.entity.ProductPrice" %>
<%@ page import="app.model.entity.ProductInfos" %>
<%@ page import="app.model.helper.PeriodicityTypeEnum" %>
<%@ page import="app.model.helper.PriceTypeEnum" %>
<%@ page import="app.model.core.AbstractDateClass" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.NumberFormatException" %>
<%@ page import="java.math.BigInteger" %>
<%  
    
    String livro_id;
    User user = new User();
    user = (User) session.getAttribute("User");
    if (null != user){
 %>

<%
if ( null == request.getParameterValues("incluir")){
    livro_id = request.getParameter("livro_id");
    session.setAttribute("livro_id", livro_id);
%>

<h1 align="center">Vender/Alugar o livro: <%=request.getParameter("livro_titulo") %> </h1>
<div style="margin-bottom: 10px; padding: 4px">
<form class="form-inline" action="./addProduct.jsp" method="get">
<!--    <input type="hidden" name="livro_id" id="bookId" >-->
    <div class="form-group">
        <label>Descrição: </label>
        <input type="text" class="form-control" id="description" name="description" required="true" placeholder="Insira uma descrição para seu livro (máx 500 caracteres):...">
    </div>
    <div class="form-group">
        <label for="title">Preço: </label>
        <input type="text" class="form-control" id="price" name="price" required="true" placeholder="Por quanto gostaria de alugar/vender o livro?...">
    </div>
    <div class="form-group">
        <input type="radio" name="priceTypeId" id="priceTypeId" value="1"> Aluguel<br>
        <input type="radio" name="priceTypeId" id="priceTypeId" value="2"> Venda<br>
    </div>

    <div class="form-group">
        <input type="radio" name="periodicityId" id="periodicityId" value="2"> Diario<br>
        <input type="radio" name="periodicityId" id="periodicityId" value="3"> Semanal<br>
        <input type="radio" name="periodicityId" id="periodicityId" value="4"> Mensal<br>
        <input type="radio" name="periodicityId" id="periodicityId" value="5"> Semestral<br>
        <input type="radio" name="periodicityId" id="periodicityId" value="6"> Anual<br>
    </div> 
    
<%
    if(user.getProfile().equals("JURIDICA")){
%>
    <div class="form-group">
        <label for="title">Quantidade: </label>
        <input type="text" class="form-control" id="nome" name="quantidade" required="true" >
    </div>
<%
    }
%>    
    <button type="submit" class="btn btn-success col-xs-4 col-xs-offset-3 col-md-2 col-md-offset-3" name="incluir" id="incluir" value="incluir">Cadastrar</button>
</form>
</div>
<%
    } else{
    ProductController pc = new ProductController();
    ProductPriceController ppc = new ProductPriceController();
    BookController bc = new BookController();
    String description = request.getParameter("description");
    float price;
    price = Float.parseFloat(request.getParameter("price"));
    String priceTypeId = request.getParameter("priceTypeId");    
    String periodicityId = request.getParameter("periodicityId");    
    String quantidade = request.getParameter("quantidade");
        Product product = new Product();
        ProductPrice product_price = new ProductPrice();
        product.setUserId(user.getId());
        livro_id = (String) session.getAttribute("livro_id");
        long livro_id_long = Long.parseLong(livro_id);
            
        product.setBookId(livro_id_long);
        product.setDescription(description);
        if (user.getProfile().equals("JURIDICA")){
        product.setQuantity(Long.parseLong(quantidade));
        } else{
            product.setQuantity(1);
        }

        product.setActive(true);
        product.setExcluded(false);
        pc.Incluir(product);

        long id = pc.EncontrarId(product);
        product_price.setProductId(id);
        product_price.setPriceTypeId("1".equalsIgnoreCase(priceTypeId) ? PriceTypeEnum.ALUGUEL : PriceTypeEnum.VENDA);
        
   /*     switch(periodicityId){
            case "2":
                product_price.setPeriodicityTypeId(PeriodicityTypeEnum.DIARIO);
                break;
            case "3":
                product_price.setPeriodicityTypeId(PeriodicityTypeEnum.SEMANAL);
                break;
            case "4":
                product_price.setPeriodicityTypeId(PeriodicityTypeEnum.MENSAL);
                break;
            case "5":
                product_price.setPeriodicityTypeId(PeriodicityTypeEnum.SEMESTRAL);
                break;
            case "6":
                product_price.setPeriodicityTypeId(PeriodicityTypeEnum.ANUAL);
                break;
        } */
        if (priceTypeId.equals("2")){
            product_price.setPeriodicityTypeId(PeriodicityTypeEnum.UNICO);
        }

        if (periodicityId.equals("2")) {
            product_price.setPeriodicityTypeId(PeriodicityTypeEnum.DIARIO);
        }
        else if (periodicityId.equals("3")) {
            product_price.setPeriodicityTypeId(PeriodicityTypeEnum.SEMANAL);
        }
        else if (periodicityId.equals("4")) {
            product_price.setPeriodicityTypeId(PeriodicityTypeEnum.MENSAL);
        }
        else if (periodicityId.equals("5")) {
            product_price.setPeriodicityTypeId(PeriodicityTypeEnum.SEMESTRAL);
        }
        else if (periodicityId.equals("6")) {
            product_price.setPeriodicityTypeId(PeriodicityTypeEnum.ANUAL);
        }
        product_price.setPrice(price);
        ppc.inserir(product_price);
%>
<div class="col-md-offset-4 col-xs-offset-4 col-md-4 colxs-4">
    <h2>livro <%=bc.titulo(livro_id) %> adicionado com sucesso!</h2>
</div>
<div class="row">
    <a href="./index.jsp" class="btn btn-primary col-xs-4 col-md-1 col-md-offset-4 col-xs-offset-4" role="button"> Voltar</a>
</div>

<div class="row">
    <a href="./searchProduct.jsp?userId=<%=user.getId()%>" class="btn btn-primary col-xs-4 col-md-1 col-md-offset-4 col-xs-offset-4" role="button"> Ver meus produtos</a>
</div>
<%
      }
    }else{
%>
<meta http-equiv="refresh" content="1;./index.jsp">
<%    } %>
    </tr>
</table>
</div> 

</html>

