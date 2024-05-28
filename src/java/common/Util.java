/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class Util {

    private static final DecimalFormat df = new DecimalFormat("#,###");

    public static String formatCurrency(double amount) {
        return df.format(amount) + " VNĐ";
    }

    public static String formatCurrency(float amount) {
        return df.format(amount) + " VNĐ";
    }
}
