<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập</title>
    <!-- Sử dụng Bootstrap CDN -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-color: #007bff; /* Màu nền xanh */
        }
        .login-container {
            margin-top: 100px;
        }
        .login-box {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        }
        .login-box h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #007bff; /* Màu chữ xanh đồng bộ */
        }
        .alert {
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container login-container">
        <div class="login-box">
            <h2>Đăng Nhập</h2>
            <% if(request.getParameter("err") !=null && request.getParameter("err").equalsIgnoreCase("timeout")) { %>
                <div class="alert alert-warning">Hết phiên làm việc. Làm ơn đăng nhập lại!</div>
            <% } else if(request.getParameter("err") !=null && request.getParameter("err").equalsIgnoreCase("fail")) { %>
                <div class="alert alert-danger">Sai tên đăng nhập/mật khẩu!</div>
            <% } %>

            <!-- Form đăng nhập -->
            <form name="dangnhap" action="doDangnhap.jsp" method="post">
                <div class="form-group">
                    <label for="username">Tên đăng nhập:</label>
                    <input type="text" class="form-control" name="username" id="username" placeholder="Nhập tên đăng nhập" required>
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <input type="password" class="form-control" name="password" id="password" placeholder="Nhập mật khẩu" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Đăng nhập</button>
            </form>
        </div>
    </div>

    <!-- Sử dụng Bootstrap JS và jQuery -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
