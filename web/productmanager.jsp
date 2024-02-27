<%-- 
    Document   : productmanager
    Created on : Oct 24, 2022, 8:44:35 PM
    Author     : SINHDQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Product Manager</title>
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
        <div id="preloder">
            <div class="loader"></div>
        </div>
        <%@ include file="headerad.jsp" %> 
        <section class="hero hero-normal">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="hero__categories">
                            <div class="hero__categories__all">
                                <i class="fa fa-bars"></i>
                                <span>All Department</span>
                            </div>
                            <form action="admin?s=productmanager" method="post">
                                <ul>
                                    <c:forEach var ="item" items="${listdepart}">
                                        <li><a href="admin?s=productmanager&iddepart=${item.id}">${item.departName}</a></li>
                                        </c:forEach>
                                </ul>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="hero__search">
                            <div class="hero__search__form">
                                <form action="admin?s=productmanager" method = "post">
                                    <div class="hero__search__categories">
                                        All Product
                                        <span class="arrow_carrot-down"></span>
                                    </div>
                                    <input type="text" placeholder="Enter Name Product" name="namepro">
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
                            <h2>PRODUCT MANAGER</h2>
                             <a href="admin?s=addproduct" class="primary-btn">Add Product</a>
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
                        <th>Department</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="item" items="${listpro}">
                    <tr>
                        <td>${item.id}</td>
                        <c:forEach var ="items" items="${listdepart}">
                            <c:if test="${item.departID == items.id}">
                        <td>${items.departName}</td>
                        </c:if>
                        </c:forEach>
                        <td>${item.productName}</td>
                        <td>${item.price}</td>
                        <td>${item.quantity}</td>
                        <td>
                            <a href="admin?s=updateproduct&id=${item.id}" style="color:MediumSeaGreen;">Update</a> | 
                            <a href="admin?s=deleteproduct&id=${item.id}" style="color:MediumSeaGreen;">Delete</a> | 
                            <a href="admin?s=detailproduct&id=${item.id}" style="color:MediumSeaGreen;">Detail</a>
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
