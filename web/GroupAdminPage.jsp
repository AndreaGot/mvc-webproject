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
        <link href='bootstrap/css/bootstrap.css' rel='stylesheet' type='text/css' >
        <title>Servlet AmministraGruppoServlet</title>
    </head>
    <body>

        <div class='panel panel-default' id='TitoloGruppo'>
            AMMINISTRA GRUPPO
        </div>
        <div class='content'>

            <div class='bottoni_amministra'>
                <form action='CambiaNomeServlet' method='POST'>
                    <input type='hidden' name='id' value='<%= request.getParameter("id") %>'>
                    <div class='modifica_amministra'>
                        <input type='submit' value='Modifica nome'>
                    </div>
                </form>

                <form action='InvitaUtenteServlet' method='POST'>
                    <input type='hidden' name='id' value='<%= request.getParameter("id") %>'>
                    <div class='invita_amministra'>
                        <input type='submit' value='Invita utente'>
                    </div>
                </form>

                <form action='CreaReportServlet' method='POST'>
                    <input type='hidden' name='id' value='<%= request.getParameter("id") %>'>
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
                        <input type="hidden" value="home" name="azione" />
                        <input type='submit' value='Torna alla home'>
                    </form>
                </div>
            </div>
    </body>
</html>