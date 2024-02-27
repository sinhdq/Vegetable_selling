<%-- 
    Document   : loginCustomer
    Created on : Oct 4, 2022, 11:03:20 AM
    Author     : SINHDQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Assistant:400,700" rel="stylesheet">
        <link rel="stylesheet" href="css/loginstyle.css">
        <title>Login</title>
    </head>
    <body>
        <div class="login-wrap">
            <div class="login-html">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Login</label>
                <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Register</label>
                <div class="login-form">
                    <form action="home?s=login" method="post">
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
                                <a href="home?s=forgotpassword">Forgot Password?</a>
                            </div>
                        </div>
                    </form>
                    <form action="home?s=register" method="post">
                        <h3 style="color:Tomato;">${messregister}</h3>
                        <div class="sign-up-htm">
                            <div class="group">
                                <label for="user" class="label">Full Name</label>
                                <input id="user" type="text" class="input" name ="fullname">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Email Address</label>
                                <input id="pass" type="text" class="input" name="email">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Phone</label>
                                <input id="pass" type="text" class="input" name="phone">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Address</label>
                                <input id="pass" type="text" class="input" name="address">
                            </div>
                            <div class="group">
                                <label for="user" class="label">User Name</label>
                                <input id="user" type="text" class="input" name ="username">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Password</label>
                                <input id="pass" type="password" class="input" data-type="password" name = "password">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Repeat Password</label>
                                <input id="pass" type="password" class="input" data-type="password" name="repassword">
                            </div>
                            <div class="group">
                                    <input type="submit" class="button" value="Sign Up" name="submit">
                            </div>
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <label for="tab-1">Already Member?</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
