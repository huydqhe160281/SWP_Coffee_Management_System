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
import model.Account;

/**
 *
 * @author Dinh Hai
 */
public class AccountDAO extends DBContext{
    public List<Account> getAllAccounts() {

        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Account";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getInt(9),
                        rs.getInt(10));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public Account getAccountByID(int id) {
        String sql = "SELECT * FROM Account WHERE AccountID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Phone"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleID"),
                        rs.getInt("CampusID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addAccount(Account account) {
        String sql = "INSERT INTO [dbo].[Account]\n" +
"           ([Username]\n" +
"           ,[Password]\n" +
"           ,[Name]\n" +
"           ,[Phone]\n" +
"           ,[Email]\n" +
"           ,[Address]\n" +
"           ,[Status]\n" +
"           ,[RoleID]\n" +
"           ,[CampusID])\n" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, account.getUsername());
                ps.setString(2, account.getPassword());
                ps.setString(3, account.getName());
                ps.setString(4, account.getPhone());
                ps.setString(5, account.getEmail());
                ps.setString(6, account.getAddress());
                ps.setBoolean(7, account.isStatus());
                ps.setInt(8, account.getRoleID());
                ps.setInt(9, account.getCampusID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editAccount(Account account) {
        String sql = "UPDATE Account SET Username = ?, Password = ?, Name = ?, Phone = ?, Email = ?, Address = ?, Status = ? WHERE AccountID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getName());
            ps.setString(4, account.getPhone());
            ps.setString(5, account.getEmail());
            ps.setString(6, account.getAddress());
            ps.setBoolean(7, account.isStatus());
            ps.setInt(8, account.getAccountID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(int AccountID) {
        String sql = "DELETE FROM Account WHERE AccountID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, AccountID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account checkLogin(String username, String password) {
        String sql = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("AccountID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Name"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getBoolean("Status"),
                        rs.getInt("RoleID"),
                        rs.getInt("CampusID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();
        Account acc = new Account(0, "Username", "Password", "Name", "Phone", "Email", "Address", true, 2, 1);
        accountDAO.addAccount(acc);
        //System.out.println(accounts);
    }
}
