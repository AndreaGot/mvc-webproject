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
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="jumbotron">
            <form name="form1" action="ControllerServlet" method="POST">
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label" id="recupera">Inserire Nome Utente:</label>
                    <div class="col-sm-10" id="b_recupera">
                        <input type="text" class="form-control" id="inputPassword3" placeholder="Nome Utente" name="nomeutente" value="Nome utente" />
                        <input type="hidden" value="inviamail" name="azione" />

                        <input type="submit" id="c_recupera" class="btn btn-primary" value="Recupera" name="ok" />
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
