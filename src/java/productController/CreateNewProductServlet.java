/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package productController;

import dal.CategoryDAO;
import dal.ProductDAO;
import dal.SizeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;
import model.ProductSize;
import model.Size;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class CreateNewProductServlet extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "images";

    /**
     *
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
            out.println("<title>Servlet CreateNewProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateNewProductServlet at " + request.getContextPath() + "</h1>");
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

        CategoryDAO categoryDAO = new CategoryDAO();
        SizeDAO sizeDAO = new SizeDAO();

        List<Category> cList = categoryDAO.getAllCategory();
        List<Size> sList = sizeDAO.getAllSizes();

        request.setAttribute("sList", sList);
        request.setAttribute("cList", cList);
        request.getRequestDispatcher("createNewProduct.jsp").forward(request, response);
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
        // Retrieve form data
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        String recipe = request.getParameter("recipe");
        boolean status = request.getParameter("status") != null;
        boolean isHot = request.getParameter("isHot") != null;
        int categoryId = Integer.parseInt(request.getParameter("category"));

        // Handle file upload
        Part filePart = request.getPart("image");
        String fileName = extractFileName(filePart);
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        // Create category object
        Category category = new Category(categoryId, null, null); // Assuming you have a way to fetch the full category details

        // Create product object
        Product product = new Product(0, productName, fileName, description, recipe, status, isHot, category, new ArrayList<>());

        // Create product sizes
        List<ProductSize> productSizes = new ArrayList<>();
        String[] sizeIds = request.getParameterValues("sizeId");
        String[] prices = request.getParameterValues("price");

        if (sizeIds != null && prices != null) {
            for (int i = 0; i < sizeIds.length; i++) {
                int sizeId = Integer.parseInt(sizeIds[i]);
                double price = Double.parseDouble(prices[i]);
                Size size = new Size(sizeId, null, null); // Assuming you have a way to fetch the full size details
                productSizes.add(new ProductSize(0, sizeId, price, size));
            }
        }

        // Save product and product sizes to database
        ProductDAO productDAO = new ProductDAO();
        productDAO.createNewProduct(product, productSizes);

        // Redirect to a success page or display a success message
        response.sendRedirect("/product");
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
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
