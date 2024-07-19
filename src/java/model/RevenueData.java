package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RevenueData {

    private Date orderDate;
    private double revenue;

    // Định dạng ngày
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    // Định dạng số tiền
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public RevenueData(Date orderDate, double revenue) {
        this.orderDate = orderDate;
        this.revenue = revenue;
    }

    // Định dạng và trả về ngày theo định dạng "dd-MM-yyyy"
    public String getOrderDate() {
        return dateFormat.format(orderDate);
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    // Định dạng và trả về số tiền theo định dạng có dấu phẩy ngăn cách hàng nghìn
    public String getRevenue() {
        return decimalFormat.format(revenue);
    }

    // Trả về doanh thu dưới dạng double
    public double getRevenueAsDouble() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "RevenueData{"
                + "orderDate=" + getOrderDate()
                + ", revenue=" + getRevenue()
                + '}';
    }
}
