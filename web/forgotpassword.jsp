<%-- 
    Document   : forgotpassword
    Created on : Oct 27, 2022, 12:44:57 AM
    Author     : SINHDQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Assistant:400,700" rel="stylesheet"><link rel="stylesheet" href="css/loginstyle.css">
        <title>Forgot Password</title>
    </head>
    <body>

        <div class="login-wrap">

            <div class="login-html">
                 <h2>FORGOT PASSWORD</h2>
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab"></label>
                <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab"></label>
                <div class="login-form" >

                    <form action="home?s=forgotpassword" method="post">
                        <div class="sign-in-htm">
                            <div class="group">
                                <label for="user" class="label">Username</label>
                                <input id="user" type="text" class="input" name = "username">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Email</label>
                                <input id="pass" type="email" class="input" data-type="mail" name = "mail">
                            </div>

                            <div class="group">
                                <input type="submit" class="button" value="Submit" name="submit">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
