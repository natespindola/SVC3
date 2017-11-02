

<%@page import="app.model.entity.User"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="app.controller.ProductController"%>
<%@ page import="app.model.entity.Pedido" %>
<%@ page import="app.model.dao.ProductDao"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="core.Transacao"%>
<html lang="pt-br">
<header>
    <title>Ver Pedidos</title>
</header>
<body bgcolor="white">
    
<CENTER><FONT COLOR=BLUE SIZE=6>Seus Pedidos</FONT></CENTER>

<form action="./pedidos.jsp" method="post">


</form> 



<%      
%>
<! ------------------------------------------------------------>

<%  
    ProductController p = new ProductController();
    User user = new User();
    user = (User) session.getAttribute("User");
   
    long id = user.getId();
    ArrayList<Pedido> pedido_array;
    pedido_array = p.ver_pedido(id);
    if (pedido_array.isEmpty()){
%>
    <CENTER><FONT COLOR=RED SIZE=4>Você não possui pedidos!</FONT></CENTER>
      
<%       } else {
%>



        <TABLE BORDER=1>
            <thead>
            <tr>
                <th>OrderId</th>
                <th>Name</th>                
                <th>ProductId</th>
                <th>Title</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>CreatedAt</th>
                <th>Adicionar Avaliação</th>
            </tr>
            </thead>
            <tbody>
        <%  
            for (Pedido pedido :
         pedido_array ) {
        %>
    <TR> <! Abre a segunda linha da tabela>
    
                <th><%= pedido.getorderId()%></th>
                <th><%= pedido.getname()%></th>
                <th><%= pedido.getproductId()%></th>
                <th><%= pedido.gettitle()%></th>
                <th><%= pedido.getprice()%></th>
                <th><%= pedido.getquantity()%></th>
                <th><%= (double)(pedido.getquantity()*pedido.getprice())%></th>
                <th><%= pedido.getCreatedAt()%></th>
                <th><a href="./adicionarcomentario.jsp?orderId=<%=pedido.getorderId()%>" class="btn btn-default btn-xs" role="button"><p class="text-center" style="color: #85bfac">+</p></a></th>
                
          
         
            </TR> <! Encerra a Segunda linha da tabela>
<%         } //while
        } //else
%>
<div class="row">
    <a href="./index.jsp" class="btn btn-primary col-xs-4 col-md-1 col-md-offset-4 col-xs-offset-4" role="button"> Voltar</a>
</div>

<%

%>
        
    </tbody>
</TABLE>

</body>





    
</html>
