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
            margin: 30px auto auto;
            display: flex;
            justify-content: center;
/*            flex-direction: column; 
            align-items: center; */
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
        .table thead th {
            text-align: center; /* Căn giữa tiêu đề */
            vertical-align: middle;
	}
        
        .reservation-info {
            background-color: #f8f9fa;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 15px;
            font-size: 1rem;
        }
        .reservation-info p {
            margin: 0;
            font-weight: 500;
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
                        <a class="nav-link" href="<%=request.getContextPath()%>/banduocdat">Gọi món</a>
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
    <div class="container container-center row">
        
        <div id="goimon" class="content col-md-8">
            <h3 style="text-align: center;">Đặt món</h3>
            <form action="<%=request.getContextPath()%>/banduocdat/datmonan/timkiem" method="get" class="form-inline mb-3">
            <input type="text" name="keyword" placeholder="Nhập tên món hoặc loại" class="form-control mr-2" style="width: 300px;">
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            </form>
            <div class="reservation-info row">
                <div class="info-row col-md-6">
                    <p>Tên khách hàng: ${ban.ten}</p>
                    <p>Số điện thoại: ${ban.sdt}</p>
                </div>
                <div class="info-row col-md-6">
                    <p>Ngày đặt: ${ban.ngaydat}</p>
                    <p>Số bàn: ${ban.soban}</p>
                </div>
            </div>
        <% String message = (String) request.getAttribute("message"); %>
        <% if (message != null) { %>
            <div class="alert alert-info"><%= message %></div> <!-- Hiển thị thông báo -->
        <% } %>

        <table class="table table-striped table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Tên món ăn</th>
                    <th>Loại món ăn</th>
                    <th>Mô tả</th>
                    <th>Giá bán</th>
                    <th>Tình trạng món</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <!-- Duyệt qua danh sách món ăn -->
                <c:forEach var="mon" items="${listMonAn}">
                    <tr>
                        <td>${mon.id}</td>
                        <td>${mon.ten}</td>
                        <td>${mon.loai}</td>
                        <td>${mon.mota}</td>
                        <td>${mon.gia}</td>
                        <td>${mon.trangthai == 1 ? "Có sẵn" : "Hết hàng"}</td>
                        <td><a href="${pageContext.request.contextPath}/banduocdat/datmonan/list?id=${mon.id}" class="btn btn-success">Chọn</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
        <div id="selectedItems" class="content col-md-4">
            <h3>Món được chọn</h3>
            <table class="table table-striped table-bordered table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th>Tên món ăn</th>
                        <th>Số lượng</th>
                        <th>Giá bán</th>
                        <th>Số tiền</th>
                    </tr>
                </thead>
                <tbody id="orderList">
                    <c:set var="totalAmount" value="0" /> 
                    <c:forEach var="mon" items="${listMonAndat}">
                        <tr>
                            <td>${mon.ten}</td>
                            <td>${mon.soLuong}</td>
                            <td>${mon.tonggia/mon.soLuong}</td>
                            <td>${mon.tonggia}</td>
                            <!-- Cộng dồn giá trị vào totalAmount -->
                            <c:set var="totalAmount" value="${totalAmount + mon.tonggia}" />
                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <th colspan="3">Tổng cộng</th>
                        <th id="totalAmount">${totalAmount}</th> 
                    </tr>
                </tfoot>

            </table>
            <div class="d-flex justify-content-between">
                <form action="<%=request.getContextPath()%>/banduocdat/datmonan/huy" method="get">
                    <button type="submit" name="action" value="cancel" class="btn btn-danger">Hủy</button>
                </form>
                <form action="<%=request.getContextPath()%>/banduocdat/datmonan/dat" method="get">
                    <button type="submit" name="action" value="order" class="btn btn-primary">Đặt món</button>
                </form>
            </div>
        </div>
        
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
