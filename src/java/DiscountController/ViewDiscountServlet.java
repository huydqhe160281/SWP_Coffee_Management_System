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

/**
 *
 * @author Namqd
 */
public class ViewDiscountServlet extends HttpServlet {
   
    private DiscountDAO discountDAO;

    public ViewDiscountServlet() {
        this.discountDAO = new DiscountDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int discountID = Integer.parseInt(request.getParameter("discountID"));
        Discount discount = discountDAO.getDiscountByID(discountID);
        if (discount != null) {
            request.setAttribute("discount", discount);
            request.getRequestDispatcher("/discountDetail.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Discount not found.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
