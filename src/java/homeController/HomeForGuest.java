/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package homeController;

import com.google.gson.Gson;
import dal.DiscountDAO;
import dal.GeneralDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import model.Discount;
import model.General;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class HomeForGuest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeForGuest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeForGuest at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DiscountDAO discountDAO = new DiscountDAO();
        ProductDAO productDAO = new ProductDAO();
        GeneralDAO generalDao = new GeneralDAO();

        General general = generalDao.getLastGeneral();
        Gson gson = new Gson();
        String generalJson = gson.toJson(general);

        List<Product> hotProducts = productDAO.getHotProducts();
        List<Discount> discounts = discountDAO.getAllDiscounts();
        Date currentDate = new Date();
        List<Discount> validDiscounts = discounts.stream()
                .filter(discount -> discount.getEndDate().after(currentDate))
                .collect(Collectors.toList());

        request.setAttribute("generalJson", generalJson);
        request.setAttribute("discounts", validDiscounts);
        request.setAttribute("hotProducts", hotProducts);

        request.getRequestDispatcher("landing-page.jsp").forward(request, response);
    }

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
        processRequest(request, response);
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
