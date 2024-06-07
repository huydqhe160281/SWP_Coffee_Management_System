/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import common.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author Namqd
 */
public class OrderDAO extends DBContext{
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.OrderID, o.AccountID, a.Name AS AccountName, o.OrderDate " +
                     "FROM [Order] o " +
                     "JOIN Account a ON o.AccountID = a.AccountID";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("AccountID"),
                        rs.getString("AccountName"),
                        rs.getDate("OrderDate")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrderById(int orderId) {
        String sql = "SELECT o.OrderID, o.AccountID, a.Name AS AccountName, o.OrderDate " +
                     "FROM [Order] o " +
                     "JOIN Account a ON o.AccountID = a.AccountID " +
                     "WHERE o.OrderID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("AccountID"),
                        rs.getString("AccountName"),
                        rs.getDate("OrderDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT od.OrderID, od.ProductID, p.ProductName, od.UnitPrice, od.Quantity, od.Note, d.Value AS DiscountValue "
                +    ", d.DiscountID" +
                     "FROM OrderDetail od " +
                     "JOIN Product p ON od.ProductID = p.ProductID " +
                     "LEFT JOIN Discount d ON od.DiscountID = d.DiscountID " +
                     "WHERE od.OrderID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("UnitPrice"),
                        rs.getInt("Quantity"),
                        rs.getString("Note"),
                        rs.getInt("DiscountID"),
                        rs.getInt("Value")
                );
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    public void addOrder(Order order) {
        String sql = "INSERT INTO [Order] (AccountID, OrderDate) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getAccountID());
            ps.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                order.setOrderID(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO OrderDetail (OrderID, ProductID, UnitPrice, Quantity, Note, DiscountID) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderDetail.getOrderID());
            ps.setInt(2, orderDetail.getProductID());
            ps.setDouble(3, orderDetail.getUnitPrice());
            ps.setInt(4, orderDetail.getQuantity());
            ps.setString(5, orderDetail.getNote());
            ps.setInt(6, orderDetail.getDiscountID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int orderId) {
        String sql = "DELETE FROM [Order] WHERE OrderID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrderDetail(int orderId, int productId) {
        String sql = "DELETE FROM OrderDetail WHERE OrderID = ? AND ProductID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
