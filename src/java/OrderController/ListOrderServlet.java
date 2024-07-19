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
            String indexPage_raw = request.getParameter("indexPage");
            String sizePage_raw = request.getParameter("sizePage");
            String sortType = request.getParameter("sortType");

            if (indexPage_raw == null) {
                indexPage_raw = "1";
            }
            if (sizePage_raw == null) {
                sizePage_raw = "5";
            }
            if (sortType == null) {
                sortType = "asc";
            }

            int indexPage = Integer.parseInt(indexPage_raw);
            int sizePage = Integer.parseInt(sizePage_raw);
            int count = orderDAO.getTotalOrders();

            int endPage = count / sizePage;
            if (count % sizePage != 0) {
                endPage++;
            }
            List<Order> listOrders = orderDAO.getOrdersByPage(indexPage, sizePage);

            request.setAttribute("listOrders", listOrders);
            request.setAttribute("indexPage", indexPage);
            request.setAttribute("pageSize", sizePage);
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
