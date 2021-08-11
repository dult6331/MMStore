/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Login;

import dao.AccountDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Alienware 15R3
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int status = startsession(request, response);
        if (status == -1) {
            request.getRequestDispatcher("/View/Login.jsp").forward(request, response);
        }
        if (status == 0) {
            response.sendRedirect("Home");
            return;
        }
        
        if (status == 1) {
            response.sendRedirect("AdminHome");
            return;
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user").trim();
        String pass = request.getParameter("pass").trim();

        Cookie[] cookie = request.getCookies();
        Cookie us = null;
        Cookie pa = null;
        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("user")) {

                    c.setMaxAge(0);
                    response.addCookie(c);
                } else if (c.getName().equals("pass")) {

                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }

        }
        if ("on".equals(request.getParameter("remember"))) {
            us = new Cookie("user", user);
            us.setMaxAge(24 * 60 * 60);
            pa = new Cookie("pass", pass);
            pa.setMaxAge(24 * 60 * 60);
            response.addCookie(us);
            response.addCookie(pa);
        }

        AccountDao dao = new AccountDao();
        Account temp = dao.getAccount(user);
        if (null != temp) {
            if (pass.equals(temp.getPassWord())) {
                Account accountsession = new Account();
                accountsession.setUserName(user);
                accountsession.setPassWord(pass);
                request.getSession().setAttribute("accountsession", temp);
                if (temp.isIsAdministrator()) {
                    response.sendRedirect("AdminHome");
                } else {
                    response.sendRedirect("Home");
                }

            } else {
                request.setAttribute("user", user);
                request.setAttribute("pass", pass);
                request.setAttribute("mess", "Password invalid");
                request.getRequestDispatcher("/View/Login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("user", user);
            request.setAttribute("pass", pass);
            request.setAttribute("mess", "Username invalid");
            request.getRequestDispatcher("/View/Login.jsp").forward(request, response);
        }

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
