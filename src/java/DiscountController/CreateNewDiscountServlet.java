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
import model.Discount;


public class CreateNewDiscountServlet extends HttpServlet {
   
    private final DiscountDAO discountDAO = new DiscountDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String currentPath = request.getRequestURI();
        request.setAttribute("currentPath", currentPath);
        request.getRequestDispatcher("/createNewDiscount.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String value = request.getParameter("value");
        String code = request.getParameter("code");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String maxDiscount = request.getParameter("maxDiscount");
        String quantity = request.getParameter("quantity");
        String status = request.getParameter("status");

        Discount discount = new Discount();
        discount.setValue(Integer.parseInt(value));
        discount.setCode(code);
        discount.setStartDate(java.sql.Date.valueOf(startDate));
        discount.setEndDate(java.sql.Date.valueOf(endDate));
        discount.setMaxDiscount(Double.parseDouble(maxDiscount));
        discount.setQuantity(Integer.parseInt(quantity));
        discount.setStatus(Boolean.parseBoolean(status));

        discountDAO.addDiscount(discount);
        response.sendRedirect("discount");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
