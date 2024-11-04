package dao;

import context.DBContext;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Mon_an645;

@WebServlet(name = "monanservlet", urlPatterns = {"/admin", "/admin/danhsachmon", "/admin/danhsachmon/edit", "/admin/danhsachmon/update","/admin/danhsachmon/timkiem"})
public class MonanDAO extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/admin":
                    showNewForm(request, response);
                    break;
                case "/admin/danhsachmon":
                    listMonan(request, response);
                    break;
                case "/admin/danhsachmon/edit":
                    selectMonAn(request, response);
                    break;
                case "/admin/danhsachmon/timkiem":
                    searchMonAn(request, response);
                    break;
                default:
                    showNewForm(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/admin/danhsachmon/update":
                    updateMonAn(request, response);
                    break;
                
                default:
                    listMonan(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listMonan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        List<Mon_an645> listMonAn = listAllMonAn();
        request.setAttribute("listMonAn", listMonAn);
        
        request.getRequestDispatcher("/WEB-INF/view/GDDanhsachmonan.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/GDChinhNV.jsp");
        dispatcher.forward(request, response);
    }

    protected void selectMonAn(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            try {
                Mon_an645 monAn = selectMonAn(Integer.parseInt(id));
                request.setAttribute("monAn", monAn);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/GDChitietmonan.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Lỗi khi lấy món ăn theo ID: " + id, e);
            }
        } else {
            response.sendRedirect("listMonAn");
        }
    }
    protected void searchMonAn(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String keyword = request.getParameter("keyword");
        List<Mon_an645> listMonAn;
        System.out.println("Từ khóa tìm kiếm: " + keyword);

        // Kiểm tra keyword có null hay không
        if (keyword != null && !keyword.isEmpty()) {

            listMonAn = timKiemMonAn(keyword);

            if (listMonAn == null || listMonAn.isEmpty()) {
                System.out.println("Không tìm thấy món ăn nào.");
                request.setAttribute("message", "Không tìm thấy món ăn nào với từ khóa: " + keyword); // Thêm thông báo
            } else {
                System.out.println("Danh sách món ăn:");
                for (Mon_an645 monAn : listMonAn) {
                    System.out.println(monAn);
                }
                request.setAttribute("listMonAn", listMonAn);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/GDDanhsachmonan.jsp");
            dispatcher.forward(request, response);
        } else {
            // Nếu không có từ khóa, điều hướng về danh sách món ăn
            response.sendRedirect("/admin/danhsachmon");
        }
    }


    private void updateMonAn(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        

        int id = Integer.parseInt(request.getParameter("id"));
        String ten = new String(request.getParameter("ten").getBytes("ISO-8859-1"), "UTF-8");
        String loai = new String(request.getParameter("loai").getBytes("ISO-8859-1"), "UTF-8");
        String mota = new String(request.getParameter("mota").getBytes("ISO-8859-1"), "UTF-8");
        float gia = Float.parseFloat(request.getParameter("gia"));
        int trangthai = Integer.parseInt(request.getParameter("trangthai"));
        int idnhahang = Integer.parseInt(request.getParameter("idnhahang"));

        Mon_an645 monAn = new Mon_an645(id, ten, loai, mota, gia, trangthai, idnhahang);
        updateMonAn(monAn);
        request.getSession().setAttribute("thongbao", "Sửa món thành công!");
        response.sendRedirect(request.getContextPath() + "/admin/danhsachmon");
    }
    public List<Mon_an645> listAllMonAn() throws SQLException {
        List<Mon_an645> listMonAn = new ArrayList<>();
        
        // Câu truy vấn SQL để lấy tất cả các món ăn từ bảng "Mon_an645"
        String sql = "SELECT * FROM Mon_an645";
        
        // Sử dụng try-with-resources để đảm bảo đóng kết nối và các tài nguyên khác
        try (Connection connection = new DBContext().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            // Duyệt qua từng dòng dữ liệu trong ResultSet
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ten = resultSet.getString("ten");
                String loai = resultSet.getString("loai");
                String mota = resultSet.getString("mota");
                float gia = resultSet.getFloat("gia");
                int trangthai = resultSet.getInt("trangthai");
                int idNhaHang = resultSet.getInt("idNhaHang");
                
                // Tạo đối tượng mon_an từ kết quả và thêm vào danh sách
                Mon_an645 mon = new Mon_an645(id, ten, loai, mota, gia, trangthai, idNhaHang);
                listMonAn.add(mon);
            }
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            System.err.println("Lỗi khi liệt kê các món ăn.");
            e.printStackTrace();
        }
        
        return listMonAn;
    }
    public void updateMonAn(Mon_an645 monAn) throws SQLException {
        String sql = "UPDATE Mon_an645 SET ten = ?, loai = ?, mota = ?, gia = ?, trangthai = ? WHERE id = ?";
        try (Connection connection = new DBContext().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, monAn.getTen());
            statement.setString(2, monAn.getLoai());
            statement.setString(3, monAn.getMota());
            statement.setFloat(4, monAn.getGia());
            statement.setInt(5, monAn.getTrangthai());
            statement.setInt(6, monAn.getId());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            System.err.println("Lỗi khi cập nhật món ăn.");
            e.printStackTrace();
        }
    }
    public Mon_an645 selectMonAn(int id) throws SQLException {
        Mon_an645 monAn = null; // Khởi tạo biến để lưu món ăn
        String sql = "SELECT * FROM Mon_an645 WHERE id = ?"; // Câu lệnh SQL để lấy món ăn theo id
        
        try (Connection connection = new DBContext().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id); // Đặt giá trị id vào câu lệnh SQL
            ResultSet resultSet = statement.executeQuery(); // Thực hiện truy vấn
            
            // Kiểm tra xem có kết quả không
            if (resultSet.next()) {
                // Nếu có, tạo đối tượng mon_an từ kết quả
                int monAnId = resultSet.getInt("id");
                String ten = resultSet.getString("ten");
                String loai = resultSet.getString("loai");
                String mota = resultSet.getString("mota");
                float gia = resultSet.getFloat("gia");
                int trangthai = resultSet.getInt("trangthai");
                int idNhaHang = resultSet.getInt("idNhaHang");
                
                // Khởi tạo đối tượng mon_an
                monAn = new Mon_an645(monAnId, ten, loai, mota, gia, trangthai, idNhaHang);
            }
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            System.err.println("Lỗi khi lấy món ăn theo ID: " + id);
            e.printStackTrace();
        }
        
        return monAn; // Trả về món ăn tìm được, hoặc null nếu không có
    }
    public List<Mon_an645> timKiemMonAn(String keyword) {
        List<Mon_an645> listMonAn = new ArrayList<>();
        String sql = "SELECT * FROM Mon_an645 WHERE ten LIKE ? OR loai LIKE ?";

        try (Connection connection = new DBContext().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Thêm từ khóa tìm kiếm vào câu truy vấn với ký tự '%'
            String searchKeyword = "%" + keyword + "%";
            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);
               
            System.out.println(sql);
            
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String ten = resultSet.getString("ten");
                String loai = resultSet.getString("loai");
                String mota = resultSet.getString("mota");
                float gia = resultSet.getFloat("gia");
                int trangthai = resultSet.getInt("trangthai");
                int idNhaHang = resultSet.getInt("idNhaHang");
                
                // Tạo đối tượng mon_an từ kết quả và thêm vào danh sách
                Mon_an645 mon = new Mon_an645(id, ten, loai, mota, gia, trangthai, idNhaHang);
                listMonAn.add(mon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listMonAn;
    }
}
