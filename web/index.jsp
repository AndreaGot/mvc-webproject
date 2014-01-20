<%-- 
    Document   : index
    Created on : 28-dic-2013, 23.22.34
    Author     : ANDre1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Benvenuto! Effettua il Login.</h1>
         <input type="text" name="username" value="user" />
            <input type="text" name="password" value="pass" />
            <input type="submit" value="ok" name="ok" />

            
            <form name="form_registrazione" action="ControllerServlet" method="POST">

                <%session.setAttribute("azione", 1); %>
            <input type="submit" value="registrati!" name="registrazione" />

        </form>
        

    </body>
</html>
