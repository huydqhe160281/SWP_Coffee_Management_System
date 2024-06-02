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
import model.General;

/**
 *
 * @author ADMIN
 */
public class GeneralDAO extends DBContext {

    public List<General> getGeneral() {
        List<General> accounts = new ArrayList<>();
        String sql = "SELECT TOP (1000) [GeneralID]\n"
                + "      ,[Email]\n"
                + "      ,[Phone]\n"
                + "      ,[NameApp]\n"
                + "      ,[Address]\n"
                + "      ,[LogoImage]\n"
                + "      ,[FivicoImage]\n"
                + "  FROM [SWP391_SU24].[dbo].[General]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int generalID = rs.getInt(1);
                String email = rs.getString(2);
                int phone = rs.getInt(3);
                String nameApp = rs.getString(4);
                String address = rs.getString(5);
                String logoImage = rs.getString(6);
                String fivicoImage = rs.getString(7);

                General general = new General(generalID, email, phone, nameApp, address, logoImage, fivicoImage);
                accounts.add(general);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public General getLastGeneral() {
        General general = null;
        String sql = "SELECT TOP 1 [GeneralID], [Email], [Phone], [NameApp], [Address], [LogoImage], [FivicoImage] "
                + "FROM [SWP391_SU24].[dbo].[General] "
                + "ORDER BY [GeneralID] DESC";
        try ( PreparedStatement ps = connection.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int generalID = rs.getInt("GeneralID");
                String email = rs.getString("Email");
                int phone = rs.getInt("Phone");
                String nameApp = rs.getString("NameApp");
                String address = rs.getString("Address");
                String logoImage = rs.getString("LogoImage");
                String fivicoImage = rs.getString("FivicoImage");
                general = new General(generalID, email, phone, nameApp, address, logoImage, fivicoImage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return general;
    }

    public boolean updateGeneral(General general, int searchGeneralID) {
        String sql = "UPDATE [dbo].[General] "
                + "SET [GeneralID] = ?, "
                + "[Email] = ?, "
                + "[Phone] = ?, "
                + "[NameApp] = ?, "
                + "[Address] = ?, "
                + "[LogoImage] = ?, "
                + "[FivicoImage] = ? "
                + "WHERE [GeneralID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, general.getGeneralID());
            ps.setString(2, general.getEmail());
            ps.setInt(3, general.getPhone());
            ps.setString(4, general.getNameApp());
            ps.setString(5, general.getAddress());
            ps.setString(6, general.getLogoImage());
            ps.setString(7, general.getFivicoImage());
            ps.setInt(8, searchGeneralID);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        GeneralDAO dao = new GeneralDAO();
        System.out.println(dao.getLastGeneral());
    }
}
