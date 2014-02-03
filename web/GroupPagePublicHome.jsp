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
        <meta http-equiv="Pragma" content="no-cache"> 
        <meta http-equiv="Cache-Control" content="no-cache"> 
        <meta http-equiv="Expires" content="Sat, 01 Dec 2012 00:00:00 GMT">
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

               
            </div>

            <div class='tasti_indietro'>
                <div class='torna_gruppo'>
                    <form action='CommonFunctionsServlet' method = 'POST'>
                        <input type="hidden" value="entra" name="azione" />
                        <input type='submit' value='Torna al login'>
                    </form>
                </div>
                
            
            
            <% session.invalidate(); %>
    </body>
</html>