/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package generalController;

import dal.GeneralDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import model.General;

/**
 *
 * @author ADMIN
 */
@MultipartConfig
public class UpdateGeneralServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateGeneralServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateGeneralServlet at " + request.getContextPath() + "</h1>");
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
        GeneralDAO generalDao = new GeneralDAO();
        try {
            General general = generalDao.getLastGeneral();
            request.setAttribute("generalInfo", general);
            request.getRequestDispatcher("updateGeneral.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
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
        int generalID = Integer.parseInt(request.getParameter("generalID"));
        String email = request.getParameter("email");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String nameApp = request.getParameter("nameApp");
        String address = request.getParameter("address");

        Part logoPart = request.getPart("logoImage");
        Part fivicoPart = request.getPart("fivicoImage");

        String uploadPath = "D:/Huy_data/FPT/Ky8/SWP392/demo2/web/assets/images";

        try {
            // Save new logo image
            String logoFilename = Paths.get(logoPart.getSubmittedFileName()).getFileName().toString();
            try ( OutputStream logoOs = new FileOutputStream(new File(uploadPath, logoFilename));  InputStream logoIs = logoPart.getInputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = logoIs.read(buffer)) != -1) {
                    logoOs.write(buffer, 0, bytesRead);
                }

                // Save new favicon image
                String fivicoFilename = Paths.get(fivicoPart.getSubmittedFileName()).getFileName().toString();
                try ( OutputStream fivicoOs = new FileOutputStream(new File(uploadPath, fivicoFilename));  InputStream fivicoIs = fivicoPart.getInputStream()) {

                    buffer = new byte[1024];
                    while ((bytesRead = fivicoIs.read(buffer)) != -1) {
                        fivicoOs.write(buffer, 0, bytesRead);
                    }

                    // Update the General record in the database with the uploaded file information
                    GeneralDAO generalDao = new GeneralDAO();
                    General currentGeneral = generalDao.getLastGeneral(); // Get current General info

                    String currentLogoName = currentGeneral.getLogoImage(); // Get current logo image name
                    String currentFivicoName = currentGeneral.getFivicoImage(); // Get current favicon image name

                    // Update General object with new image filenames
                    General general = new General(generalID, email, phone, nameApp, address, logoFilename, fivicoFilename);
                    boolean isUpdated = generalDao.updateGeneral(general, generalID);

                    if (isUpdated) {
                        // Delete old logo image file if it's different from the new one
                        if (currentLogoName != null && !currentLogoName.equals(logoFilename)) {
                            File currentLogoFile = new File(uploadPath + File.separator + currentLogoName);
                            if (currentLogoFile.exists()) {
                                currentLogoFile.delete();
                            }
                        }

                        // Delete old favicon image file if it's different from the new one
                        if (currentFivicoName != null && !currentFivicoName.equals(fivicoFilename)) {
                            File currentFivicoFile = new File(uploadPath + File.separator + currentFivicoName);
                            if (currentFivicoFile.exists()) {
                                currentFivicoFile.delete();
                            }
                        }

                        // Forward to general.jsp with updated generalInfo
                        request.setAttribute("generalInfo", generalDao.getLastGeneral());
                        request.getRequestDispatcher("general.jsp").forward(request, response);
                    } else {
                        // Handle update failure
                        response.getWriter().println("Update failed");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().println("Upload favicon image failed: " + e.getMessage());
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Upload logo image failed: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
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
