<%-- 
    Document   : GroupNewNamePage
    Created on : 28-gen-2014, 23.03.42
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
        <title>Servlet CambiaNomeServlet</title>
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

            <h1> MODIFICA NOME</h1>

            <form action='ControllerAmministrazioneServlet' method='POST'>
                <input type='hidden' name='id' value='<%= request.getParameter("id")%>'>
                <input type="hidden" value="nuovonome" name="azione" />
                

                    <div class="form-group">
                        <label  for="inputEmail3" class="col-sm-2 control-label" id="credenziali">Inserire nuovo nome</label>
                        <div class="col-sm-10">
                            <input type='text' class="form-control" id="inputEmail3" placeholder="Cambia Nome" name='nome' value='Inserisci il nome'>
                        </div>
                    </div>

                
                
                    <label for="pubblico">Gruppo Pubblico</label> <input type="checkbox" name="public" id="pubblico"/>
                
                    <div id="ceck" class="modifica_bottone">
                    <input type='submit' class="btn btn-primary" value='Modifica nome'>
                    </div>

            </form>


        </div>
    </body>
</html>
