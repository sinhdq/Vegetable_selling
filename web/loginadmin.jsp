<%-- 
    Document   : loginadmin
    Created on : Oct 24, 2022, 9:32:39 PM
    Author     : SINHDQ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Assistant:400,700" rel="stylesheet"><link rel="stylesheet" href="css/loginstyle.css">
        <title>Login</title>
    </head>
    <body>

        <div class="login-wrap">

            <div class="login-html">
                 <h2>SMARKET WELLCOME BACK</h2>
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab"></label>
                <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab"></label>
                <div class="login-form" >

                    <form action="admin?s=loginadmin" method="post">
                        <div class="sign-in-htm">
                            <div class="group">
                                <label for="user" class="label">Username</label>
                                <input id="user" type="text" class="input" name = "username">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Password</label>
                                <input id="pass" type="password" class="input" data-type="password" name = "password">
                            </div>

                            <div class="group">
                                <input type="submit" class="button" value="Sign In">
                            </div>
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <a href="admin?s=forgotpassword">Forgot Password?</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
