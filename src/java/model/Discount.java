/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Namqd
 */
public class Discount {
    private int DiscountID;
    private int Value;
    private String Code;
    private Date StartDate;
    private Date EndDate;
    private double MaxDiscount;
    private int Quantity;

    public Discount() {
    }

    public Discount(int DiscountID, int Value, String Code, Date StartDate, Date EndDate, double MaxDiscount, int Quantity) {
        this.DiscountID = DiscountID;
        this.Value = Value;
        this.Code = Code;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.MaxDiscount = MaxDiscount;
        this.Quantity = Quantity;
    }

    public int getDiscountID() {
        return DiscountID;
    }

    public void setDiscountID(int DiscountID) {
        this.DiscountID = DiscountID;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int Value) {
        this.Value = Value;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    public double getMaxDiscount() {
        return MaxDiscount;
    }

    public void setMaxDiscount(double MaxDiscount) {
        this.MaxDiscount = MaxDiscount;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    
    
}
