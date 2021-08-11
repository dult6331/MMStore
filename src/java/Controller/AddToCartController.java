/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Cookie.CookieClass;
import dao.ProductDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author Hfyl
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Home");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("pIdAdd");

        Cookie cookie = null;
        StringBuilder sb = new StringBuilder("");
        Cookie[] cookies = request.getCookies();
        for (Cookie cooky : cookies) {
            if (cooky.getName().equals("productID")) {
                cookie = cooky;
                break;
            }
        }
        if (cookie == null) {
            sb.append(id).append(CookieClass.getEncryption()).append(1).append(CookieClass.getEncryption());
        } else {
            sb.append(getAllidOfProductInCookie(cookie.getValue(),id));
        }

        Cookie product1 = new Cookie("productID", sb.toString());
        product1.setMaxAge(180);
        response.addCookie(product1);
        response.sendRedirect("ProductDetail?productid="+id);
    }

    private String getAllidOfProductInCookie(String cookie, String id)
            throws ServletException, IOException {
        StringBuilder sb = new StringBuilder("");
        String[] values = cookie.split(CookieClass.getEncryption());
        boolean check = false;
        for (int j = 0; j < values.length; j += 2) {
            String productID = values[j].trim();
            int amount = Integer.parseInt(values[j + 1].trim());
            if (productID.equalsIgnoreCase(id)) {
                ProductDao productDao= new ProductDao();
                Product product = productDao.getProduct(Integer.parseInt(id));
                amount = Math.min(amount + 1, product.getUnitsInStock());
                check =true;
            }
            sb.append(productID).append(CookieClass.getEncryption()).append(amount).append(CookieClass.getEncryption());
        }
        if(!check){
            sb.append(id).append(CookieClass.getEncryption()).append(1).append(CookieClass.getEncryption());
        }
        return sb.toString();
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
