<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,dao.*,model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giao diện chính khách hàng</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-color: #eaf2f8;
            font-family: Arial, sans-serif;
        }
        /* Thanh công cụ toàn màn hình với gradient xanh dương */
        .navbar {
            width: 100%;
            background: linear-gradient(135deg, #4b79a1, #283e51);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .navbar-brand {
            font-size: 1.5rem;
            font-weight: bold;
            color: #fff !important;
        }
        .navbar-nav .nav-link {
            font-weight: 500;
            color: #d1e8ff !important;
            transition: color 0.3s;
        }
        .navbar-nav .nav-link:hover {
            color: #a8d5ff !important;
            text-decoration: underline;
        }
        /* Nút đăng xuất màu đỏ mềm */
        .btn-logout {
            background-color: #e63946;
            border-color: #e63946;
            color: #fff;
            border-radius: 20px;
            transition: background-color 0.3s;
        }
        .btn-logout:hover {
            background-color: #d62828;
        }
        .content {
            display: none;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }
        .container-center {
            max-width: 1200px;
            margin-top: 30px;
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
    <div class="container-fluid p-0">
        <% 
            Object khachhangObj = session.getAttribute("khachhang");
            if (khachhangObj == null) {
                response.sendRedirect("gddangnhap.jsp?err=no_session");
            } else if (khachhangObj instanceof Nguoi_dung645) {
                Nguoi_dung645 khachhang = (Nguoi_dung645) khachhangObj;
        %>

        <!-- Thanh công cụ cải tiến -->
        <nav class="navbar navbar-expand-lg">
            <a class="navbar-brand" href="#">Nhà Hàng</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                    <a class="nav-link" href="#" >Đặt bàn</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/banduocdat" >Gọi món</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Đơn hàng</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Lịch sử</a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <span class="nav-link">Xin chào, <%= khachhang.getTen() %>!</span>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-logout nav-link" href="dangxuat.jsp">Đăng xuất</a>
                    </li>
                </ul>
            </div>
        </nav>

        <% 
            } else {
                response.sendRedirect("gddangnhap.jsp?err=invalid_session");
            }
        %>
    </div>
    <div class="container container-center">
        ......
    </div>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
