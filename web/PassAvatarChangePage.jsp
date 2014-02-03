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
        <title>JSP Page</title>
    </head>
    <body>
        
                <% String username = (String) session.getAttribute("user");
            if (username == null || username.equals(null)) {

                request.setAttribute("message", "non hai effettuato il login!");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }

        %>
        <div class='content'>

            <form action='ControllerGruppoServlet' method='POST'>
                <div class='newpass'>
                    <input type='text' name='cambiaPass' value='Inserire nuova password'>
                    <input type="hidden" value="cambiapass" name="azione" />
                </div>
                <div class='modifica_bottone'>
                    <input type='submit' value='Cambia Password' name='crea' >
                </div>

            </form>
            <div class='content'>

                <form action='ControllerGruppoServlet' method='POST'>
                    <div class='newavatar'>
                        <input type='text' name='cambiaAvatar' value='Inserire nuovo avatar'>
                        <input type="hidden" value="cambiaavatar" name="azione" />
                    </div>
                    <div class='modifica_bottone'>
                        <input type='submit' value='cambia' name='Cambia Avatar' >
                    </div>

                </form>
                </body>
                </html>
