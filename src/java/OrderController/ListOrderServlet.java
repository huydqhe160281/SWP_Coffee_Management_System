/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package OrderController;

import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

/**
 *
 * @author Namqd
 */
public class ListOrderServlet extends HttpServlet {

    private final OrderDAO orderDAO;

    public ListOrderServlet() {
        this.orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentPath = request.getRequestURI();
        request.setAttribute("currentPath", currentPath);
        try {
            int indexPage = Integer.parseInt(request.getParameter("indexPage") == null ? "1" : request.getParameter("indexPage"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize"));

            List<Order> listOrders = orderDAO.getOrdersByPage(indexPage, pageSize);
            int totalOrders = orderDAO.getTotalOrders();
            int endPage = (int) Math.ceil((double) totalOrders / pageSize);

            request.setAttribute("listOrders", listOrders);
            request.setAttribute("indexPage", indexPage);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("endPage", endPage);

            request.getRequestDispatcher("listOrder.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

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
