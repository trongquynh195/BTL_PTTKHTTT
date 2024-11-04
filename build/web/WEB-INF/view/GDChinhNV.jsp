<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giao Diện Quản Trị</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        /* Custom styles for the layout */
        body {
            margin: 0;
            padding: 0;
            background-color: #f2f2f2; /* Nền trắng xám */
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
            background-color: #e7f3ff; /* Nền xanh nhạt khi hover hoặc active */
            color: #0056b3; /* Chữ xanh đậm */
            border: 1px solid #000; /* Viền đen khi hover hoặc active */
        }

        .content {
            margin-left: 250px;
            padding: 20px;
        }

        .content h1 {
            font-size: 24px;
            color: #333; /* Chữ màu xám đậm */
        }

        .content p {
            font-size: 18px;
            color: #555; /* Chữ màu xám */
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


    <!-- Content area where the page content will be loaded dynamically -->
    <div class="content" id="content-area">
        <h1>Chào mừng đến với Bảng Quản Trị</h1>
        <p>Chọn một tùy chọn từ menu để xem nội dung.</p>
    </div>

    <!-- Loader (can show while loading new content) -->
    <div id="loader">Đang tải...</div>

    <!-- Optional JavaScript -->

</body>
</html>
