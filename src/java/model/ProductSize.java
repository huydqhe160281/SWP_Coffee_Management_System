/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class ProductSize {

    private int productID;
    private int sizeID;
    private double price;
    private Size size;

    public ProductSize() {
    }

    public ProductSize(int productID, int sizeID, double price, Size size) {
        this.productID = productID;
        this.sizeID = sizeID;
        this.price = price;
        this.size = size;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ProductSize{" + "productID=" + productID + ", sizeID=" + sizeID + ", price=" + price + ", size=" + size + '}';
    }
}