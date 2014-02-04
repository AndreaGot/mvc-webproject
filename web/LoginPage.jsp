<%-- 
    Document   : LoginPage
    Created on : 22-gen-2014, 17.48.13
    Author     : ANDre1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Pragma" content="no-cache"> 
        <meta http-equiv="Cache-Control" content="no-cache"> 
        <meta http-equiv="Expires" content="Sat, 01 Dec 2012 00:00:00 GMT">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

        <title>LoginPage</title>
    </head>
    <body>


        <% String username = (String) session.getAttribute("user");
            if (username == null || username.equals(null) || username == "") {

                request.setAttribute("message", "non hai effettuato il login!");
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }


        %>

        <div class="jumbotron">


            <h1> Benvenuto, <%= session.getAttribute("user")%>! </h1>
            <h3> Il tuo ultimo accesso è stato: <%= session.getAttribute("lastLogin")%>! </h3>
            <h3> ${message} </h3>


            <div class='menu_button'>
                <div class='gruppo_button'>
                    <form action='ControllerGruppoServlet' method = 'POST'>
                        <input type="hidden" value="vedigruppo" name="azione" />
                        <input type='submit' class="btn btn-primary" value='I tuoi gruppi'>
                    </form>
                </div>
                <div class='creagruppo_button'>
                    <form action='ControllerGruppoServlet' method='POST'>
                        <input type="hidden" value="creagruppo" name="azione" />
                        <input type='submit' class="btn btn-primary" value='Crea un gruppo'>
                    </form>
                </div>
                <div class='logout_button'>
                    <form action='ControllerGruppoServlet' method='POST'>
                        <input type="hidden" value="logout" name="azione" />
                        <input type='submit' class="btn btn-primary" value='Logout'>
                    </form>
                    <br>
                </div>





            </div>
<div class='menu_button'>
            <div class='cred_button'>
                <form action='ControllerGruppoServlet' method = 'POST'>
                    <input type="hidden" value="cambiacredenziali" name="azione" />
                    <input type='submit' class="btn btn-primary" value='Cambia Credenziali'>
                </form>
            </div>

            <c:if test="${utente.moder == true}">
                <div class='cred_button'>
                    <form action='ControllerServlet' method = 'POST'>
                        <input type="hidden" value="modera" name="azione" />
                        <input type='submit' class="btn btn-primary" value='Pannello Moderazione'>
                    </form>
                </div>

            </c:if>
</div>


            <div class='titolo_inviti'>
                <h3>LISTA DEI TUOI INVITI:</h3>
            </div>
            <c:if test="${empty listaInviti}">
                <div class='inviti'>
                    <h3> Non hai nessun invito in questo momento! </h3>
                </div>
            </c:if>
            <div class='inviti_blocco'>

                <c:forEach var="i" items="${listaInviti}">    
                    <div class='lista_inviti'>
                        Un invito da ${i.owner} per il gruppo ${i.nomeGruppo}
                    </div>
                    <form action='ControllerGruppoServlet' method='POST'>
                        <input type='hidden' name='idgruppo' value="${i.idGruppo}">

                        <div class='accetta_invito'>
                            <input type='submit' class="btn btn-primary" name='risposta' value='Accetta'>
                        </div>
                        <div class='rifiuta_invito'>
                            <input type='submit' class="btn btn-primary" name='risposta' value='Rifiuta'>
                        </div>
                        <input type="hidden" value="rispostainvito" name="azione" />
                    </form>
                </c:forEach>  

                <div class='titolo_inviti'>
                    <h3>LISTA AGGIORNAMENTI:</h3>
                </div>
                <c:forEach var="i" items="${listaUpdate}">    
                    <div class='lista_inviti'>
                        ${i.nomeGruppo} ha avuto delle variazioni!
                    </div>
                </c:forEach>  

                <br>
            </div>

        </div>











    </body>
</html>


