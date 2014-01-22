<%-- 
    Document   : LoginPage
    Created on : 22-gen-2014, 17.48.13
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
        <h1>Hello World!</h1>
    <body>

        <div class='panel panel-default' id='titolohome'>
            <div class='saluti'>
                <div id='welcome'>
                    <h3> Benvenuto  " + session.getAttribute("user") + "." + "</h3>
                </div>

            </div>
        </div>


        <div class='content'>

            <div class='menu_button'>
                <div class='gruppo_button'>
                    <form action='GroupServlet' method = 'POST'>
                        <input type='submit' value='I tuoi gruppi'>
                    </form>
                </div>
                <div class='creagruppo_button'>
                    <form action='CreaGruppoServlet' method='POST'>
                        <input type='submit' value='Crea un gruppo'>
                    </form>
                </div>
                <div class='logout_button'>
                    <form action='LogoutServlet' method='POST'>
                        <input type='submit' value='Logout'>
                    </form>
                    <br>
                </div>




            </div>
            <div class='titolo_inviti'>
                LISTA DEI TUOI INVITI:
            </div>

            <div class='inviti'>
                <h3> Non hai nessun invito in questo momento! </h3>
            </div>

            <div class='inviti_blocco'>
                <div class='lista_inviti'>
                    Un invito da " + i.owner + " per il gruppo " + i.nomeGruppo);
                </div>
                <form action='InvitoRispostaServlet' method='POST'>
                    <input type='hidden' name='idgruppo' value='" + i.idGruppo + "'>

                    <div class='accetta_invito'>
                        <input type='submit' name='risposta' value='Accetta'>
                    </div>
                    <div class='rifiuta_invito'>

                        <input type='submit' name='risposta' value='Rifiuta'>
                    </div>
                </form>
                <br>
            </div>




        </div>

    </body>
</html>


