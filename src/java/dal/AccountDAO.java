package dal;

import common.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;

public class AccountDAO extends DBContext {

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
        String sql = "INSERT INTO Account (Username, Password, Name, Phone, Email, Address, Status, RoleID, CampusID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        if (!checkAccountIDExists(account.getAccountID())) {
            System.out.println("AccountID does not exist. Cannot edit.");
            return;
        }

        String sql = "UPDATE Account SET Username = ?, Password = ?, Name = ?, Phone = ?, Email = ?, Address = ?, Status = ?, RoleID = ?, CampusID = ? WHERE AccountID = ?";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getName());
            ps.setString(4, account.getPhone());
            ps.setString(5, account.getEmail());
            ps.setString(6, account.getAddress());
            ps.setBoolean(7, account.isStatus());
            ps.setInt(8, account.getRoleID());
            ps.setInt(9, account.getCampusID());
            ps.setInt(10, account.getAccountID());
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

    public boolean checkAccountIDExists(int accountID) {
        boolean exists = false;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT COUNT(*) FROM Account WHERE AccountID = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, accountID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return exists;
    }

    public List<Account> searchAccountsByUsername(String text) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT AccountID, Username, Password, Name, Phone, Email, Address, Status, RoleID, CampusID "
                + "FROM Account "
                + "WHERE LOWER(Username) LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + text.toLowerCase() + "%"); // Case insensitive search
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account account = new Account(
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
                list.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
