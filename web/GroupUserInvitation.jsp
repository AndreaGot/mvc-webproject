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

        <div class='panel panel-default' id='TitoloGruppo'>
            INVITA UTENTE
        </div>

        <div class='content'>

            <form action='ControllerAmministrazioneServlet' method='POST'>
                <input type="hidden" value="nuovoinvito" name="azione" />
                <input type='hidden' name='idgruppo' value='<%= request.getParameter("id")%>'>
                <div class='modifica_textbox'>
                    <input type='text' name='nome' value='Username utente'>
                </div>
                <div class='modifica_bottone'>
                    <input type='submit' value='Invita utente'>
                </div>
            </form>

            <div class='tasti_indietro'>
                <div class='torna_gruppo'>
                    <form action='CommonFunctionsServlet' method = 'POST'>
                        <input type="hidden" value="indietro" name="azione" />
                        <input type='submit' value='Torna ai tuoi gruppi'>
                    </form>
                </div>
                <div class='torna_gruppo'>
                    <form action='CommonFunctionsServlet' method = 'POST'>
                        <input type='hidden' name='view' value='<%= request.getParameter("view")%>'>
                        <input type="hidden" value="home" name="azione" />
                        <input type='submit' value='Torna alla home'>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

