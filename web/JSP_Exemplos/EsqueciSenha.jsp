<%-- 
    Document   : EsqueciSenha.jsp
    Created on : 27/11/2016, 23:01:54
    Author     : Cauê Muriano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="app.controller.UserController"%>
<%@ page import="app.model.dao.UserDao"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="core.Transacao"%>
<%@ page import="core.SendEmail"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Esqueci a senha.</title>
        <style>
            .my_text
            {
                font-family:    Calibri;
                font-size:     20px;
                font-weight:    bold;
            }
        </style>
    </head>
        <form action="./MyProducts.jsp" method="post">
</form>
    <body>
        
        <div class = "my_text">
                <div class="container-fluid">
                <div class="row" style="height: 30px; background-color:#85bfac"></div>
        
                <h1>Esqueceu sua senha?</h1>
        
                <div class="row" style="height: 30px; background-color:#85bfac"></div>
        
                <p>Digite seu e-mail:</p>
        <form class="form-horizontal" id="formForgotPassword" data-toggle="validator" role="form">
        <input name="inputEmail" type="email" class="form-control" id="inputEmail" placeholder="Enter Email" data-error="Enter valid Email" required>
        <input type="submit" value="Solicitar nova senha" name="btnsenha" />
    
<%          
            UserController esquecisenha = new UserController();
            String email;
            UserController UC = new UserController();
            String verifyemail =  request.getParameter("inputEmail");
            email = UC.esquecisenha(verifyemail);
            
            
            if(request.getParameter("inputEmail") == null){}
            else{
                UserController mandarsenha = new UserController();
                String senha = UC.mandarsenha(verifyemail);
            if(email != "O e-mail digitado não consta em nossa base de dados. Favor inserir um e-mail válido."){
            out.println("<td> \n <td>" );
            out.println("<td> Foi enviada uma nova senha para o e-mail: '"+email+ "' </td>" );
            SendEmail MAIL = new SendEmail();
            MAIL.sendMail("Recuperar Senha","Sua senha nova é: "+ senha, email);
                    }
            else{
                out.println(email);
            }
            }
           
                        
            
            
       %>
        </form>
        </div>
        <div class="row" style="height: 30px";></div>
        <form action="./index.jsp" method="post">
            
    <input type="submit" name="retry" value="Voltar" />
</form>
        <div class="row" style="height: 30px";></div>
         <div class="row" style="height: 30px; background-color:#85bfac"></div>
    </body>
</html>
