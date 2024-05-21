/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package categoryController;

import common.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO extends DBContext {

    public List<CategoryModel> getAllCategory() {
        List<CategoryModel> list = new ArrayList<>();
        String sql = "SELECT [CategoryID]\n"
                + "      ,[CategoryName]\n"
                + "      ,[Detail]\n"
                + "  FROM [SWP391_SU24].[dbo].[Category]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);//mo ket noi voi sql
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CategoryModel c = new CategoryModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<CategoryModel> searchCategoryByName(String text) {
        List<CategoryModel> list = new ArrayList<>();
        String sql = "SELECT [CategoryID]\n"
                + "      ,[CategoryName]\n"
                + "      ,[Detail]\n"
                + "  FROM [SWP391_SU24].[dbo].[Category] \n"
                + "WHERE LOWER(CategoryName) LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + text + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CategoryModel c = new CategoryModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void createNewCategory(String categoryName, String detail) {
        String sql = "INSERT INTO [dbo].[Category]\n"
                + "           ([CategoryName]\n"
                + "           ,[Detail])\n"
                + "     VALUES (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, categoryName);
            st.setString(2, detail);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateCategory(int categoryId, String categoryName, String detail) {
        String sql = "UPDATE [dbo].[Category]\n"
                + "   SET [CategoryName] = ?\n"
                + "      ,[Detail] = ?\n"
                + " WHERE CategoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, categoryName);
            st.setString(2, detail);
            st.setInt(3, categoryId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public CategoryModel getCategoryById(String categoryId) {
        CategoryModel category = new CategoryModel();
        String sql = "SELECT [CategoryID]\n"
                + "      ,[CategoryName]\n"
                + "      ,[Detail]\n"
                + "  FROM [SWP391_SU24].[dbo].[Category]"
                + "WHERE CategoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, categoryId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                category = new CategoryModel(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Detail"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return category;
    }

    public int getTotalCategory() {
        String sql = "SELECT COUNT(*)\n"
                + "  FROM [SWP391_SU24].[dbo].[Category]";
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

    public List<CategoryModel> getAllCategoryByPage(int indexPage, int pageSize) {
        List<CategoryModel> list = new ArrayList<>();
        String sql = "SELECT [CategoryID]\n"
                + "      ,[CategoryName]\n"
                + "      ,[Detail]\n"
                + "  FROM [SWP391_SU24].[dbo].[Category] "
                + "ORDER BY [CategoryID] "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (indexPage - 1) * pageSize); // Calculate offset
            st.setInt(2, pageSize); // Number of rows to fetch
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CategoryModel c = new CategoryModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

//    test data
    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
//        dao.createNewCategory("cafe", "Cà phê là một loại đồ uống phổ biến được làm từ hạt cà phê rang.");
//        dao.updateCategory(2, "Cafe", "Cà phê là một loại đồ uống phổ biến được làm từ hạt cà phê rang.");
        System.out.println(dao.getTotalCategory());

        List<CategoryModel> list = dao.getAllCategoryByPage(2, 2);
        for (CategoryModel i : list) {
            System.out.println(i.getCategoryName());
        }
    }
}
