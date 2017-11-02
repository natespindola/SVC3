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
<%@ page import="app.controller.ProductPriceController"%>
<%@ page import="app.controller.UserController"%>
<%@ page import="app.controller.AdressController"%>
<%@ page import="app.controller.UserController"%>
<%@ page import="app.model.entity.Product" %>
<%@ page import="app.model.entity.Book" %>
<%@ page import="app.model.entity.Adress" %>
<%@ page import="app.model.entity.User" %>
<%@ page import="app.model.entity.ProductPrice" %>
<%@ page import="app.model.entity.ProductInfos" %>
<%@ page import="app.model.helper.PeriodicityTypeEnum" %>
<%@ page import="app.model.helper.PriceTypeEnum" %>
<%@ page import="app.model.core.AbstractDateClass" %>
<%@ page import="java.util.ArrayList" %>


<%
    ArrayList<ProductInfos> results = (ArrayList<ProductInfos>) session.getAttribute("ArrayCard");
    
    if (results == null){
        results = new ArrayList<ProductInfos>();
    }
    
    ProductInfos CarProduct = null;
    String idProductS = request.getParameter("variable");
    
    if (idProductS != null){
    ProductController pc = new ProductController();
    CarProduct = pc.searchCart(idProductS);
    } else { 
        CarProduct = null;
    }
    
    String bookId = null;
        
    String userId = request.getParameter("userId");
    //String userId = "1";
    String user = "";
    if (userId != null && !userId.isEmpty()) {
        UserController uc = new UserController();
        user = uc.userName(userId);
    }
    else{
        user = null;
        
    }
    
    String description = null;
    String priceTypeId = null;
    String priceMax = null;

    //ProductController pc = new ProductController();
    //ArrayList<ProductInfos> results = pc.search(bookId, description, priceTypeId, priceMax, null);
    //ArrayList<ProductInfos> results = new ArrayList<ProductInfos>();
    if (CarProduct != null){
    results.add(CarProduct);
    session.setAttribute( "ArrayCard", results );
        //results = null;
    } 

    if (user == null || user.isEmpty()) {
%>
    <h1 align="center">Você vai precisar de fazer Login para concluir com a transação.</h1>
<%        
    } else if (user != null && !user.isEmpty()) {
%>
    <h1 align="center">Oí <%=user%>, bem-vindo a seu carrinho de compras:</h1>


<%  }      
    if (results != null && !results.isEmpty()) {
%>

<div class="table-responsive">
<table class="table table-bordered table-striped">
    <thead class="thead-default">
    <tr>
        <th>Título</th>
        <th>Autor</th>
        <th>Descrição</th>
        <th>Preço</th>
        <th>Tipo de Operação</th>
        
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
        <th><%=book.getTitle()%>   </th>
        <th><%=book.getAuthor()%>   </th>
        <th><%=product.getDescription()%></th>
        <th><%=price.getPrice()%></th>
        <th>
<%  
    String acao = "";
    if (price.getPriceTypeId() == PriceTypeEnum.VENDA) {
        acao = "COMPRAR";
%>
        Comprar</th>
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
        Alugar por <%=price.getPeriodicity()%><%=" " + periodo%></th>
<%
    }
    }
%>    
    </tbody>
    </tr>
</table>
</div> 



<%    
    } else if (results == null || results.isEmpty()){
%>        

<div style="margin-bottom: 10px; padding: 4px">
<form class="form-inline" action="./searchProduct.jsp" method="get">
    
    <p>Você não tem produtos no seu carinho de compras</p>
  
</form>
</div>
    
<%
    }
    
    
%>

    <div style="margin-bottom: 10px; padding: 4px">
    <form action="./searchProduct.jsp" method="post">
        <input type="submit" name="adicionarPr" value="Adicionar novo produto" />
    </form>
    </div>
    

<% if (null == request.getParameterValues("deleteProducts")){
    
%> 

<div style="margin-bottom: 10px; padding: 4px">
<form class="form-inline" action="./Carrinho.jsp" method="get">
    <input type="hidden" name="userId" id="userId" value="<%=userId%>">
    <input type="submit" name="deleteProducts" value="Eliminar todos los productos" />
</form>
</div>
    <form action="./finalizarCompra.jsp" method="post">
        <input type="submit" name="fCompra" value="Finalizar Compra" />
    </form>
    
<%    
    } else { 
        session.setAttribute("ArrayCard", null);
        
%>

   <meta http-equiv="refresh" content="0; ./Carrinho.jsp">

<% 
    }
    
%>    
    
 
  <form action="./index.jsp" method="post">
            <input type="submit" name="sair" value="Home" />
  </form>     
    
    
