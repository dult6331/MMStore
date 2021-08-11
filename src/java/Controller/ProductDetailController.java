/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.AccountDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ProductDao;
import dao.ProductImageDao;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import model.Account;
import model.Product;
import model.ProductImage;
/**
 *
 * @author Hfyl
 */
@WebServlet(name = "ProductDetail", urlPatterns = {"/ProductDetail"})
public class ProductDetailController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int status = startsession(request, response);
        if(status ==1){
            response.sendRedirect("AdminHome");
            return;
        }
        ProductDao pdao= new ProductDao();
        ProductImageDao imgdao= new ProductImageDao();
        if(request.getParameter("productid").trim()==null ||!request.getParameter("productid").trim().matches("[\\d]+")){
            response.sendRedirect("Home");
            return;
        }
        
        int pid= Integer.parseInt(request.getParameter("productid").trim());
        Product product = pdao.getProduct(pid);
        ArrayList<ProductImage> productImage= imgdao.getProductImages(product.getProductId());
        
        if(!productImage.isEmpty()){
            request.setAttribute("productimg0", productImage.get(0).getImageUrl());
        }
        
        request.setAttribute("productname", product.getProductName());
        request.setAttribute("unitprice", String.format("%,d", product.getUnitsPrice()));
        request.setAttribute("description", product.getDescription());
        request.setAttribute("productimgs", productImage);
        request.setAttribute("productid", pid);
        request.setAttribute("iscontinue", product.isIsContinues());
        request.getRequestDispatcher("./View/ProductDetail.jsp").forward(request, response);
    }

    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    private int startsession(HttpServletRequest request, HttpServletResponse response) {
        if (request.getSession().getAttribute("accountsession") != null) {
            Account account = (Account) request.getSession().getAttribute("accountsession");
            return account.isIsAdministrator() ? 1:0;
        }
        Cookie[] cookie = request.getCookies();
        String username = "";
        String password = "";
        //get exist cookie
        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("user")) {
                    username = c.getValue();
                } else if (c.getName().equals("pass")) {
                    password = c.getValue();
                }
            }

        }
        //get account from database
        AccountDao dao = new AccountDao();
        Account temp = dao.getAccount(username);

        if (temp != null && password.equals(temp.getPassWord())) {
            //start session
            request.getSession().setAttribute("accountsession", temp);
            if (temp.isIsAdministrator()) {
                return 1;
            }
            return 0;
        } else {
            //delete invalid cookie
            Cookie user = new Cookie("user", username);
            user.setMaxAge(0);
            Cookie pass = new Cookie("pass", password);
            pass.setMaxAge(0);
            response.addCookie(user);
            response.addCookie(pass);
        }
        return -1;
    }
}
