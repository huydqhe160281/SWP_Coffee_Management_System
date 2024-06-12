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
 * Data Access Object for Product Handles database operations related to Product
 *
 */
public class ProductDAO extends DBContext {

    /**
     * Retrieve all products from the database
     *
     * @return List of products
     */
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.ProductID, p.ProductName, p.Image, p.Description, p.Recipe, p.Status, p.IsHot, "
                + "c.CategoryID, c.CategoryName, c.Detail "
                + "FROM [SWP391_SU24].[dbo].[Product] p "
                + "JOIN [SWP391_SU24].[dbo].[Category] c ON p.CategoryID = c.CategoryID";
        try ( PreparedStatement st = connection.prepareStatement(sql);  ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Detail")
                );
                Product p = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        rs.getString("Recipe"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("IsHot"),
                        category
                );
                list.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all products: " + e.getMessage());
        }
        return list;
    }

    /**
     * Retrieve products by category ID from the database
     *
     * @param categoryId The ID of the category
     * @return List of products in the specified category
     */
    public List<Product> getProductByCategoryId(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT p.ProductID, p.ProductName, p.Image, p.Description, p.Recipe, p.Status, p.IsHot, "
                + "c.CategoryID, c.CategoryName, c.Detail "
                + "FROM [SWP391_SU24].[dbo].[Product] p "
                + "JOIN [SWP391_SU24].[dbo].[Category] c ON p.CategoryID = c.CategoryID "
                + "WHERE p.CategoryID = ?";
        try ( PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, categoryId);
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category(
                            rs.getInt("CategoryID"),
                            rs.getString("CategoryName"),
                            rs.getString("Detail")
                    );
                    Product p = new Product(
                            rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getString("Image"),
                            rs.getString("Description"),
                            rs.getString("Recipe"),
                            rs.getBoolean("Status"),
                            rs.getBoolean("IsHot"),
                            category
                    );
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products by category ID: " + e.getMessage());
        }
        return list;
    }

    /**
     * Retrieve a product by its ID from the database
     *
     * @param productId The ID of the product
     * @return The product with the specified ID, or null if not found
     */
    public Product getProductById(String productId) {
        Product product = null;
        String sql = "SELECT p.ProductID, p.ProductName, p.Image, p.Description, p.Recipe, p.Status, p.IsHot, "
                + "c.CategoryID, c.CategoryName, c.Detail "
                + "FROM [SWP391_SU24].[dbo].[Product] p "
                + "JOIN [SWP391_SU24].[dbo].[Category] c ON p.CategoryID = c.CategoryID "
                + "WHERE p.ProductID = ?";
        try ( PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, productId);
            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category(
                            rs.getInt("CategoryID"),
                            rs.getString("CategoryName"),
                            rs.getString("Detail")
                    );
                    product = new Product(
                            rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getString("Image"),
                            rs.getString("Description"),
                            rs.getString("Recipe"),
                            rs.getBoolean("Status"),
                            rs.getBoolean("IsHot"),
                            category
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving product by ID: " + e.getMessage());
        }
        return product;
    }

    /**
     * Get the total number of products in the database
     *
     * @return Total number of products
     */
    public int getTotalProduct() {
        String sql = "SELECT COUNT(*) FROM [SWP391_SU24].[dbo].[Product]";
        try ( PreparedStatement st = connection.prepareStatement(sql);  ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving total number of products: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Retrieve products by page with sorting from the database
     *
     * @param indexPage The index of the page to retrieve
     * @param pageSize The number of products per page
     * @param sortType The sorting order ("ASC" or "DESC")
     * @return List of products for the specified page
     */
    public List<Product> getAllProductByPage(int indexPage, int pageSize, String sortType) {
        List<Product> list = new ArrayList<>();
        if (!sortType.equalsIgnoreCase("ASC") && !sortType.equalsIgnoreCase("DESC")) {
            sortType = "ASC";
        }
        String sql = "SELECT p.ProductID, p.ProductName, p.Image, p.Description, p.Recipe, p.Status, p.IsHot, "
                + "c.CategoryID, c.CategoryName, c.Detail "
                + "FROM [SWP391_SU24].[dbo].[Product] p "
                + "JOIN [SWP391_SU24].[dbo].[Category] c ON p.CategoryID = c.CategoryID "
                + "ORDER BY p.ProductID " + sortType + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try ( PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, (indexPage - 1) * pageSize);
            st.setInt(2, pageSize);
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Category category = new Category(
                            rs.getInt("CategoryID"),
                            rs.getString("CategoryName"),
                            rs.getString("Detail")
                    );
                    Product p = new Product(
                            rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getString("Image"),
                            rs.getString("Description"),
                            rs.getString("Recipe"),
                            rs.getBoolean("Status"),
                            rs.getBoolean("IsHot"),
                            category
                    );
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products by page: " + e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getProductByCategoryId(2);
        Product p = dao.getProductById("1");
        System.out.println(p);
        // Uncomment the following lines to print the list of products
        // for (Product i : list) {
        //     System.out.println(i);
        // }
    }
}
