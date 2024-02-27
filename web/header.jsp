<%-- 
    Document   : header
    Created on : Oct 4, 2022, 9:31:19 AM
    Author     : SINHDQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__left">
                        <ul>
                            <li><i class="fa fa-envelope"></i>ssmarket8989@gmail.com</li>
                                <c:if test="${!empty sessionScope.account}">
                                <li>Welcome back ${sessionScope.account.fullName}</li>
                                </c:if>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__right">
                        <div class="header__top__right__social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-linkedin"></i></a>
                            <a href="#"><i class="fa fa-pinterest-p"></i></a>
                        </div>
                        <div class="header__top__right__auth">
                            <c:if test="${empty sessionScope.account}">
                                <a href="home?s=login"><i class="fa fa-user"></i>Login</a>
                            </c:if>
                        </div>
                        <c:if test="${!empty sessionScope.account}">
                        <div class="header__top__right__language">
                            <div ><i class="fa fa-user"></i>    
                                        <div>${sessionScope.account.username}</div>
                            </div>
                            <span class="arrow_carrot-down"></span>
                            <ul>                           
                                <li><a href="home?s=logout">Logout</a></li>
                                <li><a href="home?s=profile">Account</a></li>
                            </ul>
                        </div>
                         </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="header__logo">
                    <a href="home"><img src="image/Logo.png" alt="" width="100" height="80"></a>
                </div>
            </div>
            <div class="col-lg-6">
                <nav class="header__menu">
                    <ul>
                        <li><a href="home">Home</a></li>
                        <li><a href="shop">Shop</a></li>
                        <li><a href="blog">Blog</a></li>
                        <li><a href="contact">Contact</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-3">
                <div class="header__cart">
                    <ul>
                        <li><a href="shop?s=cart"><i class="fa fa-shopping-bag"></i> <span>${sessionScope.quantityproduct}</span></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="humberger__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>
