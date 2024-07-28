/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package SupplierController;

import dal.SupplierDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Supplier;

/**
 *
 * @author Namqd
 */
public class UpdateSupplierServlet extends HttpServlet {
   
    private final SupplierDAO supplierDAO;
    
    public UpdateSupplierServlet() {
        this.supplierDAO = new SupplierDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String currentPath = request.getRequestURI();
        request.setAttribute("currentPath", currentPath);
        try {
            int supplierID = Integer.parseInt(request.getParameter("supplierID"));
            Supplier supplier = supplierDAO.getSupplierById(supplierID);
            request.setAttribute("supplier", supplier);
            request.getRequestDispatcher("/updateSupplier.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid supplier ID.");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            int supplierID = Integer.parseInt(request.getParameter("supplierID"));
            String supplierName = request.getParameter("supplierName");
            String contact = request.getParameter("contact");
            String address = request.getParameter("address");

            Supplier supplier = new Supplier(supplierID, supplierName, contact, address);
            supplierDAO.updateSupplier(supplier);
            response.sendRedirect("supplier"); // Redirect to the supplier list or confirmation page

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error processing input. Please ensure all fields are correctly filled.");
            request.getRequestDispatcher("/updateSupplier.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "System error: " + e.getMessage());
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Updates a supplier entry based on user input";
    }// </editor-fold>

}
