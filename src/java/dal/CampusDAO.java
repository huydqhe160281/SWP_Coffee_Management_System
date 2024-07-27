package dal;

import common.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Campus;

/**
 *
 * Campus Data Access Object (DAO)
 */
public class CampusDAO extends DBContext {

    // Phương thức lấy danh sách tất cả các Campus từ DB
    public List<Campus> getAllCampuses() {
        List<Campus> campuses = new ArrayList<>();
        String sql = "SELECT * FROM Campus";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Campus campus = new Campus(
                        rs.getInt("CampusID"),
                        rs.getString("CampusName"),
                        rs.getString("Address")
                );
                campuses.add(campus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campuses;
    }

    // Phương thức lấy Campus bởi ID từ DB
    public Campus getCampusByID(int id) {
        String sql = "SELECT * FROM Campus WHERE CampusID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Campus(
                        rs.getInt("CampusID"),
                        rs.getString("CampusName"),
                        rs.getString("Address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Phương thức thêm mới Campus vào DB
    public void addCampus(Campus campus) {
        String sql = "INSERT INTO Campus (CampusName, Address) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, campus.getCampusName());
            ps.setString(2, campus.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức chỉnh sửa thông tin Campus trong DB
    public void editCampus(Campus campus) {
        if (!checkCampusIDExists(campus.getCampusID())) {
            System.out.println("CampusID does not exist. Cannot edit.");
            return;
        }

        String sql = "UPDATE Campus SET CampusName = ?, Address = ? WHERE CampusID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, campus.getCampusName());
            ps.setString(2, campus.getAddress());
            ps.setInt(3, campus.getCampusID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức xóa Campus từ DB
    public void deleteCampus(int campusID) {
        String sql = "DELETE FROM Campus WHERE CampusID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, campusID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức kiểm tra xem Campus có tồn tại không
    public boolean checkCampusIDExists(int campusID) {
        boolean exists = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM Campus WHERE CampusID = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, campusID);
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
