package dal;

import common.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Category;
import model.Discount;
import model.Order;
import model.OrderDetail;
import model.ProductSize;
import model.RevenueData;
import model.SalesData;
import model.Size;
import model.StaffOrder;

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
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
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

    public int saveOrder(Order order) throws SQLException {
        String sql = "INSERT INTO Orders (AccountID, OrderDate, Status, Cancelled) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getAccountID());
            ps.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setBoolean(3, order.isStatus());
            ps.setBoolean(4, order.isCancelled());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    public void saveOrderDetail(OrderDetail detail) throws SQLException {
        String sql = "INSERT INTO OrderDetails (OrderID, ProductID, ProductName, UnitPrice, Quantity, Note, DiscountID, Value) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, detail.getOrderID());
            ps.setInt(2, detail.getProductID());
            ps.setString(3, detail.getProductName());
            ps.setDouble(4, detail.getUnitPrice());
            ps.setInt(5, detail.getQuantity());
            ps.setString(6, detail.getNote());
            ps.setInt(7, detail.getDiscountID());
            ps.setInt(8, detail.getValue());
            ps.executeUpdate();
        }
    }

    public void saveOrderDiscount(int orderId, int discountId) throws SQLException {
        String query = "UPDATE Orders SET discountID = ? WHERE orderID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, discountId);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        }
    }
    public Order getOrderByOrderID(int orderID) throws SQLException {
        String orderQuery = "SELECT o.OrderID, o.AccountID, a.Name AS AccountName, o.OrderDate, o.Status, o.Cancelled "
                + "FROM [Order] o "
                + "JOIN Account a ON o.AccountID = a.AccountID "
                + "WHERE o.OrderID = ?";
        Order order = null;
        try ( PreparedStatement orderStmt = connection.prepareStatement(orderQuery)) {
            orderStmt.setInt(1, orderID);
            ResultSet rs = orderStmt.executeQuery();
            if (rs.next()) {
                order = new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("AccountID"),
                        rs.getString("AccountName"),
                        rs.getDate("OrderDate"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("Cancelled"),
                        getOrderDetails(orderID)
                );
            }
        }
        return order;
    }

    public List<OrderDetail> getOrderDetails(int orderID) throws SQLException {
        List<OrderDetail> details = new ArrayList<>();
        String detailQuery = "SELECT od.OrderID, od.ProductID, p.ProductName, od.UnitPrice, od.Quantity, od.Note, od.DiscountID, d.Value "
                + "FROM OrderDetail od "
                + "JOIN Product p ON od.ProductID = p.ProductID "
                + "LEFT JOIN Discount d ON od.DiscountID = d.DiscountID "
                + "WHERE od.OrderID = ?";
        try ( PreparedStatement detailStmt = connection.prepareStatement(detailQuery)) {
            detailStmt.setInt(1, orderID);
            ResultSet rs = detailStmt.executeQuery();
            while (rs.next()) {
                OrderDetail detail = new OrderDetail(
                        rs.getInt("OrderID"),
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("UnitPrice"),
                        rs.getInt("Quantity"),
                        rs.getString("Note"),
                        rs.getInt("DiscountID"),
                        rs.getInt("Value")
                );
                details.add(detail);
            }
        }
        return details;
    }

    public int getTotalOrders() throws SQLException {
        String query = "SELECT COUNT(*) FROM [Order]";
        try ( Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public List<OrderDetail> getOrderDetailsByDate(Date orderDate) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT orderID, productID, productName, unitPrice, quantity, note, discountID, value FROM OrderDetails WHERE orderDate = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(orderDate.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail detail = new OrderDetail(
                        rs.getInt("orderID"),
                        rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getDouble("unitPrice"),
                        rs.getInt("quantity"),
                        rs.getString("note"),
                        rs.getInt("discountID"),
                        rs.getInt("value")
                );
                orderDetails.add(detail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    public List<RevenueData> getRevenueByDay() {
        List<RevenueData> list = new ArrayList<>();
        String query = "SELECT CONVERT(date, OrderDate) as OrderDate, SUM(UnitPrice * Quantity) as Revenue "
                + "FROM [Order] JOIN OrderDetail ON [Order].OrderID = OrderDetail.OrderID "
                + "GROUP BY CONVERT(date, OrderDate)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RevenueData(rs.getDate("OrderDate"), rs.getDouble("Revenue")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SalesData> getSalesByDay() {
        List<SalesData> list = new ArrayList<>();
        String query = "SELECT CONVERT(date, OrderDate) as OrderDate, SUM(Quantity) as Quantity "
                + "FROM [Order] JOIN OrderDetail ON [Order].OrderID = OrderDetail.OrderID "
                + "GROUP BY CONVERT(date, OrderDate)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SalesData(rs.getDate("OrderDate"), rs.getInt("Quantity")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<RevenueData> getRevenueByDateRange(Date fromDate, Date toDate) {
        List<RevenueData> revenueDataList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String query = "SELECT o.OrderDate, SUM(od.UnitPrice * od.Quantity) AS Revenue "
                + "FROM [Order] o "
                + "JOIN OrderDetail od ON o.OrderID = od.OrderID "
                + "WHERE o.OrderDate BETWEEN ? AND ? "
                + "GROUP BY o.OrderDate";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, sdf.format(fromDate));
            ps.setString(2, sdf.format(toDate));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date orderDate = rs.getDate("OrderDate");
                double revenue = rs.getDouble("Revenue");
                revenueDataList.add(new RevenueData(orderDate, revenue));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenueDataList;
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
        String sql = "INSERT INTO [Order] (AccountID, OrderDate, Status, Cancelled) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getAccountID());
            ps.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            ps.setBoolean(3, order.isStatus());
            ps.setBoolean(4, order.isCancelled());
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

    public List<StaffOrder> getProductsByCategory(int categoryID) {
        List<StaffOrder> products = new ArrayList<>();
        String sql = "SELECT DISTINCT p.ProductID, p.ProductName, p.Image, p.Description, c.CategoryID, c.CategoryName "
                + "FROM Product p "
                + "JOIN Category c ON p.CategoryID = c.CategoryID "
                + "WHERE p.CategoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        null // Thêm thông tin chi tiết nếu cần thiết
                );
                StaffOrder product = new StaffOrder(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        category,
                        0, // Default sizeID
                        "", // Default type
                        0, // Default price
                        0, // Default quantity
                        0, // Default orderID
                        0, // Default discountID
                        "" // Default note
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public double getPriceByProductAndSize(int productID, String size) {
        String sql = "SELECT ps.Price "
                + "FROM ProductSize ps "
                + "JOIN Size s ON ps.SizeID = s.SizeID "
                + "WHERE ps.ProductID = ? AND s.Type = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setString(2, size);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> getSizesByProduct(int productID) throws SQLException {
        List<String> sizes = new ArrayList<>();
        String query = "SELECT s.Type FROM ProductSize ps JOIN Size s ON ps.SizeID = s.SizeID WHERE ps.ProductID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sizes.add(rs.getString("Type"));
            }
        }
        return sizes;
    }

    public List<StaffOrder> getProductsByCategoryWithSizes(int categoryID) {
        List<StaffOrder> products = new ArrayList<>();
        String sql = "SELECT p.ProductID, p.ProductName, s.Type AS SizeType, ps.Price "
                + "FROM Product p "
                + "JOIN ProductSize ps ON p.ProductID = ps.ProductID "
                + "JOIN Size s ON ps.SizeID = s.SizeID "
                + "WHERE p.CategoryID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StaffOrder product = new StaffOrder(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        null, // image
                        null, // description
                        null, // category
                        0, // sizeID
                        rs.getString("SizeType"),
                        rs.getDouble("Price"),
                        0, // quantity
                        0, // orderID
                        0, // discountID
                        "" // note
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Discount> getActiveDiscounts() throws SQLException {
        List<Discount> discounts = new ArrayList<>();
        String query = "SELECT * FROM Discount WHERE Status = 1";
        try ( PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Discount discount = new Discount();
                discount.setDiscountID(rs.getInt("DiscountID"));
                discount.setValue(rs.getInt("Value"));
                discount.setCode(rs.getString("Code"));
                discount.setStartDate(rs.getDate("StartDate"));
                discount.setEndDate(rs.getDate("EndDate"));
                discount.setMaxDiscount(rs.getDouble("MaxDiscount"));
                discount.setQuantity(rs.getInt("Quantity"));
                discount.setStatus(rs.getBoolean("Status"));
                discounts.add(discount);
            }
        }
        return discounts;
    }

}
