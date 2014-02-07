<%-- 
    Document   : PassAvatarChangePage
    Created on : 2-feb-2014, 22.45.14
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
            <% String username = (String) session.getAttribute("user");
                if (username == null || username.equals(null)) {

                    request.setAttribute("message", "non hai effettuato il login!");
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }

            %>


            <form action='ControllerGruppoServlet' method='POST'>
                <div class='newpass'>

                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label" id="credenziali">Inserisci la nuova password: </label>
                        <div class="col-sm-10" >
                            <input type='password' class="form-control" id="inputPassword3" placeholder="Password" name='cambiaPass' required >
                            <input type="hidden" value="cambiapass" name="azione" />
                        </div>
                    </div>
                </div>
                <div class='modifica_bottone'>
                    <input type='submit' class="btn btn-primary" value='Cambia Password' name='crea' >
                </div>

            </form>


            <form action='ControllerCambiaAvatarServlet' enctype='multipart/form-data' method='POST'>
                <div class='newavatar'>

                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label" id="credenziali">Inserisci il nuovo avatar: </label>
                        <div class="col-sm-10" >
                            <input type='file' class="form-control" id="inputPassword3" placeholder="Avatar" name='cambiaAvatar' required>
                        </div>
                    </div>
                </div>
                <div class='modifica_bottone'>
                    <input type='submit' class="btn btn-primary" value='Cambia Avatar' name='Cambia Avatar' >
                </div>

            </form>



        </div>
    </body>
</html>
