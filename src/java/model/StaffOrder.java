/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Namqd
 */
public class StaffOrder {
    private int productID;
    private String productName;
    private String image;
    private String description;
    private Category category;
    private int sizeID;
    private String type;
    private double price;
    private int quantity;
    private int orderID;  
    private int discountID; 
    private String note;

    public StaffOrder() {
    }

    public StaffOrder(int productID, String productName, String image, String description, Category category, int sizeID, String type, double price, int quantity, int orderID, int discountID, String note) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.category = category;
        this.sizeID = sizeID;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.orderID = orderID;
        this.discountID = discountID;
        this.note = note;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "StaffOrder{" + "productID=" + productID + ", productName=" + productName + ", image=" + image + ", description=" + description + ", category=" + category + ", sizeID=" + sizeID + ", type=" + type + ", price=" + price + ", quantity=" + quantity + ", orderID=" + orderID + ", discountID=" + discountID + ", note=" + note + '}';
    }

}
