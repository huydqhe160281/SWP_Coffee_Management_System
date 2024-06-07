/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import common.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Supplier;

/**
 *
 * @author Namqd
 */
public class SupplierDAO extends DBContext{
    
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM Supplier";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierID(rs.getInt("SupplierID"));
                supplier.setSupplierName(rs.getString("SupplierName"));
                supplier.setContact(rs.getString("Contact"));
                supplier.setAddress(rs.getString("Address"));
                suppliers.add(supplier);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public Supplier getSupplierById(int id) {
        Supplier supplier = null;
        String query = "SELECT * FROM Supplier WHERE supplierID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    supplier = new Supplier();
                    supplier.setSupplierID(rs.getInt("supplierID"));
                    supplier.setSupplierName(rs.getString("supplierName"));
                    supplier.setContact(rs.getString("contact"));
                    supplier.setAddress(rs.getString("address"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplier;
    }

    public boolean addSupplier(Supplier supplier) {
        String query = "INSERT INTO Supplier (supplierName, contact, address) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContact());
            ps.setString(3, supplier.getAddress());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSupplier(Supplier supplier) {
        String query = "UPDATE Supplier SET supplierName = ?, contact = ?, address = ? WHERE supplierID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContact());
            ps.setString(3, supplier.getAddress());
            ps.setInt(4, supplier.getSupplierID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteSupplier(int id) {
        String query = "DELETE FROM Supplier WHERE supplierID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
