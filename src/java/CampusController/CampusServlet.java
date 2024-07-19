package CampusController;

import dal.CampusDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Campus;

/**
 *
 * Servlet for handling Campus operations
 */
public class CampusServlet extends HttpServlet {

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
        // You can add any additional processing here if needed
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
        CampusDAO campusDAO = new CampusDAO();
        List<Campus> campuses = campusDAO.getAllCampuses();
        request.setAttribute("campuses", campuses);
        request.getRequestDispatcher("campus.jsp").forward(request, response);
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
        // Lấy thông tin từ request để thêm mới Campus
        int campusID = Integer.parseInt(request.getParameter("campusID"));
        String campusName = request.getParameter("campusName");
        String address = request.getParameter("address");

        // Tạo đối tượng Campus từ thông tin lấy được
        Campus newCampus = new Campus(campusID, campusName, address);

        // Gọi đến CampusDAO để thêm mới Campus vào cơ sở dữ liệu
        CampusDAO campusDAO = new CampusDAO();
        campusDAO.addCampus(newCampus);

        // Sau khi thêm mới, chuyển hướng về trang danh sách Campus
        response.sendRedirect(request.getContextPath() + "/campus");
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
