<%-- 
    Document   : GroupUserInvitation
    Created on : 28-gen-2014, 23.34.38
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
        <title>Servlet InvitaUtenteServlet</title>
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
            <h1>INVITA UTENTE</h1>
        

        

            <form action='ControllerAmministrazioneServlet' method='POST'>
                <input type="hidden" value="nuovoinvito" name="azione" />
                <input type='hidden' name='idgruppo' value='<%= request.getParameter("id")%>'>
                
                   
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label" id="credenziali">Inserire Username Utente</label>
                        <div class="col-sm-10">
                    <input type='text' class="form-control" id="inputEmail3" placeholder="Username Utente" name='nome' value='Username utente'>
                        </div>
                    </div>
                
               
                
                
                    <input type='submit' class="btn btn-primary" value='Invita utente'>
                
            </form>

            
    </div>
    </body>
</html>

