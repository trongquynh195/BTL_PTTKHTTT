/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {
    // Cấu hình kết nối tới cơ sở dữ liệu
    private String jdbcURL = "jdbc:mysql://localhost:3306/nhahangdb?useSSL=false";
    private String jdbcUsername = "root";  // Thay thế bằng username của bạn
    private String jdbcPassword = "12345678";  // Thay thế bằng mật khẩu của bạn

    // Phương thức để lấy kết nối tới cơ sở dữ liệu
    public Connection getConnection() {
        Connection connection = null;
        try {
            // Nạp driver JDBC cho MySQL
            Class.forName("com.mysql.cj.jdbc.Driver"); // Sử dụng driver mới
            // Tạo kết nối tới cơ sở dữ liệu
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("Kết nối thành công");
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver không tìm thấy: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Kết nối tới cơ sở dữ liệu thất bại: " + e.getMessage());
        }
        return connection;  // Trả về kết nối (có thể là null nếu thất bại)
    }
}
