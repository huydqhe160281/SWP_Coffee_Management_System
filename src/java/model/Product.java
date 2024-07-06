/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Product {

    private int productID;
    private String productName;
    private String image;
    private String description;
    private String recipe;
    private boolean status;
    private boolean isHot;
    private Category category;
    private List<ProductSize> productSizes;

    public Product() {
    }

    public Product(int productID, String productName, String image, String description, String recipe, boolean status, boolean isHot, Category category, List<ProductSize> productSizes) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.recipe = recipe;
        this.status = status;
        this.isHot = isHot;
        this.category = category;
        this.productSizes = productSizes;
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

    public boolean isIsHot() {
        return isHot;
    }

    public void setIsHot(boolean isHot) {
        this.isHot = isHot;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductSize> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(List<ProductSize> productSizes) {
        this.productSizes = productSizes;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", image=" + image + ", description=" + description + ", recipe=" + recipe + ", status=" + status + ", isHot=" + isHot + ", category=" + category + ", productSizes=" + productSizes + '}';
    }

}
