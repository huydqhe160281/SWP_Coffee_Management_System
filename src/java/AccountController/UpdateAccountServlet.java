package AccountController;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

public class UpdateAccountServlet extends HttpServlet {

    private AccountDAO accountDAO;

    public UpdateAccountServlet() {
        this.accountDAO = new AccountDAO();  // Ensure your DAO has access to the database connection
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String currentPath = request.getRequestURI();
            request.setAttribute("currentPath", currentPath);
            int accountID = Integer.parseInt(request.getParameter("accountID"));
            Account account = accountDAO.getAccountByID(accountID);
            request.setAttribute("account", account);
            request.getRequestDispatcher("/updateAccount.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid account ID.");
            request.getRequestDispatcher("/updateAccount.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String currentPath = request.getRequestURI();
            request.setAttribute("currentPath", currentPath);
            int accountID = Integer.parseInt(request.getParameter("accountID"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            int roleID = Integer.parseInt(request.getParameter("roleID"));
            int campusID = Integer.parseInt(request.getParameter("campusID"));

            Account accountToUpdate = new Account(accountID, username, password, name, phone, email, address, status, roleID, campusID);

            accountDAO.editAccount(accountToUpdate);

            response.sendRedirect("account"); // Redirect to account list page after successful update
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error processing input. Please ensure all fields are correctly filled.");
            request.getRequestDispatcher("/updateAccount.jsp").forward(request, response);
        }
    }
}
