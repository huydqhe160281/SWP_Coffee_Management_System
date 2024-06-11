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
    private Category category; // Đối tượng Category thay vì categoryId

    // Constructor
    public Product(int productID, String productName, double costPrice, double price, String image, String description, String recipe, boolean status, Category category) {
        this.productID = productID;
        this.productName = productName;
        this.costPrice = costPrice;
        this.price = price;
        this.image = image;
        this.description = description;
        this.recipe = recipe;
        this.status = status;
        this.category = category;
    }

    // Getters và Setters
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("productID=").append(productID);
        sb.append(", productName=").append(productName);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", price=").append(price);
        sb.append(", image=").append(image);
        sb.append(", description=").append(description);
        sb.append(", recipe=").append(recipe);
        sb.append(", status=").append(status);
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }

}
