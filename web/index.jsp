<%-- 
    Document   : index
    Created on : 2-feb-2014, 23.21.20
    Author     : ANDre1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>

        
        <div class="jumbotron">

            <h1>Benvenuto!</h1>
            <p>accedi al nostro forum!</p>
            <form  name="entra" action="CommonFunctionsServlet" method="POST" >

                <input type="hidden" value="entra" name="azione" />


                <input type="submit" class="btn btn-success" value="Entra" name="entra" />


            </form>
            <%

            if (request.getAttribute("message") == null) {
                out.println(" ");
            } else {
                out.println("<div class='alert alert-danger' id='message'>");
                out.println(request.getAttribute("message"));
                out.println("</div>");
            }

        %> 
        </div>
    </body>
</html>
