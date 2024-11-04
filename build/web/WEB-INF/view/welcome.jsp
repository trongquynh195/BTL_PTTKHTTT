<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách món ăn</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>

<h2>Danh sách món ăn</h2>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Tên món ăn</th>
            <th>Loại món ăn</th>
            <th>Mô tả</th>
            <th>Giá bán</th>
            <th>Tình trạng món</th>
        </tr>
    </thead>
    <tbody>
        <!-- Duyệt qua danh sách món ăn được truyền từ Servlet -->
        <c:forEach var="mon" items="${listMonAn}">
            <tr>
                <td>${mon.id}</td>
                <td>${mon.ten}</td>
                <td>${mon.loai}</td>
                <td>${mon.mota}</td>
                <td>${mon.gia}</td>
                <td>${mon.trangthai == 1 ? "Có sẵn" : "Hết hàng"}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
