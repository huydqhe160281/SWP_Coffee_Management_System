package CampusController;

import dal.CampusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Campus;

/**
 * Servlet implementation class ViewCampusServlet
 */
public class ViewCampusServlet extends HttpServlet {

    private CampusDAO campusDAO;

    public ViewCampusServlet() {
        this.campusDAO = new CampusDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewCampusServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewCampusServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int campusId = Integer.parseInt(request.getParameter("campusID"));
        Campus campus = campusDAO.getCampusByID(campusId);
        if (campus != null) {
            request.setAttribute("campus", campus);
            request.getRequestDispatcher("/campusDetail.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Campus not found.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
