/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package categoryController;

import model.Category;
import dal.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class UpdateCategoryServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateCategoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCategoryServlet at " + request.getContextPath() + "</h1>");
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
        String currentPath = request.getRequestURI();
        request.setAttribute("currentPath", currentPath);
        String categoryID = request.getParameter("categoryID");
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = categoryDAO.getCategoryById(categoryID);

        request.setAttribute("category", category);
        request.getRequestDispatcher("updateCategory.jsp").forward(request, response);
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
        String categoryID_raw = request.getParameter("categoryID");
        String categoryName = request.getParameter("categoryName");
        String detail = request.getParameter("detail");
        int categoryID;

        if (categoryName == null || categoryName.trim().isEmpty()
                || detail == null || detail.trim().isEmpty()) {
            request.setAttribute("error", "Category Name and Detail are required.");
            request.getRequestDispatcher("/category_update.jsp").forward(request, response);
            return;
        }

        if (categoryName.length() < 3 || categoryName.length() > 50
                || detail.length() < 10 || detail.length() > 500) {
            request.setAttribute("error", "Category Name must be between 3 and 50 characters long. Detail must be between 10 and 500 characters long.");
            request.getRequestDispatcher("/category_update.jsp").forward(request, response);
            return;
        }
        CategoryDAO categoryDAO = new CategoryDAO();
        try {
            categoryID = Integer.parseInt(categoryID_raw);
            categoryDAO.updateCategory(categoryID, categoryName, detail);
            response.sendRedirect(request.getContextPath() + "/category");
        } catch (Exception e) {
            e.printStackTrace();
//            response.sendRedirect(request.getContextPath() + "/error.jsp"); // Chuyển hướng đến trang lỗi
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
