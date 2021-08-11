/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.AccountDao;
import dao.ProductDao;
import dao.ProductImageDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Product;
import model.ProductImage;

/**
 *
 * @author Admin
 */
@WebServlet(name = "HomeController", urlPatterns = {"/Home"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int status = startsession(request, response);
        if(status ==1){
            response.sendRedirect("AdminHome");
            return;
        }
        ProductDao pdao = new ProductDao();
        ProductImageDao pidao = new ProductImageDao();

        //get product and product image
        List<Product> product = pdao.getProductsHome();
        ArrayList<ProductImage> productimg = new ArrayList<>();

        for (int i = 0; i < product.size(); i++) {
            productimg.add(pidao.getFirstProductImages(product.get(i).getProductId()));
        }
        request.setAttribute("proimgs", productimg);
        request.setAttribute("listP", product);
        request.getRequestDispatcher("/View/Home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
