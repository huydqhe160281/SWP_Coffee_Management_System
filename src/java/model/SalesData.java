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
public class SalesData {
    private Date orderDate;
    private int quantity;

    public SalesData(Date orderDate, int quantity) {
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "SalesData{" +
                "orderDate=" + orderDate +
                ", quantity=" + quantity +
                '}';
    }
}
