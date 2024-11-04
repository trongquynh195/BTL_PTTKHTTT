<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,dao.*,model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    // Định dạng ngày tháng
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>

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
            flex-direction: column; 
            align-items: center; 
        }
        /* CSS cho phần tìm kiếm */
        .search-form {
            margin-bottom: 20px;
            display: flex;
            justify-content: center;
            width: 100%; 
        }
        .search-input {
            width: 300px; 
            border-radius: 25px;
            border: 1px solid #ddd; 
            padding: 10px 15px;
            margin-right: 10px; 
        }
        .search-button {
            background-color: #4b79a1; 
            border: none; 
            color: white; 
            border-radius: 25px; 
            padding: 10px 20px; 
            cursor: pointer; 
            transition: background-color 0.3s;
        }
        .search-button:hover {
            background-color: #283e51; 
        }
        .table {
            margin-top: 20px; 
        }
        .btn-secondary {
            opacity: 0.5; 
            cursor: not-allowed; 
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
                        <a class="nav-link" href="#" onclick="showContent('datban')">Đặt bàn</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Gọi món</a>
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
        <% 
            String message = (String) session.getAttribute("message");
            if (message != null) { 
        %>
            <div class="alert alert-success">
                <%= message %>
            </div>
            <% session.removeAttribute("message"); %>
        <% 
            } 
        %>
    </div>
    <div class="container container-center">
        <form method="get" action="<%=request.getContextPath()%>/banduocdat/timban" class="search-form">
            <input class="form-control search-input" type="search" placeholder="Tìm tên hoặc số điện thoại" aria-label="Search" name="searchban">
            <button class="btn search-button" type="submit">Tìm kiếm</button>
        </form>
        <div id="goimon" class="content col-md-8">
            <h3>Danh sách bàn đã đặt</h3>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Tên</th>
                        <th scope="col">Số điện thoại</th>
                        <th scope="col">Thời gian đặt</th>
                        <th scope="col">Số bàn</th>
                        <th scope="col">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ban" items="${danhSachBans}">
                        <tr>
                            <td>${ban.ten}</td>
                            <td>${ban.sdt}</td> 
                            <td>${ban.ngaydat}</td> 
                            <td>${ban.soban}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${(ban.ngaydat.time+86400000) >= currentDate.time}"> <!-- Nếu ngày còn hợp lệ -->
                                        <form action="/banduocdat/datmonan" method="get">
                                            <input type="hidden" name="idban" value="${ban.id}">
                                            <button type="submit" class="btn btn-primary">Đặt món</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-secondary" disabled>Đặt món</button> <!-- Nút mờ -->
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
