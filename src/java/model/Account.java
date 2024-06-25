/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

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
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
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
        sb.append(", Password=").append(Password);
        sb.append(", Name=").append(Name);
        sb.append(", Phone=").append(Phone);
        sb.append(", Email=").append(Email);
        sb.append(", Address=").append(Address);
        sb.append(", Status=").append(Status);
        sb.append(", RoleID=").append(RoleID);
        sb.append(", CampusID=").append(CampusID);
        sb.append('}');
        return sb.toString();
    }

}
