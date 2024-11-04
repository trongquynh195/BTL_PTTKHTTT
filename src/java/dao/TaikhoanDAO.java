package dao;

import context.DBContext;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import model.Nguoi_dung645;
import model.Tai_khoan645;

@WebServlet({"/login"})
public class TaikhoanDAO extends HttpServlet {
    public boolean kiemtraDangnhap(Tai_khoan645 tk) {
        boolean ketQua = false;
        String sql = "SELECT * FROM Tai_khoan645 WHERE tendangnhap = ? AND matkhau = ?";
        
        try (Connection connection = new DBContext().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Gán giá trị cho câu lệnh SQL
            statement.setString(1, tk.getTendangnhap());
            statement.setString(2, tk.getMatkhau());

            // Thực thi câu lệnh SQL
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                // Nếu tồn tại tài khoản, gán thông tin vào đối tượng tk
                tk.setId(rs.getInt("id"));
                tk.setIdnguoidung(rs.getInt("idNguoiDung"));
                ketQua = true;  // Đăng nhập thành công
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ketQua;
    }

    // Lấy thông tin người dùng theo idNguoiDung
    public Nguoi_dung645 getNguoiDungById(int idNguoiDung) {
        Nguoi_dung645 nguoiDung = null;
        String sql = "SELECT * FROM Nguoi_dung645 WHERE id = ?";

        try (Connection connection = new DBContext().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idNguoiDung);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                nguoiDung = new Nguoi_dung645();
                nguoiDung.setId(rs.getInt("id"));
                nguoiDung.setTen(rs.getString("ten"));
                nguoiDung.setSdt(rs.getString("sdt"));
                nguoiDung.setDiachi(rs.getString("diachi"));
                nguoiDung.setEmail(rs.getString("email"));
                nguoiDung.setGhichu(rs.getString("ghichu"));  // Vai trò người dùng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nguoiDung;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển hướng đến GDDangnhap.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("GDdangnhap.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
