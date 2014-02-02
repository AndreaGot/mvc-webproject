<%-- 
    Document   : index
    Created on : 11-nov-2013, 13.43.43
    Author     : ANDre1
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Pragma" content="no-cache"> 
        <meta http-equiv="Cache-Control" content="no-cache"> 
        <meta http-equiv="Expires" content="Sat, 01 Dec 2012 00:00:00 GMT">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        
        <form class ="form-horizontal" role="form" name="form1" action="ControllerServlet" method="POST">


            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">username</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputEmail3" placeholder="username" name="username">
                </div>
            </div>


            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="password" autocomplete="off">
                </div>
            </div>

            <input type="hidden" value="accedi" name="azione" />

            
            
            <%
               
                if (request.getAttribute("message") == null) {
                    out.println(" ");
                } else {
                     out.println("<div class='alert alert-danger'>");
                    out.println(request.getAttribute("message"));
out.println("</div>");
                }
                
            %> 
            



            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-primary" value="Accedi" name="ok" />
                </div>
            </div>

        </form>



        <form class="form-horizontal" id="re" role="form" name="registrazione" action="ControllerServlet" method="POST" >

            <input type="hidden" value="registra" name="azione" />
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-primary" value="Registrati" name="registrazione" />
                </div>
            </div>


        </form>
        <form class="form-horizontal" id="pd" role="form" name="password" action="ControllerServlet" method="POST" >

            <input type="hidden" value="recupera" name="azione" />
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-primary" value="Password dimenticata?" name="recupera" />
                </div>
            </div>
        </form>





    </body>
</html>
