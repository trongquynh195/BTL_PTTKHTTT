/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dao;

import context.DBContext;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ban_duoc_dat645;
import model.Mon_an645;
import model.Mon_duoc_dat645;

@WebServlet({"/banduocdat/datmonan","/banduocdat/datmonan/list","/banduocdat/datmonan/timkiem","/banduocdat/datmonan/huy","/banduocdat/datmonan/dat"})
public class DatmonanDAO extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/banduocdat/datmonan" -> listMonan(request, response); 
            }
            switch (action) {
                case "/banduocdat/datmonan/list" -> MonancuaKH(request, response); 
            }
            switch (action) {
                case "/banduocdat/datmonan/timkiem" -> listMonan(request, response); 
            }
            switch (action) {
                case "/banduocdat/datmonan/huy" -> huy(request, response); 
            }
            switch (action) {
                case "/banduocdat/datmonan/dat" -> xacnhan(request, response); 
            }
        } catch (java.sql.SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            listMonan(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DatmonanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    private void listMonan(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, IOException, ServletException {
        
        String id = request.getParameter("idban");
        if (id != null && !id.isEmpty()) {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            List<Mon_an645> listMonAn = listAllMonAn();
            request.setAttribute("listMonAn", listMonAn);
            Cookie idCookie = new Cookie("ban", id);
            idCookie.setMaxAge(24 * 60 * 60); // Cookie tồn tại trong 1 ngày
            response.addCookie(idCookie);

            // Lấy thông tin bàn dựa trên id
            Ban_duoc_dat645 ban = selectBanduocdat(Integer.parseInt(id));
            request.setAttribute("ban", ban);
        }else {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String keyword = request.getParameter("keyword");
            List<Mon_an645> listMonAn;
            listMonAn = timKiemMonAn(keyword);
            request.setAttribute("listMonAn", listMonAn);
            // Nếu không có idban, kiểm tra cookie
            Cookie[] cookies = request.getCookies();
            Ban_duoc_dat645 ban = null; // Khởi tạo biến ban

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    // Tìm cookie có tên là "ban"
                    if ("ban".equals(cookie.getName())) {
                        String cookieValue = cookie.getValue();
                        // Lấy thông tin bàn dựa trên giá trị trong cookie
                        ban = selectBanduocdat(Integer.parseInt(cookieValue));
                        break; // Không cần tiếp tục tìm kiếm nếu đã tìm thấy
                    }
                }
            }

            request.setAttribute("ban", ban); // Gán giá trị của ban (có thể là null nếu không tìm thấy)
        }
        
        request.getRequestDispatcher("/WEB-INF/view/GDDanhsachmon.jsp").forward(request, response);
    }
    private void huy(HttpServletRequest request, HttpServletResponse response)
            throws java.sql.SQLException, IOException, ServletException {
        
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("orderIds")) { // Kiểm tra tên cookie
                    cookie.setMaxAge(0); // Đặt thời gian tồn tại về 0 để xóa cookie
                    response.addCookie(cookie); // Cập nhật lại cookie trong response
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/banduocdat");
    }
    
    private void xacnhan(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException, SQLException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<Mon_an645> listMonAndat = new ArrayList<>();
        List<Mon_duoc_dat645> listMonduocdat = new ArrayList<>();

        // Chuỗi chứa danh sách ID món ăn từ cookie (nếu đã có) hoặc tạo chuỗi mới
        String idData = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("orderIds".equals(cookie.getName())) {
                    idData = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    break;
                }
            }
        }
            List<Mon_an645> listMonAn = listAllMonAn();
            
            request.setAttribute("listMonAn", listMonAn);

            Ban_duoc_dat645 ban = null; // Khởi tạo biến ban
                for (Cookie cookie : cookies) {
                    if ("ban".equals(cookie.getName())) {
                        String cookieValue = cookie.getValue();
                        ban = selectBanduocdat(Integer.parseInt(cookieValue));
                        break; // Không cần tiếp tục tìm kiếm nếu đã tìm thấy
                    }
                }

            String txt[] = idData.split(",");
            for(String i:txt){
                Mon_an645 mon = (Mon_an645) selectMonAn(Integer.parseInt(i));
                listMonAndat.add(mon);
    //            Mon_duoc_dat645 mondat = new Mon_duoc_dat645(ban.getId(),mon.getId(),1,mon.getGia(),mon.getTen());
    //            listMonduocdat.add(mondat);
                boolean found = false;
                for (Mon_duoc_dat645 existingMonDat : listMonduocdat) {
                    if (existingMonDat.getIdMonan() == mon.getId()) {
                        // Nếu đã tồn tại, tăng số lượng thêm 1
                        existingMonDat.setSoLuong(existingMonDat.getSoLuong() + 1);
                        existingMonDat.setTonggia(existingMonDat.getSoLuong()*mon.getGia());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    
                    Mon_duoc_dat645 mondat = new Mon_duoc_dat645(ban.getId(), mon.getId(), 1, mon.getGia(), mon.getTen());
                    listMonduocdat.add(mondat);
                }
            }
            for (Mon_duoc_dat645 mondat : listMonduocdat) {
                insertMonDuocDat(mondat);
            }
            Cookie idCookie = new Cookie("orderIds", URLEncoder.encode(idData, "UTF-8"));
            idCookie.setMaxAge(0); // Cookie tồn tại trong 7 ngày
            request.getSession().setAttribute("message", "Đặt món thành công!");
        response.sendRedirect(request.getContextPath() + "/banduocdat");
    }
    protected void MonancuaKH(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<Mon_an645> listMonAndat = new ArrayList<>();
        List<Mon_duoc_dat645> listMonduocdat = new ArrayList<>();
        int idMonan = Integer.parseInt(request.getParameter("id"));

        // Chuỗi chứa danh sách ID món ăn từ cookie (nếu đã có) hoặc tạo chuỗi mới
        String idData = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("orderIds".equals(cookie.getName())) {
                    idData = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    break;
                }
            }
        }

        if (!idData.isEmpty()) {
            idData += ",";
        }
        idData += idMonan;

            List<Mon_an645> listMonAn = listAllMonAn();
            
            request.setAttribute("listMonAn", listMonAn);

            Ban_duoc_dat645 ban = null; // Khởi tạo biến ban
                for (Cookie cookie : cookies) {
                    if ("ban".equals(cookie.getName())) {
                        String cookieValue = cookie.getValue();
                        ban = selectBanduocdat(Integer.parseInt(cookieValue));
                        break; // Không cần tiếp tục tìm kiếm nếu đã tìm thấy
                    }
                }


            request.setAttribute("ban", ban);
            String txt[] = idData.split(",");
            for(String i:txt){
                Mon_an645 mon = (Mon_an645) selectMonAn(Integer.parseInt(i));
                listMonAndat.add(mon);
    //            Mon_duoc_dat645 mondat = new Mon_duoc_dat645(ban.getId(),mon.getId(),1,mon.getGia(),mon.getTen());
    //            listMonduocdat.add(mondat);
                boolean found = false;
                for (Mon_duoc_dat645 existingMonDat : listMonduocdat) {
                    if (existingMonDat.getIdMonan() == mon.getId()) {
                        // Nếu đã tồn tại, tăng số lượng thêm 1
                        existingMonDat.setSoLuong(existingMonDat.getSoLuong() + 1);
                        existingMonDat.setTonggia(existingMonDat.getSoLuong()*mon.getGia());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    
                    Mon_duoc_dat645 mondat = new Mon_duoc_dat645(ban.getId(), mon.getId(), 1, mon.getGia(), mon.getTen());
                    listMonduocdat.add(mondat);
                }
            }
            Cookie idCookie = new Cookie("orderIds", URLEncoder.encode(idData, "UTF-8"));
            idCookie.setMaxAge(24 * 60 * 60); // Cookie tồn tại trong 7 ngày
            response.addCookie(idCookie);

            request.setAttribute("listMonAndat", listMonduocdat);

            // Xác nhận lưu ID món thành công
            request.getRequestDispatcher("/WEB-INF/view/GDDanhsachmon.jsp").forward(request, response);
    }
    public List<Mon_an645> listAllMonAn() {
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
        } catch (java.sql.SQLException e) {
            // Xử lý lỗi SQL
            System.err.println("Lỗi khi liệt kê các món ăn.");
            e.printStackTrace();
        }
        
        return listMonAn;
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
    public Ban_duoc_dat645 selectBanduocdat(int id) throws SQLException {
        Ban_duoc_dat645 ban = null; // Khởi tạo biến để lưu món ăn
        String sql = "SELECT * FROM Ban_duoc_dat645 WHERE id = ?"; // Câu lệnh SQL để lấy món ăn theo id
        
        try (Connection connection = new DBContext().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id); // Đặt giá trị id vào câu lệnh SQL
            ResultSet resultSet = statement.executeQuery(); // Thực hiện truy vấn
            
            // Kiểm tra xem có kết quả không
            if (resultSet.next()) {
                // Nếu có, tạo đối tượng mon_an từ kết quả
                int monAnId = resultSet.getInt("id");
                String ten = resultSet.getString("ten");
                String sdt = resultSet.getString("sdt");
                Date ngaydat = resultSet.getDate("ngaydat");
                int soban = resultSet.getInt("soban");
                int idBanAn = resultSet.getInt("idBanAn");
                int idKhachHang = resultSet.getInt("idKhachHang");
                ban = new Ban_duoc_dat645(id, ten,sdt,ngaydat,idBanAn,idKhachHang,soban);
            }
        } catch (SQLException e) {
            // Xử lý lỗi SQL
            System.err.println("Lỗi khi lấy món ăn theo ID: " + id);
            e.printStackTrace();
        }
        
        return ban; // Trả về món ăn tìm được, hoặc null nếu không có
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
    private void insertMonDuocDat(Mon_duoc_dat645 mondat) throws SQLException {
        String sql = "INSERT INTO Mon_duoc_dat645 (idDatHang, idMonAn, soLuong, gia, ten) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = new DBContext().getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mondat.getIdDathang());
            statement.setInt(2, mondat.getIdMonan());
            statement.setInt(3, mondat.getSoLuong());
            statement.setFloat(4, mondat.getTonggia());
            statement.setString(5, mondat.getTen());
            statement.executeUpdate();
        }
    }
}
