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
public class CreateNewSupplierServlet extends HttpServlet {
   
    private final SupplierDAO supplierDAO = new SupplierDAO();

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String currentPath = request.getRequestURI();
        request.setAttribute("currentPath", currentPath);
        request.getRequestDispatcher("/createNewSupplier.jsp").forward(request, response);
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
        String supplierName = request.getParameter("supplierName");
        String contact = request.getParameter("contact");
        String address = request.getParameter("address");
        
        Supplier sp = new Supplier();
        sp.setSupplierName(supplierName);
        sp.setContact(contact);
        sp.setAddress(address);
        supplierDAO.addSupplier(sp);
        response.sendRedirect("supplier");
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
