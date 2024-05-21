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
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CategoryServlet extends HttpServlet {

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
            out.println("<title>Servlet CategoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryServlet at " + request.getContextPath() + "</h1>");
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
        CategoryDAO categoryDAO = new CategoryDAO();
        String indexPage_raw = request.getParameter("indexPage");
        String sizePage_raw = request.getParameter("sizePage");
        String sortType = request.getParameter("sortType");

        // Kiểm tra và gán giá trị mặc định nếu cần
        if (indexPage_raw == null) {
            indexPage_raw = "1";
        }
        if (sizePage_raw == null) {
            sizePage_raw = "2";
        }
        if (sortType == null) {
            sortType = "asc";
        }

        int indexPage = Integer.parseInt(indexPage_raw);
        int sizePage = Integer.parseInt(sizePage_raw);
        int count = categoryDAO.getTotalCategory();

        int endPage = count / sizePage;
        if (count % sizePage != 0) {
            endPage++;
        }

        // Lấy danh sách các Category đã được sắp xếp
        List<Category> cList = categoryDAO.getAllCategoryByPage(indexPage, sizePage, sortType);

        request.setAttribute("indexPage", indexPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("sizePage", sizePage);
        request.setAttribute("sortType", sortType); // Đặt thuộc tính sortType để sử dụng trong JSP
        request.setAttribute("cList", cList);
        request.getRequestDispatcher("category.jsp").forward(request, response);
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

    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        List<Category> list = dao.getAllCategory();
        for (Category i : list) {
            System.out.println(i.getCategoryName());
        }
    }
}
