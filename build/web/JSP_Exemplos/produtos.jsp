<%-- 
    Document   : produtos
    Created on : 26/11/2016, 19:49:59
    Author     : Sebastian
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="app.model.dao.ProductDao"%>
<%@ page import="app.model.entity.Product"%>
<%@ page import="app.controller.ProductController"%>
<%@ page import="core.Transacao"%>
<!DOCTYPE html>
<html lang="pt-br" >
<header>
    <title>Lista de Produtos - Sebo VIrtual</title>
</header>

<body>

<div class="row" style="height: 30px; background-color:#85bfac">
    <div class="col-sm-12"></div>
</div>


<CENTER><FONT COLOR=BLUE SIZE=6>Produtos</FONT></CENTER>

<% if (session.getAttribute("Admin") != null ){ %>
<form action="./adminindex.jsp" method="post">
<input type="submit" name="voltar" value="voltar" />
</form> 

<BR>

<%  }    
    ProductController controller = new ProductController();
    Product[] ProductArray;
    ProductArray = controller.EncontrarTodosProdutos();
    if (ProductArray[0] == null){
%>        
        <CENTER><FONT COLOR=RED SIZE=4>A lista de produtos esta vazia!</FONT></CENTER>
      
<%      }else{
%>        
    <TABLE BORDER=1> <! Inicia a tabela e coloca uma borda de espessura igual a 1>
        <thead>        
            <TR> <! Cria a primeira linha da tabela>
                <th>ID do vendedor:</th> <! Aqui foi criada uma célula>
                <th>Nome do vendedor:</th>
                <th>ID do livro:</th>
                <th>Título do livro:</th>
                <th>Descrição: </th>
                <th>Quantidade:</th>
                <th>Criado em:</th>
                <th>Atualizado em: </th>
                <th>Ativado: </th>
                <th>Excluido: </th>
            </TR> <! Fecha a primeira linha da tabela>
        </thead>
        <tbody>
    <%  
        int i = 0;
        while (ProductArray[i] != null) {
    %>
            <TR> <! Abre a segunda linha da tabela>
    <%      if (ProductArray[i] != null){                
                out.println("<td>" + ProductArray[i].getUserId() + "</td>");
                out.println("<td>" + controller.EncontrarNomeDoVendedor(ProductArray[i]) + "</td>");
                out.println("<td>" + ProductArray[i].getBookId() + "</td>");
                out.println("<td>" + controller.EncontrarTituloDoLivro(ProductArray[i]) + "</td>");
                out.println("<td>" + ProductArray[i].getDescription() + "</td>");
                out.println("<td>" + ProductArray[i].getQuantity() + "</td>");
                out.println("<td>" + ProductArray[i].getCreatedAt() + "</td>");
                out.println("<td>" + ProductArray[i].getUpdatedAt() + "</td>");
                if (ProductArray[i].isActive()){
                    out.println("<td>" + "Sim" + "</td>");
                }else{
                    out.println("<td>" + "Não" + "</td>");
                }
                if (ProductArray[i].isExcluded()){
                    out.println("<td>" + "Sim" + "</td>");
                }else{
                    out.println("<td>" + "Não" + "</td>");
                }
                i+=1;
                }// if
    %>      
            </TR> <! Encerra a Segunda linha da tabela>
    <%          } //while
            } //else
%>
    </tbody>

</TABLE> <! Encerra a tabela>

</body>
</html>
