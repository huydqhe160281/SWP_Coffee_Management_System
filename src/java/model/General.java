/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class General {

    private int generalID;
    private String email;
    private int phone;
    private String nameApp;
    private String address;
    private String logoImage;
    private String fivicoImage;

    // Default constructor
    public General() {
    }

    // Parameterized constructor
    public General(int generalID, String email, int phone, String nameApp, String address, String logoImage, String fivicoImage) {
        this.generalID = generalID;
        this.email = email;
        this.phone = phone;
        this.nameApp = nameApp;
        this.address = address;
        this.logoImage = logoImage;
        this.fivicoImage = fivicoImage;
    }

    // Getters and Setters
    public int getGeneralID() {
        return generalID;
    }

    public void setGeneralID(int generalID) {
        this.generalID = generalID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getNameApp() {
        return nameApp;
    }

    public void setNameApp(String nameApp) {
        this.nameApp = nameApp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getFivicoImage() {
        return fivicoImage;
    }

    public void setFivicoImage(String fivicoImage) {
        this.fivicoImage = fivicoImage;
    }

    // toString method
    @Override
    public String toString() {
        return "General{"
                + "generalID=" + generalID
                + ", email='" + email + '\''
                + ", phone='" + phone + '\''
                + ", nameApp='" + nameApp + '\''
                + ", address='" + address + '\''
                + ", logoImage='" + logoImage + '\''
                + ", fivicoImage='" + fivicoImage + '\''
                + '}';
    }
}
