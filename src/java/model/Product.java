/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Product {

    private int productID;
    private String productName;
    private double costPrice;
    private double price;
    private String image;
    private String description;
    private String recipe;
    private boolean status;
    private int categoryId;

    // Constructors
    public Product() {
    }

    public Product(String productName, double costPrice, double price, String image, String description, String recipe, boolean status, int categoryId) {
        this.productName = productName;
        this.costPrice = costPrice;
        this.price = price;
        this.image = image;
        this.description = description;
        this.recipe = recipe;
        this.status = status;
        this.categoryId = categoryId;
    }

    // Getters and Setters
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

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // toString method (optional for debugging)
    @Override
    public String toString() {
        return "Product{"
                + "productID=" + productID
                + ", productName='" + productName + '\''
                + ", costPrice=" + costPrice
                + ", price=" + price
                + ", image='" + image + '\''
                + ", description='" + description + '\''
                + ", recipe='" + recipe + '\''
                + ", status=" + status
                + ", categoryId=" + categoryId
                + '}';
    }
}
