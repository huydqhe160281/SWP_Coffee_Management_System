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

    public List<Product> getHotProducts() {
        List<Product> hotProducts = new ArrayList<>();
        String sql = "SELECT TOP 4 p.ProductID, p.ProductName, p.Image, p.Description, p.Recipe, p.Status, p.IsHot, "
                + "c.CategoryID, c.CategoryName, c.Detail "
                + "FROM [SWP391_SU24].[dbo].[Product] p "
                + "JOIN [SWP391_SU24].[dbo].[Category] c ON p.CategoryID = c.CategoryID "
                + "WHERE p.IsHot = 1 "
                + "ORDER BY NEWID()";  // NEWID() để lấy ra mẫu ngẫu nhiên

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
                hotProducts.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving hot products: " + e.getMessage());
        }
        return hotProducts;
    }

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

    public List<Product> getProductByCategoryIdByPage(String categoryId, int indexPage, int pageSize, String sortType) {
        List<Product> list = new ArrayList<>();
        if (!sortType.equalsIgnoreCase("ASC") && !sortType.equalsIgnoreCase("DESC")) {
            sortType = "ASC";
        }
        String sql = "SELECT p.ProductID, p.ProductName, p.Image, p.Description, p.Recipe, p.Status, p.IsHot, "
                + "c.CategoryID, c.CategoryName, c.Detail "
                + "FROM [SWP391_SU24].[dbo].[Product] p "
                + "JOIN [SWP391_SU24].[dbo].[Category] c ON p.CategoryID = c.CategoryID ";

        if (categoryId != null && !categoryId.isEmpty()) {
            sql += "WHERE p.CategoryID = ? ";
        }

        sql += "ORDER BY p.ProductID " + sortType + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try ( PreparedStatement st = connection.prepareStatement(sql)) {
            int parameterIndex = 1;

            if (categoryId != null && !categoryId.isEmpty()) {
                st.setString(parameterIndex++, categoryId);
            }

            st.setInt(parameterIndex++, (indexPage - 1) * pageSize);
            st.setInt(parameterIndex++, pageSize);

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
            System.err.println("Error retrieving products by category and page: " + e.getMessage());
        }
        return list;
    }

    public int getTotalProductByCategoryId(String categoryId) {
        String sql = "SELECT COUNT(*) "
                + "FROM [SWP391_SU24].[dbo].[Product] ";
        try {
            if (categoryId != null && !categoryId.isEmpty()) {
                sql += "WHERE CategoryID = ?";
            }
            PreparedStatement st = connection.prepareStatement(sql);

            if (categoryId != null && !categoryId.isEmpty()) {
                st.setString(1, categoryId);
            }

            try ( ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving total number of products: " + e.getMessage());
        }
        return 0;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getProductByCategoryIdByPage("", 1, 20, "ASC");
        Product p = dao.getProductById("1");
//        System.out.println(list);
        // Uncomment the following lines to print the list of products
        for (Product i : list) {
            System.out.println(i);
        }
    }
}
