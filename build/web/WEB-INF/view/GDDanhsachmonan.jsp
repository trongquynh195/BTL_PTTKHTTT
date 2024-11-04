<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giao Diện Quản Trị</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        /* Custom styles for the layout */
        body {
            margin: 0;
            padding: 0;
            background-color: #f8f9fa; /* Màu nền sáng hơn */
        }

        .sidebar {
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            width: 250px;
            background-color: #343a40; /* Nền tối */
            color: #ffffff; /* Chữ trắng */
            padding-top: 20px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.2); /* Đổ bóng nhẹ */
        }

        .sidebar a {
            padding: 10px 15px;
            text-align: left;
            text-decoration: none;
            font-size: 18px;
            color: #ffffff; /* Chữ trắng */
            display: block;
            border-radius: 4px; /* Bo góc */
            margin-bottom: 10px; /* Khoảng cách giữa các ô */
            transition: background-color 0.3s; /* Hiệu ứng chuyển động */
        }

        .sidebar a:hover,
        .sidebar a.active {
            background-color: #495057; /* Nền tối hơn khi hover */
        }

        .content {
            margin-left: 250px;
            padding: 20px;
        }

        .content h1 {
            font-size: 24px;
            color: #333; /* Chữ màu xám đậm */
            margin-bottom: 20px; /* Khoảng cách dưới tiêu đề */
        }

        .alert {
            margin-bottom: 20px; /* Khoảng cách dưới thông báo */
        }

        .table th, .table td {
            vertical-align: middle; /* Canh giữa các ô */
        }

        .table-hover tbody tr:hover {
            background-color: #f1f1f1; /* Nền sáng hơn khi hover */
        }
        .table th {
		    text-align: center; /* Căn giữa tiêu đề */
		}
		
		

        /* Loader style */
        #loader {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            font-size: 20px;
            color: #007bff;
        }
    </style>
</head>

<body>

    <!-- Sidebar -->
    <div class="sidebar">
        <h2 class="text-center">Menu</h2>
        <a href="dashboard.jsp" class="nav-link">Bảng điều khiển</a>
        <a href="<%=request.getContextPath()%>/admin/danhsachmon" class="nav-link">Quản lý món ăn</a>
        <a href="orders.jsp" class="nav-link">Đơn hàng</a>
        <a href="customers.jsp" class="nav-link">Khách hàng</a>
        <a href="reports.jsp" class="nav-link">Báo cáo</a>
        <a href="settings.jsp" class="nav-link">Cài đặt</a>
    </div>

    <!-- Content area -->
    <div class="container content">
        <% 
            String thongbao = (String) session.getAttribute("thongbao");
            if (thongbao != null) { 
        %>
            <div class="alert alert-success">
                <%= thongbao %>
            </div>
            <% session.removeAttribute("thongbao"); %>
        <% 
            } 
        %>
        <h1 class="text-center">Danh sách món ăn</h1>

        <form action="<%=request.getContextPath()%>/admin/danhsachmon/timkiem" method="get" class="form-inline mb-3">
            <input type="text" name="keyword" placeholder="Nhập tên món hoặc loại" class="form-control mr-2" style="width: 300px;">
            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
        </form>
            
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
                        <td><a href="${pageContext.request.contextPath}/admin/danhsachmon/edit?id=${mon.id}" class="btn btn-warning">Chỉnh sửa</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Loader -->
    <div id="loader">Đang tải...</div>
    

    <!-- Optional JavaScript -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT+0J/2yZqQn7o6N5F4B8A3TwQOXfSIFaO4B8hF6C0W7z9PbY" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-JZy1fXKf68Zx+oV9M2oYclT/vo7E6Nf/7nBQslFlR5Lttf4cqgf7g8G3hnKD53nD" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"></script>
</body>
</html>
