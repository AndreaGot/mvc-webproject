<%-- 
    Document   : GroupCreationPage
    Created on : 26-gen-2014, 22.31.27
    Author     : ANDre1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Pragma" content="no-cache"> 
        <meta http-equiv="Cache-Control" content="no-cache"> 
        <meta http-equiv="Expires" content="Sat, 01 Dec 2012 00:00:00 GMT">
        <link href='bootstrap/css/bootstrap.css' rel='stylesheet' type='text/css' >
        <title>CreaGruppoServlet</title>
    </head>
    <body>

        <% String username = (String) session.getAttribute("user");
            if (username == null || username.equals(null)) {

                request.setAttribute("message", "non hai effettuato il login!");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }

        %>


        <div class="jumbotron">
            <h1>CREA GRUPPO</h1>




            <form action='ControllerGruppoServlet' method='POST'>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type='text' class="form-control" id="inputPassword3" placeholder="Nome Gruppo" name='creaGruppoTextbox' value='Inserire nome gruppo'>
                        <input type="hidden" value="confermagruppo" name="azione" />
                    </div>
                </div>
                <div>
                    <label id="ceck" for="pubblico">Gruppo Pubblico </label> <input type="checkbox" name="public" id="pubblico"/>
                </div>
                <div class='modifica_bottone'>
                    <input type='submit' class="btn btn-primary" value='crea' name='crea' >
                </div>

            </form>




        </div>
    </body>
</html>
