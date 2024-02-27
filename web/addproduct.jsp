<%-- 
    Document   : addproduct
    Created on : Oct 25, 2022, 12:11:34 AM
    Author     : SINHDQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Add Product</title>

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

        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>
        <%@ include file="headerad.jsp" %>

        <section class="breadcrumb-section set-bg" data-setbg="image/breadcrumb.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>ADD PRODUCT</h2>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Checkout Section Begin -->
        <section class="checkout spad">
            <div class="container">
                <div class="checkout__form">
                    <form action="admin?s=addproduct" method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-lg-8 col-md-6">
                                <div class="row">
                                    <div class="col-lg-9">
                                        <div class="checkout__input">
                                            <p>Product Name<span>*</span></p>
                                            <input type="text" name = "pname" >
                                        </div>
                                    </div>
                                    <div class="col-lg-3">
                                        <div class="checkout__input">
                                            <p><a style="color:black;" href="admin?s=adddepartment">Department</a><span>*</span></p>
                                            <select name="department" class="checkout__input">
                                                <c:forEach var ="item" items="${sessionScope.listdepart}">
                                                    <option value="${item.id}">${item.departName}</option>
                                                </c:forEach>             
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-4">
                                        <div class="checkout__input">
                                            <p>Quantity<span>*</span></p>
                                            <input type="number" name = "quantity" value="1" min="1">
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="checkout__input">
                                            <p>Price<span>*</span></p>
                                            <input type="text" name = "price" >
                                        </div>
                                    </div>
                                    <div class="col-lg-4">
                                        <div class="checkout__input">
                                            <p>Image<span>*</span></p>
                                            <input accept=".jpg, .jpeg, .png, .svg" type="file" name = "image" >
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="checkout__input">
                                            <p>Description<span>*</span></p>
                                            <input type="text" name = "description">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-lg-4 col-md-6">
                                <div class="checkout__order">
                                    <button type="submit" class="site-btn" name="submit">SUBMIT</button>
                                    <button class="site-btn"><a style="color:white;" href="admin?s=productmanager">BACK</a></button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </section>

        <!-- Js Plugins -->
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