package CampusController;

import dal.CampusDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Campus;

public class UpdateCampusServlet extends HttpServlet {

    private CampusDAO campusDAO;

    public UpdateCampusServlet() {
        this.campusDAO = new CampusDAO();  // Ensure your DAO has access to the database connection
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String currentPath = request.getRequestURI();
            request.setAttribute("currentPath", currentPath);
            int campusID = Integer.parseInt(request.getParameter("campusID"));
            Campus campus = campusDAO.getCampusByID(campusID);
            request.setAttribute("campus", campus);
            request.getRequestDispatcher("/updateCampus.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid campus ID.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String currentPath = request.getRequestURI();
            request.setAttribute("currentPath", currentPath);
            int campusID = Integer.parseInt(request.getParameter("campusID"));
            String campusName = request.getParameter("campusName");
            String address = request.getParameter("address");

            Campus campusToUpdate = new Campus(campusID, campusName, address);

            campusDAO.editCampus(campusToUpdate);

            response.sendRedirect("campus"); // Redirect to campus list page after successful update
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error processing input. Please ensure all fields are correctly filled.");
            request.getRequestDispatcher("/updateCampus.jsp").forward(request, response);
        }
    }
}
