package dal;

import common.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Order;
import model.OrderDetail;
import model.ProductSize;
import model.Size;

/**
 *
 * @author Nam
 */
public class OrderDAO extends DBContext {

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT o.OrderID, o.AccountID, a.Name AS AccountName, o.OrderDate, o.status, o.cancelled "
                     + "FROM [Order] o "
                     + "JOIN Account a ON o.AccountID = a.AccountID";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Order order = new Order();
            order.setOrderID(rs.getInt("OrderID"));
            order.setAccountID(rs.getInt("AccountID"));
            order.setAccountName(rs.getString("AccountName"));
            order.setOrderDate(rs.getDate("OrderDate"));
            order.setStatus(rs.getBoolean("status"));
            order.setCancelled(rs.getBoolean("cancelled"));
            orders.add(order);
        }

        return orders;
    }

    public List<Order> getOrdersByPage(int indexPage, int pageSize) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT o.OrderID, o.AccountID, a.Name AS AccountName, o.OrderDate, o.status, o.cancelled "
                     + "FROM [Order] o "
                     + "JOIN Account a ON o.AccountID = a.AccountID "
                     + "ORDER BY o.OrderID "
                     + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, (indexPage - 1) * pageSize);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("OrderID"));
                order.setAccountID(rs.getInt("AccountID"));
                order.setAccountName(rs.getString("AccountName"));
                order.setOrderDate(rs.getDate("OrderDate"));
                order.setStatus(rs.getBoolean("status"));
                order.setCancelled(rs.getBoolean("cancelled"));
                orders.add(order);
            }
        }
        return orders;
    }

    
    public int getTotalOrders() throws SQLException {
        String query = "SELECT COUNT(*) FROM [Order]";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public List<OrderDetail> getOrderDetails(int orderId) {
        List<OrderDetail> details = new ArrayList<>();
        String query = "SELECT od.OrderID, od.ProductID, p.ProductName, od.UnitPrice, od.Quantity, od.Note, od.DiscountID, d.Value "
                     + "FROM OrderDetail od "
                     + "JOIN Product p ON od.ProductID = p.ProductID "
                     + "LEFT JOIN Discount d ON od.DiscountID = d.DiscountID "
                     + "WHERE od.OrderID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setOrderID(rs.getInt("OrderID"));
                detail.setProductID(rs.getInt("ProductID"));
                detail.setProductName(rs.getString("ProductName"));
                detail.setUnitPrice(rs.getDouble("UnitPrice"));
                detail.setQuantity(rs.getInt("Quantity"));
                detail.setNote(rs.getString("Note"));
                detail.setDiscountID(rs.getInt("DiscountID"));
                detail.setValue(rs.getInt("Value"));
                details.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }

    public String getAccountNameByOrderId(int orderId) throws SQLException {
        String query = "SELECT a.Name FROM [Order] o JOIN Account a ON o.AccountID = a.AccountID WHERE o.OrderID = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, orderId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getString("Name");
        }

        return null;
    }

    public void addOrder(Order order) {
        String sql = "INSERT INTO [Order] (AccountID, OrderDate) VALUES (?, ?)";
        try ( PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
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
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrderDetail(int orderId, int productId) {
        String sql = "DELETE FROM OrderDetail WHERE OrderID = ? AND ProductID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Category";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Detail")
                );
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<ProductSize> getProductsByCategory(int categoryID) {
        List<ProductSize> products = new ArrayList<>();
        String sql = "SELECT p.ProductID, p.ProductName, p.Image, p.Description, p.Recipe, p.Status, p.IsHot, "
                + "c.CategoryID, c.CategoryName, c.Detail, "
                + "ps.SizeID, ps.Price, "
                + "s.SizeID AS sizeId, s.Type AS sizeType, s.Description AS sizeDescription "
                + "FROM Product p "
                + "JOIN ProductSize ps ON p.ProductID = ps.ProductID "
                + "JOIN Category c ON p.CategoryID = c.CategoryID "
                + "JOIN Size s ON ps.SizeID = s.SizeID "
                + "WHERE p.CategoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Detail")
                );
                Size size = new Size(
                        rs.getInt("sizeId"),
                        rs.getString("sizeType"),
                        rs.getString("sizeDescription")
                );
                ProductSize product = new ProductSize(
                        rs.getInt("ProductID"),
                        rs.getInt("SizeID"),
                        rs.getDouble("Price"),
                        size
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<String> getSizesByProduct(int productID) {
        List<String> sizes = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM ProductSize ps "
                + "JOIN Size s ON ps.SizeID = s.SizeID "
                + "WHERE ps.ProductID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sizes.add(rs.getString("Type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizes;
    }
}
