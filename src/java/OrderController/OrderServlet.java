package OrderController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import java.io.BufferedReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import model.Account;
import model.Category;
import model.Discount;
import model.Order;
import model.OrderDetail;
import model.StaffOrder;

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
        String action = request.getParameter("action");
        if ("getActiveDiscounts".equals(action)) {
            try {
                List<Discount> discounts = orderDAO.getActiveDiscounts();
                String json = new Gson().toJson(discounts);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } catch (SQLException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error: " + e.getMessage());
            }
            return;
        }
        String categoryIDParam = request.getParameter("categoryID");
        String productIDParam = request.getParameter("productID");
        String sizeParam = request.getParameter("size");

        if (productIDParam != null && sizeParam != null) {
            try {
                int productID = Integer.parseInt(productIDParam);
                double price = orderDAO.getPriceByProductAndSize(productID, sizeParam);
                Map<String, Double> priceMap = new HashMap<>();
                priceMap.put("price", price);

                String json = new Gson().toJson(priceMap);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.write(json);
                out.flush();
                return;
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ProductID must be a valid integer.");
            }
        }

        if (categoryIDParam != null && !categoryIDParam.isEmpty()) {
            try {
                int categoryID = Integer.parseInt(categoryIDParam);
                List<StaffOrder> products = orderDAO.getProductsByCategoryWithSizes(categoryID);
                List<Category> categories = categoryDAO.getAllCategory();
                request.setAttribute("categories", categories);
                request.setAttribute("products", products);
                request.setAttribute("selectedCategoryID", categoryID);

                HttpSession session = request.getSession();
                List<StaffOrder> orderList = (List<StaffOrder>) session.getAttribute("orderList");
                if (orderList != null) {
                    request.setAttribute("orderList", orderList);
                }

                request.getRequestDispatcher("order.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "CategoryID must be a valid integer.");
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
        String action = request.getParameter("action");
        if ("saveOrder".equals(action)) {
            handleSaveOrder(request, response);
        }
    }

    private void handleSaveOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: No active session.");
            return;
        }

        Account account = (Account) session.getAttribute("account");
        int accountId = account.getAccountID();

        try {
            String orderDetailsJson = request.getParameter("orderDetails");
            String discountInfo = request.getParameter("discountDetails");

            OrderDetail[] orderDetails = new Gson().fromJson(orderDetailsJson, OrderDetail[].class);

            Order order = new Order();
            order.setAccountID(accountId);
            order.setOrderDate(new Date());
            order.setStatus(true);
            order.setCancelled(false);

            int orderId = orderDAO.saveOrder(order);

            Discount discount = null;
            if (!"None".equals(discountInfo)) {
                String discountCode = discountInfo.split(" ")[1];
                discount = orderDAO.getDiscountByCode(discountCode);
            }

            for (OrderDetail detail : orderDetails) {
                detail.setOrderID(orderId);
                if (discount != null) {
                    detail.setDiscountID(discount.getDiscountID());
                } else {
                    detail.setDiscountID(0); // Or a default value if no Discount
                }
                orderDAO.saveOrderDetail(detail);
            }

            response.sendRedirect("checkout.jsp");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error processing your order: " + e.getMessage());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error processing your order: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
