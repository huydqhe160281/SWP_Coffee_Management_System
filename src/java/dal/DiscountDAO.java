/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Discount;
import common.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Namqd
 */
public class DiscountDAO extends DBContext {

    public List<Discount> getAllDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        String query = "SELECT * FROM Discount";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Discount discount = new Discount(
                        rs.getInt("DiscountID"),
                        rs.getInt("Value"),
                        rs.getString("Code"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getDouble("MaxDiscount"),
                        rs.getInt("Quantity")
                );
                discounts.add(discount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }

    public void addDiscount(Discount discount) {
        String query = "INSERT INTO Discount (Value, Code, StartDate, EndDate, MaxDiscount, Quantity) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, discount.getValue());
            ps.setString(2, discount.getCode());
            ps.setDate(3, new java.sql.Date(discount.getStartDate().getTime()));
            ps.setDate(4, new java.sql.Date(discount.getEndDate().getTime()));
            ps.setDouble(5, discount.getMaxDiscount());
            ps.setInt(6, discount.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Discount> searchDiscounts(String code, Integer value, Date startDate, Date endDate) {
        List<Discount> discounts = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Discount WHERE 1=1");

        // Thêm các điều kiện tìm kiếm vào câu truy vấn
        if (code != null && !code.isEmpty()) {
            query.append(" AND Code LIKE ?");
        }
        if (value != null) {
            query.append(" AND Value = ?");
        }
        if (startDate != null) {
            query.append(" AND StartDate <= ?");
        }
        if (endDate != null) {
            query.append(" AND EndDate >= ?");
        }

        try ( Connection conn = this.connection;  PreparedStatement ps = conn.prepareStatement(query.toString())) {

            int index = 1;
            if (code != null && !code.isEmpty()) {
                ps.setString(index++, "%" + code + "%");
            }
            if (value != null) {
                ps.setInt(index++, value);
            }
            if (startDate != null) {
                ps.setDate(index++, new java.sql.Date(startDate.getTime()));
            }
            if (endDate != null) {
                ps.setDate(index++, new java.sql.Date(endDate.getTime()));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                discounts.add(new Discount(
                        rs.getInt("DiscountID"),
                        rs.getInt("Value"),
                        rs.getString("Code"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getDouble("MaxDiscount"),
                        rs.getInt("Quantity")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }

    public void deleteDiscount(int discountID) {
        String query = "DELETE FROM Discount WHERE DiscountID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, discountID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
