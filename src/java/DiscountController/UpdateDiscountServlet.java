package DiscountController;

import dal.DiscountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import model.Discount;

public class UpdateDiscountServlet extends HttpServlet {
   
    private DiscountDAO discountDAO;

    public UpdateDiscountServlet() {
        this.discountDAO = new DiscountDAO();  
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String currentPath = request.getRequestURI();
        request.setAttribute("currentPath", currentPath);
        try {
            int discountID = Integer.parseInt(request.getParameter("discountID"));
            Discount discount = discountDAO.getDiscountByID(discountID);
            if (discount != null) {
                request.setAttribute("discount", discount);
                request.getRequestDispatcher("/updateDiscount.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Discount information is not available. Please check the Discount ID.");
                request.getRequestDispatcher("/updateDiscount.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid discount ID.");
            request.getRequestDispatcher("/updateDiscount.jsp").forward(request, response);
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
        int discountID = Integer.parseInt(request.getParameter("discountID"));
        String value = request.getParameter("value");
        String code = request.getParameter("code");
        Date startDate = java.sql.Date.valueOf(request.getParameter("startDate"));
        Date endDate = java.sql.Date.valueOf(request.getParameter("endDate"));
        double maxDiscount = Double.parseDouble(request.getParameter("maxDiscount"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        // Check for duplicate code
        if (discountDAO.isCodeExist(code, discountID)) {
            request.setAttribute("error", "The discount code '" + code + "' is already in use. Please use a different code.");
            request.setAttribute("discount", new Discount(discountID, Integer.parseInt(value), code, startDate, endDate, maxDiscount, quantity, status));
            request.getRequestDispatcher("/updateDiscount.jsp").forward(request, response);
        } else {
            Discount discount = new Discount(discountID, Integer.parseInt(value), code, startDate, endDate, maxDiscount, quantity, status);
            discountDAO.updateDiscount(discount);
            response.sendRedirect("discount"); // Redirect to the discount list or confirmation page
        }
    } catch (NumberFormatException e) {
        request.setAttribute("error", "Error processing input. Please ensure all fields are correctly filled.");
        request.getRequestDispatcher("/updateDiscount.jsp").forward(request, response);
    } catch (Exception e) {
        request.setAttribute("error", "System error: " + e.getMessage());
        request.getRequestDispatcher("/updateDiscount.jsp").forward(request, response);
    }
    }

    @Override
    public String getServletInfo() {
        return "Updates a discount entry based on user input";
    }
}
