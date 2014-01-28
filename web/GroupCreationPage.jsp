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
        <link href='bootstrap/css/bootstrap.css' rel='stylesheet' type='text/css' >
        <title>CreaGruppoServlet</title>
    </head>
    <body>

        <div class='panel panel-default' id='TitoloGruppo'>
            CREA GRUPPO
        </div>

        <div class='content'>

            <form action='ControllerGruppoServlet' method='POST'>
                <div class='modifica_textbox'>
                    <input type='text' name='creaGruppoTextbox' value='Inserire nome gruppo'>
                    <input type="hidden" value="confermagruppo" name="azione" />
                </div>
                <div class='modifica_bottone'>
                    <input type='submit' value='crea' name='crea' >
                </div>

            </form>


            <div class='torna_gruppo'>
                <form action='CommonFunctionsServlet' method = 'POST'>
                    <input type="hidden" value="home" name="azione" />
                    <input type='submit' value='Torna alla home'>
                </form>
            </div>
        </div>

    </body>
</html>
