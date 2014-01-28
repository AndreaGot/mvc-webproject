<%-- 
    Document   : GroupPage
    Created on : 28-gen-2014, 15.10.29
    Author     : ANDre1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='bootstrap/css/bootstrap.css' rel='stylesheet' type='text/css' >
        <title>Servlet VediGruppoServlet</title>
    </head>

    <body>
        <div>
            <div class='panel panel-default' id='TitoloGruppo'>
                Gruppo
            </div>
            <div class='content'>

                <table class='table table-striped'>

                    <thead>
                        <tr>
                            <th>
                                UTENTE
                            </th>
                            <th>
                                MESSAGGIO
                            </th>
                            <th>
                                DATA
                            </th>
                        </tr>
                    </thead
                    <tbody>
                        <c:if test="${empty listaPost}">
                        <div class='post'>
                            <h3> Non ci sono post in questo momento! </h3>
                        </div>
                    </c:if>
                    <c:forEach var="p" items="${listaPost}">

                        <tr class='active'>
                            <td>
                                ${p.autore}
                            </td>

                            <td class='contenuto'>
                                ${p.contenuto}
                            </td>

                            <td>
                                ${p.data}
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>

                <form action='InserisciPostServlet' method='POST'>

                    <div class='inserisci_commento'>
                        <input type='text' name='contenuto'   value='Scrivi il tuo commento' autocomplete='off' />
                    </div> 
                    <div class='aggiungi_button'>
                        <input type='submit' name='AggiungiPost' value='Aggiungi'/>
                    </div>
                    <input type='hidden' name='passaID' value='<%= request.getParameter("view")%>'>
                </form>



                <form enctype='multipart/form-data' method='POST' action='UploadFileServlet'>
                    <div class='upload_button'>
                        <input type='submit' value='Upload'>
                    </div>

                    <div class='sceglifile'>
                        <input class='scegli_button' type='file' name='file'>
                    </div>

                </form>
            </div>

            <div class='tasti_indietro'>
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