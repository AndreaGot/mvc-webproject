<%-- 
    Document   : GroupListPage
    Created on : 26-gen-2014, 21.48.08
    Author     : ANDre1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>


    <head>
        <link href='bootstrap/css/bootstrap.css' rel='stylesheet' type='text/css' >
        <title>Servlet GroupServlet</title>
    </head>
    <body>

        <div class='panel panel-default' id='TitoloGruppo'>
            I MIEI GRUPPI ${userid}
        </div>

        <div class='content'>


            <c:if test="${empty listaGruppi}">
                <div class='inviti'>
                    <h3> Non appartieni a nessun gruppo in questo momento! </h3>
                </div>
            </c:if>
            <c:forEach var="g" items="${listaGruppi}"> 
                <div class='gruppo_blocco'>
                    <div class='nome_gruppo'>
                        <h3> ${g.nome} </h3>
                    </div>
                    <div class='gestisci_gruppo'>
                        <div class='vedi_gruppo'>
                            <form action='ControllerGruppoServlet' method='POST' >
                                <input type="hidden" value="listapost" name="azione" />
                                <input type='hidden' name='view' value='${g.id}'>
                                <input type='submit' value='Vedi Gruppo'>
                            </form>
                        </div>
                        <c:if test="${g.proprietario == userid}">
                            <div class='amministra_gruppo'>
                                <form action='ControllerAmministrazioneServlet' method='POST' >
                                    <input type="hidden" value="amministra" name="azione" />
                                    <input type='hidden' name='id' value=' ${g.id}'>
                                    <input type='submit' value='Amministra'>
                                </form>
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class='torna_gruppo'>
            <form action='CommonFunctionsServlet' method = 'POST'>
                <input type="hidden" value="home" name="azione" />
                <input type='submit' value='Torna alla home'>
            </form>
        </div>
    </body>
</html>
