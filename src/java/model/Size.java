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
    private int SizeID;
    private String Type;
    private String Description;

    public Size() {
    }

    public Size(int SizeID, String Type, String Description) {
        this.SizeID = SizeID;
        this.Type = Type;
        this.Description = Description;
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

    @Override
    public String toString() {
        return "Size{" + "sizeID=" + sizeID + ", type=" + type + '}';
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
}
