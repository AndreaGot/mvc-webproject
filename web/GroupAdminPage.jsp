<%--
    Document   : GroupAdminPage
    Created on : 28-gen-2014, 15.29.33
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
        <title>Servlet AmministraGruppoServlet</title>
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
            AMMINISTRA GRUPPO
        </div>
        <div class='content'>

            <div class='bottoni_amministra'>
                <form action='ControllerAmministrazioneServlet' method='POST'>
                    <input type='hidden' name='id' value='<%= request.getParameter("id")%>'>
                    <input type="hidden" value="cambianome" name="azione" />
                    <div class='modifica_amministra'>
                        <input type='submit' value='Modifica nome'>
                    </div>
                </form>

                <form action='ControllerAmministrazioneServlet' method='POST'>
                    <input type='hidden' name='id' value='<%= request.getParameter("id")%>'>
                    <input type="hidden" value="invitautente" name="azione" />
                    <div class='invita_amministra'>
                        <input type='submit' value='Invita utente'>
                    </div>
                </form>

                <form action='ControllerAmministrazioneServlet' method='POST'>
                    <input type='hidden' name='id' value='<%= request.getParameter("id")%>'>
                    <input type="hidden" value="creareport" name="azione" />
                    <div class='creareport_amministra'>
                        <input type='submit' value='Crea report'>
                    </div>
                </form>

            </div>

            <div class='bottoni_indietro'>
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
    </body>
</html>