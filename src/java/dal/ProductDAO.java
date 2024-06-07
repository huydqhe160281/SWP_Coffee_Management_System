/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import common.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

//    public void createNewCategory(String categoryName, String detail, List productIng ) {
//        String sql = "INSERT INTO [dbo].[Category]\n"
//                + "           ([CategoryName]\n"
//                + "           ,[Detail])\n"
//                + "     VALUES (?, ?)";
//        
//         String sql1 = "INSERT INTO [dbo].[Category]\n"
//                + "           ([CategoryName]\n"
//                + "           ,[Detail])\n"
//                + "     VALUES (?, ?)";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
//            st.setString(1, categoryName);
//            st.setString(2, detail);
//            int affectedRows = st.executeUpdate();
//
//        // Check if the insert was successful
//        if (affectedRows > 0) {
//            // Retrieve the generated keys
//            ResultSet rs = st.getGeneratedKeys();
//            if (rs.next()) {
//                int generatedId = rs.getInt(1); // Assuming the ID is the first column in the generated keys
//                System.out.println("Inserted category ID: " + generatedId);
//                for (Object item : productIng) {
//                   PreparedStatement st1 = connection.prepareStatement(sql1);
//            st1.setString(1, categoryName);
//            st1.setString(2, detail);
//            st1.executeUpdate();
//
//                }
//            }
//        }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [ProductID]\n"
                + "      ,[ProductName]\n"
                + "      ,[CostPrice]\n"
                + "      ,[Price]\n"
                + "      ,[Image]\n"
                + "      ,[Description]\n"
                + "      ,[Recipe]\n"
                + "      ,[Status]\n"
                + "      ,[CategoryID]\n"
                + "  FROM [SWP391_SU24].[dbo].[Product]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getInt(9));
                list.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [ProductID]\n"
                + "      ,[ProductName]\n"
                + "      ,[CostPrice]\n"
                + "      ,[Price]\n"
                + "      ,[Image]\n"
                + "      ,[Description]\n"
                + "      ,[Recipe]\n"
                + "      ,[Status]\n"
                + "      ,[CategoryID]\n"
                + "  FROM [SWP391_SU24].[dbo].[Product]\n"
                + "  WHERE [CategoryID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getInt(9));
                list.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductById(String productId) {
        Product product = null;
        String sql = "SELECT [ProductID]\n"
                + "      ,[ProductName]\n"
                + "      ,[CostPrice]\n"
                + "      ,[Price]\n"
                + "      ,[Image]\n"
                + "      ,[Description]\n"
                + "      ,[Recipe]\n"
                + "      ,[Status]\n"
                + "      ,[CategoryID]\n"
                + "  FROM [SWP391_SU24].[dbo].[Product]\n"
                + "  WHERE [ProductID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getInt(9));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return product;
    }

    public int getTotalProduct() {
        String sql = "SELECT COUNT(*)\n"
                + "  FROM [SWP391_SU24].[dbo].[Product]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Product> getAllProductByPage(int indexPage, int pageSize, String sortType) {
        List<Product> list = new ArrayList<>();
        if (!sortType.equalsIgnoreCase("ASC") && !sortType.equalsIgnoreCase("DESC")) {
            sortType = "ASC";
        }
        String sql = "SELECT [ProductID]\n"
                + "      ,[ProductName]\n"
                + "      ,[CostPrice]\n"
                + "      ,[Price]\n"
                + "      ,[Image]\n"
                + "      ,[Description]\n"
                + "      ,[Recipe]\n"
                + "      ,[Status]\n"
                + "      ,[CategoryID]\n"
                + "  FROM [SWP391_SU24].[dbo].[Product]\n"
                + "ORDER BY [ProductID] " + sortType + "\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (indexPage - 1) * pageSize);
            st.setInt(2, pageSize);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getInt(9));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean updateProductStatus(int productId, boolean status) {
        String sql = "UPDATE [SWP391_SU24].[dbo].[Product] "
                + "SET [Status] = ? "
                + "WHERE [ProductID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setBoolean(1, status);
            st.setInt(2, productId);
            int rowsUpdated = st.executeUpdate();
            st.close();
            // If rowsUpdated is greater than 0, the update was successful
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getAllProductByPage(1, 1, "asc");
        Product p = dao.getProductById("1");
        System.out.println(p);
//        for (Product i : list) {
//            System.out.println(i);
//        }
    }
}
