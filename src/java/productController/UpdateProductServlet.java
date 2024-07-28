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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
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
public class UpdateProductServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductServlet at " + request.getContextPath() + "</h1>");
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
        String productID = request.getParameter("productID");
        ProductDAO productDao = new ProductDAO();
        SizeDAO sizeDAO = new SizeDAO();

        Product product = productDao.getProductById(productID);
        List<Size> sList = sizeDAO.getAllSizes();
        CategoryDAO categoryDAO = new CategoryDAO();

        List<Category> cList = categoryDAO.getAllCategory();

        request.setAttribute("product", product);
        request.setAttribute("cList", cList);
        request.setAttribute("sList", sList);
        request.setAttribute("currentPath", currentPath);
        request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
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
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        String recipe = request.getParameter("recipe");
        boolean status = request.getParameter("status") != null;
        boolean isHot = request.getParameter("isHot") != null;
        int categoryId = Integer.parseInt(request.getParameter("category"));

        Part filePart = request.getPart("image");
        String fileName = null;
        String uploadPath = "D:/Huy_data/FPT/Ky8/SWP392/demo2/web/assets/images";

        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            try ( InputStream fileInputStream = filePart.getInputStream();  OutputStream fileOutputStream = new FileOutputStream(new File(uploadPath, fileName))) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error uploading image: " + e.getMessage());
                return;
            }
        }

        ProductDAO productDAO = new ProductDAO();
        Product currentProduct = productDAO.getProductById("" + productId);

        if (fileName == null) {
            fileName = currentProduct.getImage();
        }

        Category category = new Category(categoryId, null, null);
        Product product = new Product(productId, productName, fileName, description, recipe, status, isHot, category, new ArrayList<>());

        List<ProductSize> productSizes = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("price_")) {
                int sizeId = Integer.parseInt(paramName.substring(6));
                double price = Double.parseDouble(request.getParameter(paramName));
                Size size = new Size(sizeId, null, null);
                productSizes.add(new ProductSize(productId, sizeId, price, size));
            }
        }

        productDAO.updateProduct(product, productSizes);

        response.sendRedirect("/product");
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
