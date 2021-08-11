/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 *
 * @author Hfyl
 */
@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session!=null){
            session.invalidate();
        }
        Cookie[] cookie = request.getCookies();
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
        response.sendRedirect("Home");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
