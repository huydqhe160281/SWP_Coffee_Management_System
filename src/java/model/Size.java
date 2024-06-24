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
        return SizeID;
    }

    public void setSizeID(int SizeID) {
        this.SizeID = SizeID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    
}

