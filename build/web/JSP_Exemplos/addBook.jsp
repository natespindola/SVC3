<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>       
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
<%     if ( null == request.getParameterValues("incluir_livro")){
%>    

        <%@ page import="app.model.entity.Book" %>
        <%@ page import="app.controller.BookController" %>
    <%@ page import="java.util.ArrayList" %>

    Preencha os campos do livro que deseja adicionar
        
        
        <form action="./addBook.jsp" method="post">
            
            <table>
                <tr>
                    <td>Titulo:</td>
                    <td><input type="text" name="title" required ="true"></td>
                </tr>
                <tr>
                    <td>Author:</td>
                    <td><input type="text" name="author" ><br></td>
                </tr>
                <tr>
                    <td>Editora:
                    <td><input type="text" name="publisher" ><br></td>
                </tr>
                <tr>
                    <td>Isbn10:
                    <td><input type="text" name="isbn10" ><br></td>
                </tr>
                <tr>
                    <td>Isbn13:</td>
                    <td><input type="text" name="isbn13" ><br></td>
                </tr>
                
            </table>
                
            <input type="submit" value="submit" name="incluir_livro"><br>
            
         </form>
            
<%          
 } else {
    String title  = request.getParameter("title");
    String author = request.getParameter("author");
    String publisher= request.getParameter("publisher");
    String isbn10 = request.getParameter("isbn10");
    String isbn13 = request.getParameter("isbn13");


    BookController bc = new BookController();
    Book book = new Book();


     book.setTitle(title);
     book.setAuthor(author);
     book.setPublisher(publisher);
     book.setIsbn10(isbn10);
     book.setIsbn13(isbn13);

    ArrayList<Book> al = bc.incluir(book);


    if (al.isEmpty() || al == null){
%>
 Erro ao adicionar o livro retry
<form action="./addBook.jsp" method="post">
    <input type="submit" name="voltar" value="Adicionar Livro" />
</form>
    <% } else if(al.size() == 1){

    Book b = al.get(0); %>
    <jsp:forward page="./addProduct.jsp" >
        <jsp:param name="livro_id" value="<%= b.getId() %>" />
        <jsp:param name="livro_autor" value="<%= b.getAuthor() %>" />
        <jsp:param name="livro_titulo" value="<%=b.getTitle() %>"/>
    </jsp:forward>
    <%--<form action="./addProduct.jsp" method="post" name="livro_selected" id="livro_selected">--%>
        <%--<input type="hidden" name="livro_id" value=""/>--%>
        <%--<input type="hidden" name="livro_autor" value="<% out.println(b.getAuthor()); %> "/>--%>
        <%--<input type="hidden" name="livro_titulo" value="<% out.println(b.getTitle()); %> "/>--%>
    <%--</form>--%>
<%  }    else { %>
    <table border=1>
        <thead>
        <th>Nome </th>
        <th>Autor </th>
        <th>Isbn antigo </th>
        <th>Isbn Novo</th>
        <th>Ação</th>
        </thead>
        <tbody>
            <%
    for (Book b :
         al ) {
        %>
        <tr>
            <%
                out.println("<td>" + b.getTitle() + "</td>");
                out.println("<td>" + b.getAuthor() + "</td>");
                out.println("<td>" + b.getIsbn10() + "</td>");
                out.println("<td>" + b.getIsbn13() + "</td>");
            %>
            <td>
            <form action="./addProduct.jsp" method="post">
                <input type="submit" name="Livro" value="Selecionar"/>
                <input type="hidden" name="livro_id" value="<% out.println(b.getId()); %> "/>
                <input type="hidden" name="livro_autor" value="<% out.println(b.getAuthor()); %> "/>
                <input type="hidden" name="livro_titulo" value="<% out.println(b.getTitle()); %> "/>
            </form>
            </td>
        </tr>
        <% } %>
        </tbody>
        </table>
<%    }
} %>
</body>
<script src="js/jquery-3.1.1.min.js">
</script>
</html>