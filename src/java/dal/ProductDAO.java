package dal;

import common.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;
import model.ProductSize;
import model.Size;

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
                List<ProductSize> productSizes = getProductSizesByProductId(rs.getInt("ProductID"));
                Product p = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        rs.getString("Recipe"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("IsHot"),
                        category,
                        productSizes
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
                + "ORDER BY NEWID()";

        try ( PreparedStatement st = connection.prepareStatement(sql);  ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Detail")
                );
                List<ProductSize> productSizes = getProductSizesByProductId(rs.getInt("ProductID"));
                Product p = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Image"),
                        rs.getString("Description"),
                        rs.getString("Recipe"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("IsHot"),
                        category,
                        productSizes
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
                    List<ProductSize> productSizes = getProductSizesByProductId(rs.getInt("ProductID"));
                    Product p = new Product(
                            rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getString("Image"),
                            rs.getString("Description"),
                            rs.getString("Recipe"),
                            rs.getBoolean("Status"),
                            rs.getBoolean("IsHot"),
                            category,
                            productSizes
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
                    List<ProductSize> productSizes = getProductSizesByProductId(rs.getInt("ProductID"));
                    product = new Product(
                            rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getString("Image"),
                            rs.getString("Description"),
                            rs.getString("Recipe"),
                            rs.getBoolean("Status"),
                            rs.getBoolean("IsHot"),
                            category,
                            productSizes
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
                    List<ProductSize> productSizes = getProductSizesByProductId(rs.getInt("ProductID"));
                    Product p = new Product(
                            rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getString("Image"),
                            rs.getString("Description"),
                            rs.getString("Recipe"),
                            rs.getBoolean("Status"),
                            rs.getBoolean("IsHot"),
                            category,
                            productSizes
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

    private List<ProductSize> getProductSizesByProductId(int productId) {
        List<ProductSize> productSizes = new ArrayList<>();
        String sql = "SELECT ps.ProductID, ps.SizeID, ps.Price, s.SizeID, s.Description, s.Type "
                + "FROM [SWP391_SU24].[dbo].[ProductSize] ps "
                + "JOIN [SWP391_SU24].[dbo].[Size] s ON ps.SizeID = s.SizeID "
                + "WHERE ps.ProductID = ?";
        try ( PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, productId);
            try ( ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Size size = new Size(
                            rs.getInt("SizeID"),
                            rs.getString("Type"),
                            rs.getString("Description")
                    );
                    ProductSize productSize = new ProductSize(
                            rs.getInt("ProductID"),
                            rs.getInt("SizeID"),
                            rs.getDouble("Price"),
                            size
                    );
                    productSizes.add(productSize);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving product sizes: " + e.getMessage());
        }
        return productSizes;
    }

    public void updateProductStatus(int productId, String field, boolean newValue) {
        String sql = "UPDATE [SWP391_SU24].[dbo].[Product] SET " + field + " = ? WHERE ProductID = ?";
        try ( PreparedStatement st = connection.prepareStatement(sql)) {
            st.setBoolean(1, newValue);
            st.setInt(2, productId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating product status: " + e.getMessage());
        }
    }

    public void createNewProduct(Product product, List<ProductSize> productSizes) {
        String insertProductSQL = "INSERT INTO [SWP391_SU24].[dbo].[Product] "
                + "(ProductName, Image, Description, Recipe, Status, IsHot, CategoryID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String insertProductSizeSQL = "INSERT INTO [SWP391_SU24].[dbo].[ProductSize] "
                + "(ProductID, SizeID, Price) VALUES (?, ?, ?)";

        try {
            connection.setAutoCommit(false);

            try ( PreparedStatement st = connection.prepareStatement(insertProductSQL, Statement.RETURN_GENERATED_KEYS)) {
                st.setString(1, product.getProductName());
                st.setString(2, product.getImage());
                st.setString(3, product.getDescription());
                st.setString(4, product.getRecipe());
                st.setBoolean(5, product.isStatus());
                st.setBoolean(6, product.isIsHot());
                st.setInt(7, product.getCategory().getCategoryID());
                st.executeUpdate();

                try ( ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        product.setProductID(rs.getInt(1));
                    }
                }
            }

            try ( PreparedStatement st = connection.prepareStatement(insertProductSizeSQL)) {
                for (ProductSize ps : productSizes) {
                    st.setInt(1, product.getProductID());
                    st.setInt(2, ps.getSize().getSizeID());
                    st.setDouble(3, ps.getPrice());
                    st.addBatch();
                }
                st.executeBatch();
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error rolling back transaction: " + ex.getMessage());
            }
            System.err.println("Error creating new product: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    public void updateProduct(Product product, List<ProductSize> productSizes) {
        String updateProductSQL = "UPDATE [SWP391_SU24].[dbo].[Product] SET "
                + "ProductName = ?, Image = ?, Description = ?, Recipe = ?, Status = ?, IsHot = ?, CategoryID = ? "
                + "WHERE ProductID = ?";

        String deleteProductSizeSQL = "DELETE FROM [SWP391_SU24].[dbo].[ProductSize] WHERE ProductID = ?";

        String insertProductSizeSQL = "INSERT INTO [SWP391_SU24].[dbo].[ProductSize] "
                + "(ProductID, SizeID, Price) VALUES (?, ?, ?)";

        try {
            connection.setAutoCommit(false);

            try ( PreparedStatement st = connection.prepareStatement(updateProductSQL)) {
                st.setString(1, product.getProductName());
                st.setString(2, product.getImage());
                st.setString(3, product.getDescription());
                st.setString(4, product.getRecipe());
                st.setBoolean(5, product.isStatus());
                st.setBoolean(6, product.isIsHot());
                st.setInt(7, product.getCategory().getCategoryID());
                st.setInt(8, product.getProductID());
                st.executeUpdate();
            }

            try ( PreparedStatement st = connection.prepareStatement(deleteProductSizeSQL)) {
                st.setInt(1, product.getProductID());
                st.executeUpdate();
            }

            try ( PreparedStatement st = connection.prepareStatement(insertProductSizeSQL)) {
                for (ProductSize ps : productSizes) {
                    st.setInt(1, product.getProductID());
                    st.setInt(2, ps.getSize().getSizeID());
                    st.setDouble(3, ps.getPrice());
                    st.addBatch();
                }
                st.executeBatch();
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("Error rolling back transaction: " + ex.getMessage());
            }
            System.err.println("Error updating product: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getProductByCategoryIdByPage("", 1, 20, "ASC");
        Product p = dao.getProductById("1");

//        for (Product i : list) {
//            System.out.println(i);
//        }
    }
}
