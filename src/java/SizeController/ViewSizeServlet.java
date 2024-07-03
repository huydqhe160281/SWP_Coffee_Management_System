package SizeController;

import dal.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Size;

/**
 * Servlet implementation class ViewSizeServlet
 */
public class ViewSizeServlet extends HttpServlet {

    private SizeDAO sizeDAO;

    public ViewSizeServlet() {
        this.sizeDAO = new SizeDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewSizeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewSizeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int sizeId = Integer.parseInt(request.getParameter("sizeID"));
        Size size = sizeDAO.getSizeByID(sizeId);
        if (size != null) {
            request.setAttribute("size", size);
            request.getRequestDispatcher("/sizeDetail.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Size not found.");
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
