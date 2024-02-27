<%-- 
    Document   : headerad
    Created on : Oct 24, 2022, 8:49:42 PM
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
                                <c:if test="${!empty sessionScope.accountadmin}">
                                <li>Welcome back ${sessionScope.accountadmin.fullName}</li>
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
                        <c:if test="${!empty sessionScope.accountadmin}">
                        <div class="header__top__right__language">
                            <div ><i class="fa fa-user"></i>    
                                        <div>${sessionScope.accountadmin.username}</div>
                            </div>
                            <span class="arrow_carrot-down"></span>
                            <ul>                           
                                <li><a href="admin?s=logoutadmin">Logout</a></li>
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
            <div class="col-lg-2">
                <div class="header__logo">
                    <a href="admin"><img src="image/Logo.png" alt="" width="100" height="80"></a>
                </div>
            </div>
            <div class="col-lg-10">
                <nav class="header__menu">
                    <ul>
                        <li><a href="admin?s=productmanager">Product</a></li>
                        <li><a href="admin?s=accountmanager">Account</a></li>
                        <li><a href="admin?s=managerbill">Bill</a></li>
                        <li><a href="admin?s=contactmanager">Contact</a></li>
                        <li><a href="admin?s=blogmanager">Blog</a></li>
                        <li><a href="admin?s=history">History</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</header>
