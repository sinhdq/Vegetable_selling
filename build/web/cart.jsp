<%-- 
    Document   : cart
    Created on : Oct 6, 2022, 10:14:29 AM
    Author     : SINHDQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Cart</title>

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

        
        <%@ include file="header.jsp" %> 

<!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>
        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-section set-bg" data-setbg="image/breadcrumb.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>Shopping Cart</h2>
                            <div class="breadcrumb__option">
                                <a href="home">Home</a>
                                <span>Shopping Cart</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Shoping Cart Section Begin -->
        <section class="shoping-cart spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="shoping__cart__table">
                            <table>
                                <thead>
                                    <tr>
                                        <th class="shoping__product">Products</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Total</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                
                                   <c:forEach var ="item" items="${sessionScope.listcart}">
                                   
                                        <tr>
                                            <td class="shoping__cart__item">
                                                <img src="image/${item.product.image}" alt="">
                                                <h5 name = "proname">${item.product.productName}</h5>
                                            </td>
                                            <td class="shoping__cart__price">
                                                ${item.product.price}VND
                                            </td>
                                            <td class="shoping__cart__quantity">
                                                <div class="quantity">
                                                    <div > 
                                                        <c:if test="${item.quantity > 1}">
                                                        <a href="shop?s=updatequantityde&iditem=${item.product.id}" style="color:black; font-size:20px;">-</a>
                                                        </c:if>
                                                        <c:if test="${item.quantity == 1}">
                                                        <a href="shop?s=deleteitem&iditem=${item.product.id}" style="color:black; font-size:20px;">-</a>
                                                        </c:if>
                                                        ${item.quantity}
                                                        <a href="shop?s=updatequantityinc&iditem=${item.product.id}" style="color:black; font-size:20px;">+</a>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="shoping__cart__total">
                                                 ${item.product.price * item.quantity}VND
                                            </td>
                                            <td class="shoping__cart__price">
                                                 <a href="shop?s=deleteitem&iditem=${item.product.id}" style="color:Tomato;" class="breadcrumb__option">X</a>
                                            </td>
                                        </tr>  
                                       
                                   </c:forEach>
                                        
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="shoping__cart__btns">
                            <a href="shop" class="primary-btn cart-btn">CONTINUE SHOPPING</a>
                        </div>
                    </div>
                    <div class="col-lg-6">

                    </div>
                    <div class="col-lg-6">
                        <div class="shoping__checkout">
                            <h5>Cart Total</h5>
                            <ul>
                                <li>Total <span>${total}VND</span></li>
                            </ul>
                            <a href="shop?s=checkout" class="primary-btn">PROCEED TO CHECKOUT</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Shoping Cart Section End -->

        <%@ include file="footer.jsp" %>

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