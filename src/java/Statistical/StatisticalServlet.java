/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Statistical;

import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.RevenueData;
import model.SalesData;

/**
 *
 * @author Namqd
 */
public class StatisticalServlet extends HttpServlet {
   
    private OrderDAO orderDAO = new OrderDAO();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        List<RevenueData> revenueData = orderDAO.getRevenueByDay();
        List<SalesData> salesData = orderDAO.getSalesByDay();

        System.out.println("Revenue Data: " + revenueData);
        System.out.println("Sales Data: " + salesData);
        request.setAttribute("revenueData", revenueData);
        request.setAttribute("salesData", salesData);

        request.getRequestDispatcher("/chart-morris.jsp").forward(request, response);
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
