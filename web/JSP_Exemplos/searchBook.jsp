<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <title>Pesquisar Livros - Sebo Virtual</title>

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
<%@ page import="app.model.entity.Book" %>
<%@ page import="java.util.ArrayList" %>

<h1 align="center">Pesquise Livros</h1>

<%
    
    String title = request.getParameter("title");
    String author = request.getParameter("author");
    String publisher = request.getParameter("publisher");
    String isbn = request.getParameter("isbn");

    BookController bc = new BookController();
    ArrayList<Book> results = bc.search(title, author, publisher, isbn);
    
%>
<div style="margin-bottom: 10px; padding: 4px">
<form class="form-inline" action="./searchBook.jsp" method="get">
    <div class="form-group">
        <label for="title">Título: </label>
        <input type="text" name="title" id="title" class="form-control" value="<%= (title == null) ? "" : title%>">
    </div>  
    <div class="form-group">
        <label>Autor: </label>
        <input type="text" name="author" id="author" class="form-control" value="<%=(author == null) ? "" : author%>">
    </div>
    <div class="form-group">
        <label for="title">Editora: </label>
        <input type="text" name="publisher" id="publisher" class="form-control" value="<%=(publisher == null) ? "" : publisher%>">
    </div>
    <div class="form-group">
        <label for="title">ISBN: </label>
        <input type="text" name="isbn" id="isbn" class="form-control" value="<%=(isbn == null) ? "" : isbn%>">
    </div>
    
    <button type="submit" name="buscar" value="buscar" class="btn btn-default">Pesquisar</button>
</form>
</div>


<div>
<table class="table table-bordered table-striped">
    <thead class="thead-default">
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>Autor</th>
        <th>Editora</th>
        <th>ISBN 10</th>
        <th>ISBN 13</th>
        <th>Exemplares</th>
    </tr>
    </thead>
<%
    for (Book book : results) {
%>
    <tbody>
    <tr>
        <th><%=book.getId()%></th>
        <th><%=book.getTitle()%></th>
        <th><%=book.getAuthor()%></th>
        <th><%=book.getPublisher()%></th>
        <th><%=book.getIsbn10()%></th>
        <th><%=book.getIsbn13()%></th>
        <th><a href="./searchProduct.jsp?bookId=<%=book.getId()%>&title=<%=book.getTitle()%>" class="btn btn-default btn-xs" role="button"><p class="text-center" style="color: #85bfac">+</p></a></th>
<%      
        }
%>
    </tbody>
    </tr>
</table>
</div>    
</body>
</html>
