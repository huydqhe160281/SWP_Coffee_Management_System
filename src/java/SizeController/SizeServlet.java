/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package SizeController;

import dal.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Size;

/**
 *
 * @author Dinh Hai
 */
public class SizeServlet extends HttpServlet {

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
        try {
            // TODO output your page here. You may use following sample code. 
            // PrintWriter out = response.getWriter();
            // out.println("<!DOCTYPE html>");
            // out.println("<html>");
            // out.println("<head>");
            // out.println("<title>Servlet SizeServlet</title>");            
            // out.println("</head>");
            // out.println("<body>");
            // out.println("<h1>Servlet SizeServlet at " + request.getContextPath() + "</h1>");
            // out.println("</body>");
            // out.println("</html>");
            
        } catch (Exception e) {
            e.printStackTrace();
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
        SizeDAO sizeDAO = new SizeDAO();
        List<Size> sizes = sizeDAO.getAllSizes();
        request.setAttribute("sizes", sizes);
        request.getRequestDispatcher("size.jsp").forward(request, response);
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
        // Lấy thông tin từ request để thêm mới Size
        int sizeID = Integer.parseInt(request.getParameter("sizeID"));
        String type = request.getParameter("type");
        String description = request.getParameter("description");

        // Tạo đối tượng Size từ thông tin lấy được
        Size newSize = new Size(sizeID, type, description);

        // Gọi đến SizeDAO để thêm mới Size vào cơ sở dữ liệu
        SizeDAO sizeDAO = new SizeDAO();
        sizeDAO.addSize(newSize);

        // Sau khi thêm mới, chuyển hướng về trang danh sách Size
        response.sendRedirect(request.getContextPath() + "/sizes");
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
