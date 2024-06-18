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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Category;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author Namqd
 */
public class OrderServlet extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            List<Category> categories = orderDAO.getAllCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/order.jsp").forward(request, response);
        } else if (action.equals("getProducts")) {
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            List<Product> products = orderDAO.getProductsByCategory(categoryID);
            request.setAttribute("products", products);
            request.getRequestDispatcher("products.jsp").forward(request, response);
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> orders = orderDAO.getAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("order.jsp").forward(request, response);
    }

    private void viewOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        Order order = orderDAO.getOrderById(orderId);
        List<OrderDetail> orderDetails = orderDAO.getOrderDetailsByOrderId(orderId);
        request.setAttribute("order", order);
        request.setAttribute("orderDetails", orderDetails);
        request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        orderDAO.deleteOrder(orderId);
        response.sendRedirect("order?action=list");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                addOrder(request, response);
                break;
            default:
                listOrders(request, response);
                break;
        }
    }
    
    private void addOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        Date orderDate = parseDate(request.getParameter("orderDate"));

        Order order = new Order();
        order.setAccountID(accountId);
        order.setOrderDate(orderDate);

        orderDAO.addOrder(order);
        response.sendRedirect("order?action=list");
    }

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
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
