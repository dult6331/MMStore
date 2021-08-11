/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Create;

import dao.AccountDao;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CreateServlet", urlPatterns = {"/SignUp"})
public class CreateServlet extends HttpServlet {

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
        request.setAttribute("displaylogin", "none");
        request.setAttribute("displaysignup", "block");
        if(startsession(request, response)){
            response.sendRedirect("Home");
        }else{
            request.getRequestDispatcher("./View/Login.jsp").forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user").trim();
        String pass = request.getParameter("pass");
        AccountDao dao = new AccountDao();
        Account temp = dao.getAccount(user);
        if (null == temp) {
            request.setAttribute("messRegister", "Create success!!!");
            dao.insert(new Account(0, user, pass, false));
            request.getSession().setAttribute("accountsession", new Account(0, user, pass, false));
            response.sendRedirect("Home");
        } else {
            request.setAttribute("euser", "Username already taken!");
            request.setAttribute("displaylogin", "none");
            request.setAttribute("displaysignup", "block");
            request.setAttribute("suser", user);
            request.setAttribute("spass", pass);
            request.setAttribute("srepass", pass);
            request.getRequestDispatcher("./View/Login.jsp").forward(request, response);
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
    private boolean startsession(HttpServletRequest request, HttpServletResponse response){
        if(request.getSession().getAttribute("accountsession")!=null){
            return true;
        }
        
        Cookie[] cookie = request.getCookies();
        String username="";
        String password="";
        //get exist cookie
        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("user")) {
                    username= c.getValue();
                } else if (c.getName().equals("pass")) {
                    password= c.getValue();
                }
            }

        }
        //get account from database
        AccountDao dao = new AccountDao();
        Account temp = dao.getAccount(username);
        
        if(temp!=null && password.equals(temp.getPassWord())){
            //start session
            request.getSession().setAttribute("accountsession", temp);
            if(temp.isIsAdministrator()){
                
            }
            return true;
        }else{
            //delete invalid cookie
            Cookie user = new Cookie("user", username);
            user.setMaxAge(0);
            Cookie pass = new Cookie("pass", password);
            pass.setMaxAge(0);
            response.addCookie(user);
            response.addCookie(pass);
        }
        return false;
    }
}
