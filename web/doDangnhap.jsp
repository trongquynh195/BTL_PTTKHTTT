<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList,dao.*,model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Lấy thông tin đăng nhập từ form
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // Tạo đối tượng Tai_khoan645 và gán giá trị
    Tai_khoan645 tk = new Tai_khoan645();
    tk.setTendangnhap(username);
    tk.setMatkhau(password);

    // Khởi tạo DAO để xử lý kiểm tra đăng nhập
    TaikhoanDAO dao = new TaikhoanDAO();
    boolean ketQuaDangNhap = dao.kiemtraDangnhap(tk);  // Kiểm tra đăng nhập trả về true/false
    
    if(ketQuaDangNhap) {
        // Nếu đăng nhập thành công, lấy vai trò từ người dùng
        Nguoi_dung645 nguoiDung = dao.getNguoiDungById(tk.getIdnguoidung());  // Lấy thông tin người dùng từ idNguoiDung
        String vaiTro = nguoiDung.getGhichu();  // Vai trò lưu trong cột 'ghichu' (nhanvien/khachhang)

        // Kiểm tra vai trò và chuyển hướng đến giao diện tương ứng
        if(vaiTro.equalsIgnoreCase("nhanvien")) {
            session.setAttribute("nhanvien", tk);
            response.sendRedirect("admin");
        } else if(vaiTro.equalsIgnoreCase("khachhang")) {
            session.setAttribute("khachhang", nguoiDung);
            java.util.Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String attributeName = attributeNames.nextElement();
                Object attributeValue = session.getAttribute(attributeName);
                System.out.println("Session Attribute: " + attributeName + " = " + attributeValue);
            }
            response.sendRedirect("khachhang");
        } else {
            // Nếu vai trò không hợp lệ, chuyển hướng lại trang đăng nhập với thông báo lỗi
            response.sendRedirect("gddangnhap.jsp?err=invalid_role");
        }
    } else {
        // Đăng nhập thất bại, chuyển hướng lại trang đăng nhập với thông báo lỗi
        response.sendRedirect("gddangnhap.jsp?err=fail");
    }
%>
