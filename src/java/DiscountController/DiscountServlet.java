/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package DiscountController;

import dal.DiscountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Discount;

/**
 *
 * @author Namqd
 */
public class DiscountServlet extends HttpServlet {
   
    private final DiscountDAO discountDAO = new DiscountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                int discountID = Integer.parseInt(request.getParameter("id"));
                discountDAO.deleteDiscount(discountID);
                response.sendRedirect("discount");
                break;
            default:
                List<Discount> discounts = discountDAO.getAllDiscounts();
                request.setAttribute("discounts", discounts);
                request.getRequestDispatcher("/discount.jsp").forward(request, response);
                break;
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String value = request.getParameter("value");
        String code = request.getParameter("code");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String maxDiscount = request.getParameter("maxDiscount");
        String quantity = request.getParameter("quantity");

        Discount discount = new Discount();
        discount.setValue(Integer.parseInt(value));
        discount.setCode(code);
        discount.setStartDate(java.sql.Date.valueOf(startDate));
        discount.setEndDate(java.sql.Date.valueOf(endDate));
        discount.setMaxDiscount(Double.parseDouble(maxDiscount));
        discount.setQuantity(Integer.parseInt(quantity));

        discountDAO.addDiscount(discount);
        response.sendRedirect("discount");
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
