/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.Account;

/**
 *
 * @author Dinh Hai
 */
public class UpdateAccountServlet extends HttpServlet {

    private AccountDAO accountDAO;

    public UpdateAccountServlet() {
        this.accountDAO = new AccountDAO();  // Ensure your DAO has access to the database connection
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int accountID = Integer.parseInt(request.getParameter("accountID"));
            Account account = accountDAO.getAccountByID(accountID);
            request.setAttribute("account", account);
            request.getRequestDispatcher("/updateAccount.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid account ID.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
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

            // Call DAO method to update account
            accountDAO.editAccount(accountToUpdate);

            // Redirect to a success page or list page
            response.sendRedirect("accountList"); // Change to appropriate URL
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Error processing input. Please ensure all fields are correctly filled.");
            request.getRequestDispatcher("/updateAccount.jsp").forward(request, response);
        } 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
