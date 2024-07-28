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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String currentPath = request.getRequestURI();
        request.setAttribute("currentPath", currentPath);
        String fromDateStr = request.getParameter("fromDate");
        String toDateStr = request.getParameter("toDate");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate = sdf.parse(fromDateStr);
            toDate = sdf.parse(toDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<RevenueData> revenueData = orderDAO.getRevenueByDateRange(fromDate, toDate);
        double totalRevenue = revenueData.stream().mapToDouble(RevenueData::getRevenueAsDouble).sum();

        request.setAttribute("revenueData", revenueData);
        request.setAttribute("totalRevenue", decimalFormat.format(totalRevenue));

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
