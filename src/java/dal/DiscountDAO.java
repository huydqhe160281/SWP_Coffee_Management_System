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
import java.util.List;

/**
 *
 * @author Namqd
 */
public class DiscountDAO extends DBContext{
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
        String query = "INSERT INTO Discounts (Value, Code, StartDate, EndDate, MaxDiscount, Quantity) VALUES (?, ?, ?, ?, ?, ?)";
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

    public void deleteDiscount(int discountID) {
        String query = "DELETE FROM Discounts WHERE DiscountID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, discountID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
