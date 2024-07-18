package model;

import java.util.Date;

public class RevenueData {

    private Date orderDate;
    private double revenue;

    public RevenueData(Date orderDate, double revenue) {
        this.orderDate = orderDate;
        this.revenue = revenue;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "RevenueData{"
                + "orderDate=" + orderDate
                + ", revenue=" + revenue
                + '}';
    }
}
