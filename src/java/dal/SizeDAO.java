package dal;

import common.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Size;

/**
 *
 * Size Data Access Object (DAO)
 */
public class SizeDAO extends DBContext {

    // Phương thức lấy danh sách tất cả các Size từ DB
    public List<Size> getAllSizes() {
        List<Size> sizes = new ArrayList<>();
        String sql = "SELECT * FROM Size";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Size size = new Size(
                        rs.getInt("SizeID"),
                        rs.getString("Type"),
                        rs.getString("Description")
                );
                sizes.add(size);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizes;
    }

    // Phương thức lấy Size bởi ID từ DB
    public Size getSizeByID(int id) {
        String sql = "SELECT * FROM Size WHERE SizeID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Size(
                        rs.getInt("SizeID"),
                        rs.getString("Type"),
                        rs.getString("Description")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức thêm mới Size vào DB
    public void addSize(Size size) {
        String sql = "INSERT INTO Size (Type, Description) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, size.getType());
            ps.setString(2, size.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức chỉnh sửa thông tin Size trong DB
    public void editSize(Size size) {
        if (!checkSizeIDExists(size.getSizeID())) {
            System.out.println("SizeID does not exist. Cannot edit.");
            return;
        }

        String sql = "UPDATE Size SET Type = ?, Description = ? WHERE SizeID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, size.getType());
            ps.setString(2, size.getDescription());
            ps.setInt(3, size.getSizeID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức xóa Size từ DB
    public void deleteSize(int sizeID) {
        String sql = "DELETE FROM Size WHERE SizeID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, sizeID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức kiểm tra xem Size có tồn tại không
    public boolean checkSizeIDExists(int sizeID) {
        boolean exists = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM Size WHERE SizeID = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, sizeID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý exception nếu cần
        } finally {
            // Đóng ResultSet, PreparedStatement nếu cần
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }
}