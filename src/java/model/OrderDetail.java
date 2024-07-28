/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DecimalFormat;

/**
 *
 * @author Namqd
 */
public class OrderDetail {
    private int orderID;
    private int productID;
    private String productName;
    private double unitPrice;
    private int quantity;
    private String note;
    private int discountID;
    private int value;

    public OrderDetail() {
    }

    public OrderDetail(int orderID, int productID, String productName, double unitPrice, int quantity, String note, int discountID, int value) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.note = note;
        this.discountID = discountID;
        this.value = value;
    }

    public OrderDetail(int orderID, int productID, double unitPrice, int quantity, String note, int discountID) {
        this.orderID = orderID;
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.note = note;
        this.discountID = discountID;
    }

    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnitPrice() {
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(unitPrice);
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderID=" + orderID + ", productID=" + productID + ", productName=" + productName + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", note=" + note + ", discountID=" + discountID + ", value=" + value + '}';
    }
 
    
}
