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
        String sql = "SELECT * \n"
                + "FROM category";
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
        String sql = "SELECT * \n"
                + "FROM category \n"
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

//    test
    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        List<CategoryModel> list = dao.searchCategoryByName("nuoc");
        for (CategoryModel i : list) {
            System.out.println(i.getCategoryName());
        }
    }
}
