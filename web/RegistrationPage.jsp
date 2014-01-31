<%-- 
    Document   : home
    Created on : 28-dic-2013, 23.35.14
    Author     : ANDre1
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Pragma" content="no-cache"> 
        <meta http-equiv="Cache-Control"      content="no-cache"> 
        <meta http-equiv="Expires" content="Sat, 01 Dec 2012 00:00:00 GMT">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <title>Registrati!</title>
    </head>
    <body>
        <h1>Benvenuto, compila il form per registrarti!</h1>

        <form name="form_completo" action="ControllerServlet" method="POST">
            <p>Inserisci Username.</p>
            <input type="text" name="username" value="username" />
            <p>Inserisci Password.</p>
            <input type="text" name="password" value="password" />
            <p>Inserisci nome completo.</p>
            <input type="text" name="nome_completo" value="nome completo" />
            <p>Inserisci Email.</p>
            <input type="text" name="email" value="email" />

            <input type="hidden" value="SalvaDatiRegistrazione" name="azione" />
            <input type="submit" name="conferma" value="conferma!" />
        </form>





    </body>
</html>
