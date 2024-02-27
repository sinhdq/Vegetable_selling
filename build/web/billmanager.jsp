<%-- 
    Document   : billmanager
    Created on : Oct 27, 2022, 10:49:56 PM
    Author     : SINHDQ
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <section class="breadcrumb-section set-bg" data-setbg="image/breadcrumb.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>Bill MANAGER</h2>
                             
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
                        <th>Customer Name</th>
                        <th>Create Date</th>
                        <th>Pay Date</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var ="item" items="${sessionScope.listbill}">
                    <tr>
                        <td>${item.cusname}</td>
                        <td>${item.bill.createDate}</td>
                         <td>${item.bill.requiredDate}</td>
                         <td>${item.bill.total}</td>
                         <td>
                             <c:if test="${item.bill.status == 1}">
                                 <p style="color:DodgerBlue;">Done</p>
                             </c:if>
                                <c:if test="${item.bill.status == 0}">
                                 <p style="color:Tomato;">Doing</p>
                             </c:if>
                         </td>
                         <td>
                             <a href="admin?s=billDetail&idb=${item.bill.id}&idcus=${item.bill.customerID}" style="color:MediumSeaGreen;">Detail</a>
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
