<%-- 
    Document   : index
    Created on : 11-nov-2013, 13.43.43
    Author     : ANDre1
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Pragma" content="no-cache"> 
        <meta http-equiv="Cache-Control"      content="no-cache"> 
        <meta http-equiv="Expires" content="Sat, 01 Dec 2012 00:00:00 GMT">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <form name="form1" action="ControllerServlet" method="POST">

            <input type="text" name="username"   value="username" />
            <input type="password" name="password" autocomplete="off" value="password" />
            <input type="hidden" value="accedi" name="azione" />

            <%
                if (request.getAttribute("message") == null) {
                    out.println(" ");
                } else {
                    out.println(request.getAttribute("message"));

                }

            %> 


            <input type="submit" value="ACCEDI" name="ok" />

        </form>

        <form name="registrazione" action="ControllerServlet" method="POST" >

            <input type="hidden" value="registra" name="azione" />
            <input type="submit" value="REGISTRATI" name="registrazione" />

        </form>
    </body>
</html>
