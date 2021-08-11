/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.AccountDao;
import dao.OrderDao;
import dao.OrderDetailDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Order;

/**
 *
 * @author Hfyl
 */
@WebServlet(name = "UserOrderController", urlPatterns = {"/UserOrder"})
public class UserOrderController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int status = startsession(request, response);
        if(status ==1){
            response.sendRedirect("AdminHome");
            return;
        }
        if(status==-1){
            response.sendRedirect("Login");
            return;
        }
        Account account = (Account) request.getSession().getAttribute("accountsession");
        OrderDao ordao = new OrderDao();
        OrderDetailDao oddao = new OrderDetailDao();
        ArrayList<Order> orlst = (ArrayList<Order>) ordao.getOrders(account.getAcountId());
        int orderId;
        for (int i = 0; i < orlst.size(); i++) {
            orderId =orlst.get(i).getOrderId();
            orlst.get(i).setLst(oddao.getOdetail(orderId));
        }
        request.setAttribute("orders", orlst);
        request.setAttribute("endods", orlst.size()-1);
        request.getRequestDispatcher("/View/userorder.jsp").forward(request, response);
    }

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
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
