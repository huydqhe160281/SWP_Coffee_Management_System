/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
	
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author Dinh Hai
 */
public class Account {
    private int AccountID;
    private String Username;
    private String Password;
    private String Name;
    private String Phone;
    private String Email;
    private String Address;
    private boolean Status;
    private int RoleID;
    private int CampusID;

    public Account() {
    }

    public Account(int AccountID, String Username, String Password, String Name, String Phone, String Email, String Address, boolean Status, int RoleID, int CampusID) {
        this.AccountID = AccountID;
        this.Username = Username;
        this.Password = Password;
        this.Name = Name;
        this.Phone = Phone;
        this.Email = Email;
        this.Address = Address;
        this.Status = Status;
        this.RoleID = RoleID;
        this.CampusID = CampusID;
    }

    public int getAccountID() {
        return AccountID;
    }

    public void setAccountID(int AccountID) {
        this.AccountID = AccountID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        if (isValidPhone(Phone)) {
        this.Phone = Phone;
            this.Phone = Phone;
        } else {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
    }
    private boolean isValidPhone(String phone) {
        String regex = "\\d{10}"; // Số điện thoại 10 chữ số
        return phone.matches(regex);
    }
    

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        if (isValidEmail(Email)) {
        this.Email = Email;
            this.Email = Email;
        } else {
            throw new IllegalArgumentException("Invalid email address format.");
        }
    }
    private boolean isValidEmail(String email) {
        // Biểu thức chính quy cho email
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public int getCampusID() {
        return CampusID;
    }

    public void setCampusID(int CampusID) {
        this.CampusID = CampusID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account{");
        sb.append("AccountID=").append(AccountID);
        sb.append(", Username=").append(Username);
        sb.append(", Password='").append(Password).append('\'');
        sb.append(", Name='").append(Name).append('\'');
        sb.append(", Phone='").append(Phone).append('\'');
        sb.append(", Email='").append(Email).append('\'');
        sb.append(", Address='").append(Address).append('\'');
        sb.append(", Status=").append(Status);
        sb.append(", RoleID=").append(RoleID);
        sb.append(", CampusID=").append(CampusID);
        sb.append('}');
        return sb.toString();
    }

}
