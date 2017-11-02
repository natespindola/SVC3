<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Sebo Virtual</title>

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
    
<body bgcolor="white">
<%@ page import="app.controller.BookController"%>
<%@ page import="app.controller.ProductController"%>
<%@ page import="app.controller.UserController"%>
<%@ page import="app.model.entity.Product" %>
<%@ page import="app.model.entity.Book" %>
<%@ page import="app.model.entity.User" %>
<%@ page import="app.model.entity.ProductPrice" %>
<%@ page import="app.model.entity.ProductInfos" %>
<%@ page import="app.model.helper.PeriodicityTypeEnum" %>
<%@ page import="app.model.helper.PriceTypeEnum" %>
<%@ page import="app.model.core.AbstractDateClass" %>
<%@ page import="java.util.ArrayList" %>


<%
    
    String bookId = request.getParameter("bookId");
    String title = "";
    if (bookId != null && !bookId.isEmpty()) {
        BookController bc = new BookController();
        title = bc.titulo(bookId);
    } else { 
        title = request.getParameter("title");
    }
    
        UserController uc = new UserController();
    String userId = request.getParameter("userId");
    String user = "";
    if (userId != null && !userId.isEmpty()) {
        user = uc.userName(userId);
    }
    
    String description = request.getParameter("description");
    String priceTypeId = request.getParameter("priceTypeId");
    String priceMax = request.getParameter("priceMax");

    ProductController pc = new ProductController();
    ArrayList<ProductInfos> results = pc.search(bookId, description, priceTypeId, priceMax, userId);
    
    if (title != null && !title.isEmpty()) {
%>
    <h1 align="center">Exemplares disponíveis de <%=title%></h1>
<%        
    } else if (user != null && !user.isEmpty()) {
%>
    <h1 align="center"><%=user%>, estes são seus produtos cadastrados:</h1>
<%
}
%>

<div style="margin-bottom: 10px; padding: 4px">
<form class="form-inline" action="./searchProduct.jsp" method="get">
    <input type="hidden" name="bookId" id="bookId" value="<%=(bookId == null) ? "" : bookId%>">
    <input type="hidden" name="userId" id="userId" value="<%=(userId == null) ? "" : userId%>">
    <div class="form-group">
        <label>Descrição: </label>
        <input type="text" name="description" id="description" class="form-control" value="<%=(description == null) ? "" : description%>">
    </div>
    <div class="form-group">
        <label for="title">Preço Máximo: </label>
        <input type="text" name="priceMax" id="priceMax" class="form-control" value="<%=(priceMax == null) ? "" : priceMax%>">
    </div>
    <div class="form-group">
        <input type="radio" name="priceTypeId" id="priceTypeId" value="1"> Aluguel<br>
        <input type="radio" name="priceTypeId" id="priceTypeId" value="2"> Venda<br>
    </div>
    
    <button type="submit" name="buscar" value="buscar" class="btn btn-default">Pesquisar</button>
    <th><a href="./searchBook.jsp" class="btn btn-default btn-xs" role="button"><p class="text-center" style="color: #85bfac">Voltar / Outro Livro</p></a></th>
</form>
</div>


<div class="table-responsive">
<table class="table table-bordered table-striped">
    <thead class="thead-default">
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>Descrição</th>
        <th>Preço</th>
        <th>Disponibilidade</th>
        <th>Ação</th>
    </tr>
    </thead>
<%
    for (ProductInfos product_infos : results) {
        Product product = product_infos.getProduct();
        ProductPrice price = product_infos.getPrice();
        String idBook = Long.toString(product.getBookId());
        BookController bk = new BookController();
        Book book = bk.searchById(idBook);
        String idProduct = Long.toString(price.getProductId());
%>
    <tbody>
    <tr>
        <th><%=price.getProductId()%>   </th>
        <th><%=book.getTitle()%>   </th>
        <th><%=product.getDescription()%></th>
        <th><%=price.getPrice()%></th>
        <th><%=product.getQuantity()%><%=product.getQuantity() > 1 ? " exemplares" : " exemplar"%>
<%  
    String acao = "";
    if (price.getPriceTypeId() == PriceTypeEnum.VENDA) {
        acao = "COMPRAR";
%>
        a venda</th>
<% 
    } else if (price.getPriceTypeId() == PriceTypeEnum.ALUGUEL) {
    acao = "ALUGAR";
    Boolean plural = price.getPeriodicity() > 1 ? true : false;
    String periodo = "";
    switch (price.getPeriodicityTypeId()) {
        case DIARIO :      
        periodo = plural ? "dias" : "dia";
        break;
        case SEMANAL :
        periodo = plural ? "semanas" : "semana";
        break;
        case MENSAL :
        periodo = plural ? "meses" : "mês";
        break;
        case SEMESTRAL :
        periodo = plural ? "semestres" : "semestre";
        break;
        case ANUAL :
        periodo = plural ? "anos" : "ano";
        break;
        default :
        break;
      }
%>
        para aluguel por <%=price.getPeriodicity()%><%=" " + periodo%></th>
<%
    }
    if ( null != session.getAttribute("User") || (null != session.getAttribute("Admin"))){
    User usuario_logado = (User) session.getAttribute("User");
    String nome_logado = usuario_logado.getName();
    Long dono_id = product.getUserId();
    String dono_id_string =  dono_id.toString();
    String dono = uc.userName(dono_id_string);
    
    if ((dono.equals(nome_logado) || (user != null && !user.isEmpty() && user.equals(nome_logado))) || (null != session.getAttribute("Admin"))) {
%>
    <th><a href="./PAGINA_DE_EDICAO_DE_PRODUTO" class="btn btn-default btn-xs" role="button"><p class="text-center" style="color: #85bfac">EDITAR</p></a></th>
<%
    } else {
%>
    <th><form action="Carrinho.jsp"><input type="hidden" name="variable" value="<%=idProduct%>"/>
            <input type="submit" value="<%=acao%>" /></a></form></th>
<%
    }
    } else {
%>
    <th><form action="Carrinho.jsp"><input type="hidden" name="variable" value="<%=idProduct%>"/>
            <input type="submit" value="<%=acao%>" /></a></form></th>
<%
    }
    }
%>
    </tbody>
    </tr>
</table>
</div> 
</body>
</html>