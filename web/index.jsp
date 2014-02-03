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
        <title>JSP Page</title>
    </head>
    <body>
                <form class="form-horizontal" id="pd" role="form" name="entra" action="CommonFunctionsServlet" method="POST" >

            <input type="hidden" value="entra" name="azione" />
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-primary" value="Entra" name="entra" />
                </div>
            </div>
        </form>
    </body>
</html>
