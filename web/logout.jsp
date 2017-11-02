<%-- 
    Document   : adminlogout
    Created on : 24/11/2016, 20:34:51
    Author     : Sebastian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log Out</title>
    </head>
    <body>
        <h1></h1>
<% session.invalidate(); %>

<meta http-equiv="refresh" content="0;./adminindex.jsp">
    </body>
</html>
