<%-- 
    Document   : GroupPage
    Created on : 28-gen-2014, 15.10.29
    Author     : ANDre1
--%>

<%@page import="java.awt.Image"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.RenderingHints"%>
<%@page import="java.awt.Graphics2D"%>
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
        <% String username = (String) session.getAttribute("user");
            if (username == null || username.equals(null)) {

                request.setAttribute("message", "non hai effettuato il login!");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }

        %>

        <div class="jumbotron">
            <h1> Gruppo</h1>



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
                </thead>
                <tbody>
                    <c:if test="${empty listaPost}">
                    <div class='post'>
                        <h3> Non ci sono post in questo momento! </h3>
                    </div>
                </c:if>
                <c:forEach var="p" items="${listaPost}">

                    <tr class='active'>
                        <td>
                            <div class="autore">
                                ${p.autore} 
                            </div>

                            <img src ="<c:url value="${p.link}"/>" height ="100px" width="100px"/>
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

            <form action='ControllerGruppoServlet' method='POST'>

                <div class='inserisci_commento'>

                    <div class="form-group">
                       
                        <div class="col-sm-10">

                            <input type='text' class="form-control" id="inputEmail3" placeholder="Inserisci Commento" name='contenuto'  required  autocomplete='off' />
                        </div>
                    </div>


                </div> 
                <div class='aggiungi_button'>
                    <input type='submit' class="btn btn-primary" name='AggiungiPost' value='Aggiungi'/>
                </div>
                <input type='hidden' name='view' value='<%= request.getParameter("view")%>'>
                <input type="hidden" value="nuovopost" name="azione" />
            </form>



            <form enctype='multipart/form-data' action='UploadController' method='POST' >
                <input type='hidden' name='view' value='<%= request.getParameter("view")%>'>
                <input type="hidden" value="nuovoupload" name="azione" />

                <div class='upload_button'>
                    <input type='submit' class="btn btn-primary" value='Upload'>
                </div>

                <div class='sceglifile'>
                    <input  required type='file' name='file'>
                </div>


            </form>



        </div>
    </body>
</html>