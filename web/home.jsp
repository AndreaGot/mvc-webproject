<%-- 
    Document   : home
    Created on : 28-dic-2013, 23.35.14
    Author     : ANDre1
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="salva_nome" class="beans.salva_nome" scope="page"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>aaaaaaa</title>
    </head>
    <body>
        <h1>HOMEEEEE!</h1>
        
    
        
        <input type="text" name="nome" value="nome" />
   
    <% out.println(salva_nome.salva_in_db("nome"));%>
        
    
        
    
    </body>
</html>
