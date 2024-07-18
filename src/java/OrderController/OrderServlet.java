/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package OrderController;

import com.google.gson.Gson;
import dal.CategoryDAO;
import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Category;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.ProductSize;
import model.Size;
import model.StaffOrder;

/**
 *
 * @author Namqd
 */
public class OrderServlet extends HttpServlet {

    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;

    @Override
    public void init() {
        categoryDAO = new CategoryDAO();
        productDAO = new ProductDAO();
        orderDAO = new OrderDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryIDParam = request.getParameter("categoryID");
        String productIDParam = request.getParameter("productID");

        if (categoryIDParam != null && !categoryIDParam.isEmpty()) {
            try {
                int categoryID = Integer.parseInt(categoryIDParam);
                List<StaffOrder> products = orderDAO.getProductsByCategory(categoryID);
                List<Category> categories = categoryDAO.getAllCategory();
                request.setAttribute("categories", categories);
                request.setAttribute("products", products);
                request.setAttribute("selectedCategoryID", categoryID); // Lưu trữ categoryID đã chọn
                if (productIDParam != null && !productIDParam.isEmpty()) {
                    int productID = Integer.parseInt(productIDParam);
                    List<String> sizes = orderDAO.getSizesByProduct(productID);
                    request.setAttribute("sizes", sizes);
                    request.setAttribute("selectedProductID", productID); // Lưu trữ productID đã chọn
                }
                request.getRequestDispatcher("order.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "CategoryID and ProductID must be valid integers.");
            }
        } else {
            List<Category> categories = categoryDAO.getAllCategory();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("order.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<StaffOrder> orderList = (List<StaffOrder>) session.getAttribute("orderList");
        if (orderList == null) {
            orderList = new ArrayList<>();
        }

        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String sizeType = request.getParameter("sizeType");
        double unitPrice = Double.parseDouble(request.getParameter("unitPrice"));

        StaffOrder order = new StaffOrder();
        order.setProductID(productID);
        order.setProductName(productName);
        order.setType(sizeType);
        order.setPrice(unitPrice);

        orderList.add(order);
        session.setAttribute("orderList", orderList);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(orderList));
        out.flush();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