<%    
    
    AdressController ac = new AdressController();
    Adress adress = new Adress();
    adress = ac.SearchAdressByUser(userId);
    //session.setAttribute("RegisterAdressOperation", null);
    //session.setAttribute("UpdateAdressOperation", null);
    
    if ( (null == request.getParameterValues("cadEndereco")) & (adress == null) & (userId != null) & (session.getAttribute("RegisterAdressOperation") == null)){
    
%> 

<div style="margin-bottom: 10px; padding: 4px">
<form class="form-inline" action="./Carrinho.jsp" method="get">
    <input type="hidden" name="userId" id="userId" value="<%=userId%>">
    <input type="submit" name="cadEndereco" value="Cadastrar Endereço" />
</form>
</div>
    
<%    
    } else { 
        session.setAttribute("RegisterAdressOperation", true);
    }
    
    
    if (( null == request.getParameterValues("registro")) & (adress == null) & (userId != null) & (session.getAttribute("RegisterAdressOperation") != null)){
        //session.setAttribute("RegisterAdressOperation", null);
%>
<div style="margin-bottom: 10px; padding: 4px">
<form action="./Carrinho.jsp" method="post">

    <table>
        <tr>
            <td>*Código Zip</td>
            <td><input type="text" name="zipCodeC" required="true"/>
        </tr>
        <tr>
            <td>*Endereco</td>
            <td><input type="text" name="adressC" required="true"/>
        </tr>
        <tr>
            <td>Número</td>
            <td><input type="text" name="numberC"/>
        </tr>
        <tr>
            <td>Complemento</td>
            <td><input type="text" name="complementC"/>
        </tr>
        <tr>
            <td>Referencia</td>
            <td><input type="text" name="referenceC"/>
        </tr>
    </table>
    <input type="submit" name="registro" value="Adicionar" />
</form>
</div>

<%       } 
    
    if (( null != request.getParameterValues("registro")) & (adress == null) & (userId != null) & (session.getAttribute("RegisterAdressOperation") != null)){
        String zipCodeRegister = request.getParameter("zipCodeC");
        String adressRegister = request.getParameter("adressC");
        String numberRegister = request.getParameter("numberC");;
        String complementRegister = request.getParameter("complementC");
        String referenceRegister = request.getParameter("referenceC");
        
        AdressController tn = new AdressController();
        
        Adress t_adress = new Adress();
        t_adress.setZipCode(zipCodeRegister);
        t_adress.setAdress(adressRegister);
        t_adress.setNumber(numberRegister);
        t_adress.setComplement(complementRegister);
        t_adress.setReference(referenceRegister);
        t_adress.setUserId(userId);
        if ( tn.AddAdress(t_adress)) {
        // avisar usuario que transacao foi feita com sucesso
            session.setAttribute("RegisterAdressOperation", null);
%>
Cadastro realizado com sucesso!
<form action="./Carrinho.jsp" method="post">
    <input type="submit" name="voltar" value="Voltar" />
</form>
<%     } else {
            session.setAttribute("RegisterAdressOperation", null);
%>
Erro ao registrar endereco
<form action="./carinho.jsp" method="post">
    <input type="submit" name="retry" value="Repetir" />
</form>
<%     }
%>

<%       } 
    
    if (( null == request.getParameterValues("modEndereco")) & (adress != null) & (userId != null) & (session.getAttribute("UpdateAdressOperation") == null)){
   
    %>
<div style="margin-bottom: 10px; padding: 4px">
<form action="./Carrinho.jsp" method="post">

    <table>
        <tr>
            <td>Dados para o envio:</td>
        </tr>
        <tr>
            <td>Endereço: <%=adress.getAdress()%> <%=adress.getNumber()%> <%=adress.getComplement()%></td>
        </tr>
        <tr>
            <td>Referencia: <%=adress.getReference()%></td>
        </tr>
        <tr>
            <td>Código ZIP: <%=adress.getZipCode()%></td>
        </tr>
    </table>
    </form>
</div>
<div style="margin-bottom: 10px; padding: 4px">
    <form action="./Carrinho.jsp" method="post">
        <input type="submit" name="modEndereco" value="Modificar Endereço" />
    </form>
</div>

<%
        }else { 
        session.setAttribute("UpdateAdressOperation", true);
    }
    
    if (( null == request.getParameterValues("modificacao")) & (adress != null) & (userId != null) & (session.getAttribute("UpdateAdressOperation") != null)){
        //session.setAttribute("UpdateAdressOperation", null);
%>
<div style="margin-bottom: 10px; padding: 4px">
<form action="./Carrinho.jsp" method="post">

    <table>
        <tr>
            <td>*Código Zip</td>
            <td><input type="text" name="zipCodeM" required="true"/>
        </tr>
        <tr>
            <td>*Endereco</td>
            <td><input type="text" name="adressM" required="true"/>
        </tr>
        <tr>
            <td>Número</td>
            <td><input type="text" name="numberM"/>
        </tr>
        <tr>
            <td>Complemento</td>
            <td><input type="text" name="complementM"/>
        </tr>
        <tr>
            <td>Referencia</td>
            <td><input type="text" name="referenceM"/>
        </tr>
    </table>
    <input type="submit" name="modificacao" value="Modificar" />
</form>
</div>

<%       } 
    if (( null != request.getParameterValues("modificacao")) & (adress != null) & (userId != null) & (session.getAttribute("UpdateAdressOperation") != null)){
        String zipCodeRegister = request.getParameter("zipCodeM");
        String adressRegister = request.getParameter("adressM");
        String numberRegister = request.getParameter("numberM");;
        String complementRegister = request.getParameter("complementM");
        String referenceRegister = request.getParameter("referenceM");
        
        AdressController tn = new AdressController();
        
        Adress t_adress = new Adress();
        t_adress.setZipCode(zipCodeRegister);
        t_adress.setAdress(adressRegister);
        t_adress.setNumber(numberRegister);
        t_adress.setComplement(complementRegister);
        t_adress.setReference(referenceRegister);
        
        if ( tn.UpdateAdress(t_adress,userId)) {
            session.setAttribute("UpdateAdressOperation", null);
        // avisar usuario que transacao foi feita com sucesso
%>
Modificação realizado com sucesso!
<form action="./Carrinho.jsp" method="post">
    <input type="submit" name="voltar" value="Voltar" />
</form>
<%     } else {
            session.setAttribute("UpdateAdressOperation", null);
%>
Erro ao modificar endereco
<form action="./carrinho.jsp" method="post">
    <input type="submit" name="retry" value="Repetir" />
</form>
<%     }
    }
%>
    

    
</body>
</html>