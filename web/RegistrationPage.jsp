<%-- 
    Document   : home
    Created on : 28-dic-2013, 23.35.14
    Author     : ANDre1
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Pragma" content="no-cache"> 
        <meta http-equiv="Cache-Control" content="no-cache"> 
        <meta http-equiv="Expires" content="Sat, 01 Dec 2012 00:00:00 GMT">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
        <title>Registrati!</title>
    </head>
    <body>

        <div class="jumbotron">
             
            <h3>Benvenuto, compila il form per registrarti!</h3>
             
            <form class="form-horizontal" id="re_page" role="form" name="form_completo" action="ControllerServlet" method="POST">
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label" id="registra">Inserisci username</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputPassword3" placeholder="Password" name="username" value="username" />
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label" id="registra">Inserisci password</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputPassword3" placeholder="Password" name="password" value="password" />
                    </div>
                </div>



                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label" id="registra2">Inserisci nome completo</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputPassword3" placeholder="Password" name="nome_completo" value="nome completo" />
                    </div>
                </div>



                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label" id="registra3">Inserisci e-mail</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputPassword3" placeholder="Password" name="email" value="email" />
                    </div>
                </div>


                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label" id="registra3">Inserisci avatar</label>

                    <input type="file" name="avatar" id="avatar">
                    <input type="hidden" value="SalvaDatiRegistrazione" name="azione" />
                    <div class="col-sm-10">
                        <input type="submit" class="btn btn-primary" id="boh" name="conferma" value="conferma!" />
                    </div>
                </div>
            </form>
        </div>




    </body>
</html>
