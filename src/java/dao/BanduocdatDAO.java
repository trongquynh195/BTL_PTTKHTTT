/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dao;

import context.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ban_duoc_dat645;
import model.Nguoi_dung645;

/**
 *
 * @author admin
 */
@WebServlet( {"/banduocdat","/banduocdat/timban"})
public class BanduocdatDAO extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         	request.setCharacterEncoding("UTF-8");
	        
	        // Đặt mã hóa cho response
	        response.setContentType("text/html; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
                Nguoi_dung645 nguoidung = (Nguoi_dung645) request.getSession().getAttribute("khachhang");
                int idKhachHang = nguoidung.getId();
	        List<Ban_duoc_dat645> danhSachBans = BansByKhachHang(idKhachHang);
                request.setAttribute("danhSachBans", danhSachBans);
                Date currentDate = new Date();
                request.setAttribute("currentDate", currentDate);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/GDTimban.jsp");
	        dispatcher.forward(request, response);
    }
    
    protected void Timban(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html;charset=UTF-8");
         	request.setCharacterEncoding("UTF-8");
	        
	        // Đặt mã hóa cho response
	        response.setContentType("text/html; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
                Nguoi_dung645 nguoidung = (Nguoi_dung645) request.getSession().getAttribute("khachhang");
                int idKhachHang = nguoidung.getId();
                String keyword = request.getParameter("searchban");
	        List<Ban_duoc_dat645> danhSachBans = BAcuaKH(idKhachHang,keyword);
                request.setAttribute("danhSachBans", danhSachBans);
                Date currentDate = new Date();
                request.setAttribute("currentDate", currentDate);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/GDTimban.jsp");
	        dispatcher.forward(request, response);
    }
    
    public List<Ban_duoc_dat645> BansByKhachHang(int idKhachHang){
        List<Ban_duoc_dat645> danhSachBans = new ArrayList<>();
        // Thực hiện truy vấn SQL
        String sql = "SELECT * FROM Ban_duoc_dat645 WHERE idKhachHang = ? ORDER BY ngaydat ASC";
        try (Connection connection = new DBContext().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idKhachHang);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                String sdt = rs.getString("sdt");
                Date ngaydat = rs.getDate("ngaydat");
                int soban = rs.getInt("soban");
                int idBanAn = rs.getInt("idBanAn");
//                int idKhachHang = rs.getInt("idKhachHang");
                Ban_duoc_dat645 ban = new Ban_duoc_dat645(id, ten,sdt,ngaydat,idBanAn,idKhachHang,soban);
                danhSachBans.add(ban);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachBans;
    }
    public List<Ban_duoc_dat645> BAcuaKH(int idKhachHang, String keyword) {
        List<Ban_duoc_dat645> listbanduocdat = new ArrayList<>();
        String sql = "SELECT * FROM Ban_duoc_dat645 WHERE idKhachHang = ? AND (ten LIKE ? OR sdt LIKE ?)";

        try (Connection connection = new DBContext().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Thêm từ khóa tìm kiếm vào câu truy vấn với ký tự '%'
            statement.setInt(1, idKhachHang);
            String searchKeyword = "%" + keyword + "%";
            statement.setString(2, searchKeyword);
            statement.setString(3, searchKeyword);
               
            System.out.println(sql);
            
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ten = resultSet.getString("ten");
                String sdt = resultSet.getString("sdt");
                Date ngaydat = resultSet.getDate("ngaydat");
                int idBanAn = resultSet.getInt("idBanAN");
//                int idKhachHang = resultSet.getInt("idKhachHang");
                int soban = resultSet.getInt("soban");
                
                // Tạo đối tượng mon_an từ kết quả và thêm vào danh sách
                Ban_duoc_dat645 ban = new Ban_duoc_dat645(id, ten, sdt, ngaydat, idBanAn, idKhachHang, soban);
                listbanduocdat.add(ban);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listbanduocdat;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getServletPath();
        switch (action) {
            case "/banduocdat" -> processRequest(request, response);
        }
        switch (action) {
            case "/banduocdat/timban" -> Timban(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
