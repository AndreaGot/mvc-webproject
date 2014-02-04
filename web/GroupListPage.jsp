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
        <meta http-equiv="Pragma" content="no-cache"> 
        <meta http-equiv="Cache-Control" content="no-cache"> 
        <meta http-equiv="Expires" content="Sat, 01 Dec 2012 00:00:00 GMT">
        <link href='bootstrap/css/bootstrap.css' rel='stylesheet' type='text/css' >
        <title>Servlet GroupServlet</title>
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


            
                <h1>I MIEI GRUPPI:</h1>
           





            <c:if test="${empty listaGruppi}">
               
                    <h3> Non appartieni a nessun gruppo in questo momento! </h3>
                
            </c:if>
                    

                    <div class="gruppi">       
             <c:forEach var="g" items="${listaGruppi}"> 
                <div class='gruppo_blocco'>
                    <div class='nome_gruppo'>
                        <h3> ${g.nome} </h3>
                    </div>
                    <div class='gestisci_gruppo'>
                        <c:if test="${g.chiuso == false}">
                            <div class='vedi_gruppo'>
                                <form action='ControllerGruppoServlet' method='POST' >
                                    <input type="hidden" value="listapost" name="azione" />
                                    <input type='hidden' name='view' value='${g.id}'>
                                    <input type='submit'class="btn btn-primary" value='Vedi Gruppo'>
                                </form>
                            </div>
                        </c:if>
                        <c:if test="${g.chiuso == true}">
                            <div class='gestisci_gruppo'>
                                <div class='vedi_gruppo'>
                                    <form action='ControllerGruppoServlet' method='POST' >
                                        <input type="hidden" value="listapostpubblici" name="azione" />
                                        <input type='hidden' name='view' value='${g.id}'>
                                        <input type='submit' class="btn btn-primary" value='Vedi Gruppo'>
                                    </form>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${g.proprietario == userid}">
                            <div class='amministra_gruppo'>
                                <form action='ControllerAmministrazioneServlet' method='POST' >
                                    <input type="hidden" value="amministra" name="azione" />
                                    <input type='hidden' name='id' value=' ${g.id}'>
                                    <input type='submit' class="btn btn-primary" value='Amministra'>
                                </form>
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
                    </div>
            <c:forEach var="g" items="${listaGruppiPubblici}"> 
                <div class='gruppo_blocco'>
                    <div class='nome_gruppo'>
                        <h3> ${g.nome} </h3>
                    </div>
                    <div class='gestisci_gruppo'>
                        <div class='vedi_gruppo'>
                            <form action='ControllerGruppoServlet' method='POST' >
                                <input type="hidden" value="listapostpubblici" name="azione" />
                                <input type='hidden' name='view' value='${g.id}'>
                                <input type='submit' class="btn btn-primary" value='Vedi Gruppo'>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>

            
        </div>
    </body>
</html>
