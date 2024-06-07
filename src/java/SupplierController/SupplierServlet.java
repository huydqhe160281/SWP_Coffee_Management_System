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
import java.util.List;
import model.Supplier;

/**
 *
 * @author Namqd
 */
public class SupplierServlet extends HttpServlet {
   
    private final SupplierDAO supplierDAO = new SupplierDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String currentPath = request.getRequestURI();
        request.setAttribute("currentPath", currentPath);
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "delete":
                int supplierID = Integer.parseInt(request.getParameter("id"));
//                supplierID.deleteSupplier(supplierID);
                response.sendRedirect("discount");
                break;
            default:
                List<Supplier> suppliers = supplierDAO.getAllSuppliers();
                request.setAttribute("suppliers", suppliers);
                request.getRequestDispatcher("/supplier.jsp").forward(request, response);
                break;
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
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
