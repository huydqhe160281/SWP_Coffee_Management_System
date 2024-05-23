/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {
    
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
}
