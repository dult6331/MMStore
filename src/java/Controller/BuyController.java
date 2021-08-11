/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Cookie.CookieClass;
import dao.OrderDao;
import dao.OrderDetailDao;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Order;
import model.ProductVirtual;
import model.OrderDetail;

/**
 *
 * @author Hfyl
 */
@WebServlet(name = "BuyController", urlPatterns = {"/buy"})
public class BuyController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("accountsession") == null) {
            response.sendRedirect("Login");
            return;
        }
        Account account = (Account) request.getSession().getAttribute("accountsession");
        if(account.isIsAdministrator()){
            response.sendRedirect("AdminHome");
            return;
        }
        
        ArrayList<ProductVirtual> cart = getAllidOfProductInCookie(request, response);
        if (cart == null || cart.isEmpty()) {
            request.setAttribute("mess", "Please insert at least 1 product to cart!");
        } else {
            OrderDao odao = new OrderDao();
            java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDate.now());
            odao.insert(new Order(0, account.getAcountId(), sqlDate));
            Order lastestorder = odao.getlastOrder();

            OrderDetailDao dao = new OrderDetailDao();
            for (ProductVirtual productVirtual : cart) {
                dao.insert(new OrderDetail(lastestorder.getOrderId(), productVirtual.getProductId(), productVirtual.getAmount()));
            }
            Cookie c = new Cookie("productID", "lmao");
            c.setMaxAge(0);
            response.addCookie(c);
            request.setAttribute("mess", "Buy successfully!");
        }

        request.getRequestDispatcher("./View/Cart.jsp").forward(request, response);
    }

    private ArrayList<ProductVirtual> getAllidOfProductInCookie(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<ProductVirtual> getAll = null;
        Cookie[] cookies = null;

        // Get an array of Cookies associated with this domain
        try {
            cookies = request.getCookies();
            if (cookies != null) {
                getAll = new ArrayList<>();
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().startsWith("productID")) {
                        Cookie cookie = cookies[i];
                        String value = cookie.getValue();
                        String[] values = value.split(CookieClass.getEncryption());
                        for (int j = 0; j < values.length; j += 2) {
                            int productID = Integer.parseInt(values[j].trim());
                            int amount = Integer.parseInt(values[j + 1].trim());
                            getAll.add(new ProductVirtual(productID, amount));
                        }
                        break;
                    }
                }
            } else {
                request.setAttribute("messListAddToCart", "No product in cart ");
            }
        } catch (Exception e) {

        }

        return getAll;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
