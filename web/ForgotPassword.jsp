<%-- 
    Document   : ForgotPassword
    Created on : 1-feb-2014, 14.09.45
    Author     : ANDre1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="form1" action="ControllerServlet" method="POST">

            <input type="text" name="nomeutente" value="Nome utente" />
            <input type="hidden" value="inviamail" name="azione" />

            <input type="submit" value="Recupera" name="ok" />

        </form>
    </body>
</html>
