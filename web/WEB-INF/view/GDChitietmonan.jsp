<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chỉnh Sửa Món Ăn</title>
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
    <div class="sidebar">
        <h2 class="text-center">Menu</h2>
        <a href="dashboard.jsp" class="nav-link">Bảng điều khiển</a>
        <a href="<%=request.getContextPath()%>/admin/danhsachmon" class="nav-link">Quản lý món ăn</a>
        <a href="orders.jsp" class="nav-link">Đơn hàng</a>
        <a href="customers.jsp" class="nav-link">Khách hàng</a>
        <a href="reports.jsp" class="nav-link">Báo cáo</a>
        <a href="settings.jsp" class="nav-link">Cài đặt</a>
    </div>
    <div class="container content">
        <h2 class="text-center">Chỉnh Sửa Món Ăn</h2>
        <form id="editForm" action="update" method="post" accept-charset="UTF-8">
            <input type="hidden" name="id" value="${monAn.id}">
            <input type="hidden" name="idnhahang" value="${monAn.idNhaHang}"> <!-- Lưu ID món ăn -->
            <div class="form-group">
                <label for="ten">Tên món ăn:</label>
                <input type="text" class="form-control" name="ten" value="${monAn.ten}" required>
            </div>
            <div class="form-group">
                <label for="loai">Loại món ăn:</label>
                <input type="text" class="form-control" name="loai" value="${monAn.loai}" required>
            </div>
            <div class="form-group">
                <label for="mota">Mô tả:</label>
                <textarea class="form-control" name="mota" required>${monAn.mota}</textarea>
            </div>
            <div class="form-group">
                <label for="gia">Giá bán:</label>
                <input type="number" class="form-control" name="gia" value="${monAn.gia}" required>
            </div>
            <div class="form-group">
			    <label for="trangthai">Tình trạng món:</label>
			    <select class="form-control" name="trangthai" required>
			        <option value="1" <c:if test="${monAn.trangthai == 1}">selected</c:if>>Có sẵn</option>
			        <option value="0" <c:if test="${monAn.trangthai == 0}">selected</c:if>>Hết hàng</option>
			    </select>
			</div>
            <button type="submit" class="btn btn-primary">Cập nhật</button>
        </form>
    </div>
    <script>
    document.getElementById("editForm").onsubmit = function(event) {
        event.preventDefault(); // Ngăn chặn gửi form mặc định

        // Tạo đối tượng JSON từ các giá trị trong form
        const formData = {
            id: document.querySelector('input[name="id"]').value,
            ten: document.querySelector('input[name="ten"]').value,
            loai: document.querySelector('input[name="loai"]').value,
            mota: document.querySelector('textarea[name="mota"]').value,
            gia: document.querySelector('input[name="gia"]').value,
            trangthai: document.querySelector('select[name="trangthai"]').value,
            idnhahang: document.querySelector('input[name="idnhahang"]').value
        };

        // Log đối tượng JSON ra console
        console.log(JSON.stringify(formData));

        // Gửi form
        this.submit(); // Gửi form sau khi log
    };
</script>
</body>
</html>
