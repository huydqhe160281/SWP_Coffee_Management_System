package AccountController;

import model.Account;
import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Dinh Hai
 */
public class SearchAccountServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchAccountServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentPath = request.getRequestURI();
        request.setAttribute("currentPath", currentPath);
        response.setContentType("text/html;charset=UTF-8");

        String text_search = request.getParameter("name");
        if (text_search == null) {
            text_search = ""; // Default to empty string if null
        }
        text_search = text_search.trim(); // Remove leading/trailing whitespace

        AccountDAO accountDAO = new AccountDAO();
        List<Account> accounts = accountDAO.searchAccountsByName(text_search);

        request.setAttribute("name", text_search);
        request.setAttribute("accounts", accounts);

        request.getRequestDispatcher("account.jsp").forward(request, response);
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
