<%-- 
    Document   : ModerationPage
    Created on : 3-feb-2014, 1.03.47
    Author     : ANDre1
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="<c:url value="/css/jquery.dataTables.css"/>"/>
        <script type="text/javascript" src='<c:url value="/js/jquery.js"/>'></script>
        <script type="text/javascript" src='<c:url value="/js/jquery.dataTables.min.js"/>'></script>
        <script>
            $(document).ready(function() {
                $("#table_id").dataTable();
            });
        </script>
    </head>
    <body>



        <table id="table_id">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Numero Partecipanti</th>
                    <th>Pubblico?</th>
                    <th>Numero Post</th>
                    <th>Vai al gruppo</th>
                    <th>Chiudi Gruppo </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="p" items="${modtable}">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.nome}</td>
                        <td>${p.utenti}</td>
                        <td>${p.pubblico}</td>
                        <td>${p.post}</td>
                        <td>
                            <div class='gestisci_gruppo'>
                                <div class='vedi_gruppo'>
                                    <form action='ControllerGruppoServlet' method='POST' >
                                        <input type="hidden" value="listapostpubblici" name="azione" />
                                        <input type='hidden' name='view' value='${p.id}'>
                                        <input type='submit' value='Vedi Gruppo'>
                                    </form>
                                </div>
                            </div>
                        </td>
                        <td>
                            <c:if test="${p.chiuso == false}">
                                <div class='gestisci_gruppo'>
                                    <div class='vedi_gruppo'>
                                        <form action='ControllerGruppoServlet' method='POST' >
                                            <input type="hidden" value="chiudigruppo" name="azione" />
                                            <input type='hidden' name='view' value='${p.id}'>
                                            <input type='submit' value='Chiudi Gruppo'>
                                        </form>
                                    </div>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>



    </body>
</html>
