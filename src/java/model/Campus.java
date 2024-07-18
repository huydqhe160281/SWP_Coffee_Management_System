/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dinh Hai
 */
public class Campus {
    int campusID;
    String campusName;
    String address;

    public Campus() {
    }

    public Campus(int campusID, String campusName, String address) {
        this.campusID = campusID;
        this.campusName = campusName;
        this.address = address;
    }

    public int getCampusID() {
        return campusID;
    }

    public void setCampusID(int campusID) {
        this.campusID = campusID;
    }

    public String getCampusName() {
        return campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
