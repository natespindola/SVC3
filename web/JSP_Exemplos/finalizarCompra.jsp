<%-- 
    Document   : finalizarCompra
    Created on : 28/11/2016, 19:47:38
    Author     : pc
--%>
<%@page import="app.controller.BookController"%>
<%@page import="app.model.entity.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Finalizar Compra</title>
    </head>
    <%@ page import="app.controller.AdressController"%>
    <%@ page import="app.controller.UserController"%>
    <%@ page import="app.controller.ProductController"%>
    <%@ page import="app.controller.OrderProductController"%>
    <%@ page import="app.controller.OrderController" %>
    <%@ page import="app.model.entity.OrderProduct" %>
    <%@ page import="app.model.entity.Adress" %>
    <%@ page import="app.model.entity.User" %>
    <%@ page import="app.model.entity.ProductInfos" %>
    <%@ page import="app.model.entity.Product" %>
    
    <%@ page import="app.model.helper.ProfileEnum" %>
    <%@ page import="java.util.ArrayList" %>
    
    <body>
        <% if (null == request.getParameter("inicio")){
            
            ArrayList<ProductInfos> results = (ArrayList<ProductInfos>) session.getAttribute("ArrayCard");
            ProductController pcontrol = new ProductController();
            
            for (ProductInfos pinfo : results) {
                pcontrol.suspendProduct(pinfo.getProduct());
                           }
            
            //CartController cart = new CartController();
            //cart = (CartController) session.getAttribute("cart");
            //ArrayList<Product> plist = new ArrayList<Product>();
            //plist = cart.getProduct();
            //ProductController pcontrol = new ProductController();
            //pcontrol.suspendList(plist);
               
        
        if (null == session.getAttribute("user")){
            
        
        %>
    
    <jsp:forward page="./insertBuyer.jsp" >
        <jsp:param name="inicio" value="true" />
    </jsp:forward>
    
    <% } else {
            User user = new User();
            user = (User) session.getAttribute("user");
            
            %>
    <jsp:forward page="./RegisterAdress.jsp" >
        <jsp:param name="userId" value="<%=user.getId()%>"/>
    </jsp:forward>
            <%
        }
    }
    else {
            String userId = new String();
            String adressId = new String();
            userId = request.getParameter("userId");
            adressId = request.getParameter("adressId");
            OrderController orderC = new OrderController();
            long orderId = orderC.addOrder(userId, adressId);
            
            
            //CartController cart = new CartController();
            //cart = (CartController) session.getAttribute("cart");
            ArrayList<ProductInfos> pInfoList = (ArrayList<ProductInfos>) session.getAttribute("ArrayCard");
            
            OrderProductController orderPC = new OrderProductController();
            orderPC.addPOrder(pInfoList, orderId);
            
            ArrayList<OrderProduct> productList = new ArrayList<OrderProduct>();
            productList = orderPC.getOrderProduct(orderId);
            
            User user = new User();
            Adress adress = new Adress();
            user = (User) session.getAttribute("user");
            adress = (Adress) session.getAttribute("adress");
            
            //UserController userC = new UserController();
            //AdressController adressC = new AdressController();
            
            //user = userC.;
            //adress = adressC.SearchAdressByUser("1");
            
            
            
            if (null != session.getAttribute("user") && null != (Adress) session.getAttribute("adress")) {
                %>
            <h1>Seu Pedido foi Finalizado</h1>
            
            <p>
            Nome = <% out.println(user.getName());%>
            Documento = <% out.println(user.getDocument());%>
            Email = <% out.println(user.getEmail());%>
            Telefone = <% out.println(user.getPhone());%>
            Celular = <% out.println(user.getCelPhone());%>
            
            
            Endereco = <% out.println(adress.getAdress());%>
            Numero = <% out.println(adress.getNumber());%>
            Bairro = <% out.println(adress.getReference());%>
            CEP = <% out.println(adress.getZipCode());%>
            Complemento = <% out.println(adress.getComplement());%>
            
            Numero do Pedido = <% out.println(Long.toString(orderId));%>
            </p>
            
            <table>
            <%
            BookController bookC = new BookController();
            float total = 0;
            for (OrderProduct b : productList ) {
        %>
        
        <tr>
            <%
                long productId = b.getProductId();
                Book book = new Book();
                book = bookC.BookById(Long.toString(productId));
                
                total += b.getPrice();
                
                
                //out.println("<td>" + book.getTitle() + "</td>");
                //out.println("<td>" + book.getAuthor() + "</td>");
                //out.println("<td>" + b.getProductId() + "</td>");
                //out.println("<td>" + b.getPrice() + "</td>");
                //out.println("<td>" + b.getIsbn10() + "</td>");
                //out.println("<td>" + b.getIsbn13() + "</td>");
            %>
        </tr>
            
            
            <%
               }
            out.println("total=" + total);
                       }
    }
        %>
    
            </table>
            </body>
</html>
