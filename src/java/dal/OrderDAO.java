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
import model.Product;
import model.ProductOrder;

/**
 *
 * @author Nam
 */
public class OrderDAO extends DBContext {

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.OrderID, o.AccountID, a.Name AS AccountName, o.OrderDate "
                + "FROM [Order] o "
                + "JOIN Account a ON o.AccountID = a.AccountID";
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
        String sql = "SELECT o.OrderID, o.AccountID, a.Name AS AccountName, o.OrderDate "
                + "FROM [Order] o "
                + "JOIN Account a ON o.AccountID = a.AccountID "
                + "WHERE o.OrderID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
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
        String sql = "SELECT od.OrderID, od.ProductID, p.ProductName, od.UnitPrice, od.Quantity, od.Note, d.Value AS DiscountValue, d.DiscountID "
                + "FROM OrderDetail od "
                + "JOIN Product p ON od.ProductID = p.ProductID "
                + "LEFT JOIN Discount d ON od.DiscountID = d.DiscountID "
                + "WHERE od.OrderID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
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
                        rs.getInt("DiscountValue")
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

    public List<ProductOrder> getProductsByCategory(int categoryID) {
        List<ProductOrder> products = new ArrayList<>();
        String sql = "SELECT p.ProductID, p.ProductName, ps.Price "
                + "FROM Product p "
                + "JOIN ProductSize ps ON p.ProductID = ps.ProductID "
                + "WHERE p.CategoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductOrder product = new ProductOrder(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<ProductOrder> getProductsByCategoryWithPrice(int categoryID) {
        List<ProductOrder> products = new ArrayList<>();
        String query = "SELECT p.ProductID, p.ProductName, p.Image, p.Description, p.Recipe, p.Status, p.isHot, p.CategoryID, ps.SizeID, ps.Price, s.Type AS SizeType "
                + "FROM Product p "
                + "JOIN ProductSize ps ON p.ProductID = ps.ProductID "
                + "JOIN Size s ON ps.SizeID = s.SizeID "
                + "WHERE p.CategoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductOrder product = new ProductOrder();
                product.setProductID(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));

                product.setPrice(rs.getDouble("Price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private List<ProductOrder> getDummyProducts(int categoryID) {
        // This method returns dummy products for demonstration purposes.
        List<ProductOrder> products = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            products.add(new ProductOrder(i, "Product " + categoryID + "-" + i, categoryID * 10.0 + i));
        }
        return products;
    }
    
    public static void main(String[] args) {
        int categoryID = 1;
        OrderDAO dao = new OrderDAO();
        ProductDAO pdao = new ProductDAO();
        List<Product> products = pdao.getProductByCategoryId(categoryID);

        // In ra danh sách sản phẩm để kiểm tra
        if (products != null && !products.isEmpty()) {
            for (Product product : products) {
                System.out.println("Product ID: " + product.getProductID());
                System.out.println("Product Name: " + product.getProductName());
                System.out.println("-----------------------------");
            }
        } else {
            System.out.println("No products found for category ID: " + categoryID);
        }
    }
}
