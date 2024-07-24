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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import model.Category;
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
        HttpSession session = request.getSession();
        List<StaffOrder> orderList = (List<StaffOrder>) session.getAttribute("orderList");
        if (orderList == null) {
            orderList = new ArrayList<>();
        }

        String orderListJson = request.getParameter("orderList");
        Gson gson = new Gson();
        StaffOrder[] orders = gson.fromJson(orderListJson, StaffOrder[].class);

        for (StaffOrder order : orders) {
            orderList.add(order);
        }

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
