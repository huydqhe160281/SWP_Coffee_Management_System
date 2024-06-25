package SizeController;

import dal.SizeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Size;

public class UpdateSizeServlet extends HttpServlet {

    private SizeDAO sizeDAO;

    public UpdateSizeServlet() {
        this.sizeDAO = new SizeDAO();  // Ensure your DAO has access to the database connection
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int sizeID = Integer.parseInt(request.getParameter("sizeID"));
            Size size = sizeDAO.getSizeByID(sizeID);
            request.setAttribute("size", size);
            request.getRequestDispatcher("/updateSize.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid size ID.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int sizeID = Integer.parseInt(request.getParameter("sizeID"));
            String type = request.getParameter("type");
            String description = request.getParameter("description");

            Size sizeToUpdate = new Size(sizeID, type, description);

            sizeDAO.editSize(sizeToUpdate);

            response.sendRedirect("size"); // Redirect to size list page after successful update
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error processing input. Please ensure all fields are correctly filled.");
            request.getRequestDispatcher("/updateSize.jsp").forward(request, response);
        } 
    }
}
