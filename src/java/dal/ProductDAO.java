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
import model.Category;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class ProductDAO extends DBContext {

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.ProductID, p.ProductName, p.CostPrice, p.Price, p.Image, p.Description, p.Recipe, p.Status, "
                + "c.CategoryID, c.CategoryName, c.Detail "
                + "FROM [SWP391_SU24].[dbo].[Product] p "
                + "JOIN [SWP391_SU24].[dbo].[Category] c ON p.CategoryID = c.CategoryID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Detail")
                );
                Product p = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("CostPrice"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        rs.getString("Recipe"),
                        rs.getBoolean("Status"),
                        category
                );
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
        String sql = "SELECT p.ProductID, p.ProductName, p.CostPrice, p.Price, p.Image, p.Description, p.Recipe, p.Status, "
                + "c.CategoryID, c.CategoryName, c.Detail "
                + "FROM [SWP391_SU24].[dbo].[Product] p "
                + "JOIN [SWP391_SU24].[dbo].[Category] c ON p.CategoryID = c.CategoryID "
                + "WHERE p.CategoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Detail")
                );
                Product p = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("CostPrice"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        rs.getString("Recipe"),
                        rs.getBoolean("Status"),
                        category
                );
                list.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

//
    public Product getProductById(String productId) {
        Product product = null;
        String sql = "SELECT p.ProductID, p.ProductName, p.CostPrice, p.Price, p.Image, p.Description, p.Recipe, p.Status, "
                + "c.CategoryID, c.CategoryName, c.Detail "
                + "FROM [SWP391_SU24].[dbo].[Product] p "
                + "JOIN [SWP391_SU24].[dbo].[Category] c ON p.CategoryID = c.CategoryID "
                + "WHERE p.ProductID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Detail")
                );
                product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("CostPrice"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        rs.getString("Recipe"),
                        rs.getBoolean("Status"),
                        category
                );
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return product;
    }

    public int getTotalProduct() {
        String sql = "SELECT COUNT(*) FROM [SWP391_SU24].[dbo].[Product]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            rs.close();
            st.close();
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
        String sql = "SELECT p.ProductID, p.ProductName, p.CostPrice, p.Price, p.Image, p.Description, p.Recipe, p.Status, "
                + "c.CategoryID, c.CategoryName, c.Detail "
                + "FROM [SWP391_SU24].[dbo].[Product] p "
                + "JOIN [SWP391_SU24].[dbo].[Category] c ON p.CategoryID = c.CategoryID "
                + "ORDER BY p.ProductID " + sortType + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (indexPage - 1) * pageSize);
            st.setInt(2, pageSize);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Detail")
                );
                Product p = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("CostPrice"),
                        rs.getDouble("Price"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        rs.getString("Recipe"),
                        rs.getBoolean("Status"),
                        category
                );
                list.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

//
//    public boolean updateProductStatus(int productId, boolean status) {
//        String sql = "UPDATE [SWP391_SU24].[dbo].[Product] "
//                + "SET [Status] = ? "
//                + "WHERE [ProductID] = ?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setBoolean(1, status);
//            st.setInt(2, productId);
//            int rowsUpdated = st.executeUpdate();
//            st.close();
//            // If rowsUpdated is greater than 0, the update was successful
//            return rowsUpdated > 0;
//        } catch (SQLException e) {
//            System.out.println(e);
//            return false;
//        }
//    }
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getProductByCategoryId(2);
        Product p = dao.getProductById("1");
        System.out.println(p);
//        for (Product i : list) {
//            System.out.println(i);
//        }
    }
}
