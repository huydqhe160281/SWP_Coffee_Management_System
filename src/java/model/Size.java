/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dinh Hai
 */
public class Size {
    private int sizeID;
    private String type;
    private String description;

    public Size() {
    }

    public Size(int sizeID, String type, String description) {
        this.sizeID = sizeID;
        this.type = type;
        this.description = description;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Size{" + "sizeID=" + sizeID + ", type=" + type + ", description=" + description + '}';
    }

    
}
