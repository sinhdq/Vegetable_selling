<%-- 
    Document   : accountmanager
    Created on : Oct 27, 2022, 4:43:49 PM
    Author     : SINHDQ
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Account Manager</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>
    <body>
      
        <%@ include file="headerad.jsp" %> 
          <div id="preloder">
            <div class="loader"></div>
        </div>
        <section class="hero hero-normal">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="hero__search">
                            <div class="hero__search__form">
                                <form action="admin?s=accountmanager" method = "post">
                                    <div class="hero__search__categories">
                                        All Account
                                        <span class="arrow_carrot-down"></span>
                                    </div>
                                    <input type="text" placeholder="Enter Full Name" name="fname">
                                    <button type="submit" class="site-btn">SEARCH</button>
                                </form>
                            </div>
                         
                        </div>
                    </div>
                </div>
            </div>
        </section>
                <section class="breadcrumb-section set-bg" data-setbg="image/breadcrumb.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>ACCOUNT MANAGER</h2>
                             
                        </div>
                    </div>
                </div>
            </div>
        </section>
                <br>
        <div class="container">         
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>User Name</th>
                        <th>Active</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="item" items="${sessionScope.listacc}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.fullName}</td>
                        <td>${item.email}</td>
                        <td>${item.phone}</td>
                        <td>${item.address}</td>
                        <td>${item.username}</td>
                        <td>
                            <c:if test="${item.active == false}">
                            <a href="admin?s=updateactive&id=${item.id}" style="color:Tomato;">Disable</a>
                            </c:if>
                            <c:if test="${item.active == true}">
                            <a href="admin?s=updateactive&id=${item.id}" style="color:DodgerBlue;">Enable</a>
                            </c:if>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
