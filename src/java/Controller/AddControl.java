/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.AccountDao;
import dao.ManufacturerDAO;
import dao.ProductDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Manufacturer;

/**
 *
 * @author trinh
 */
@WebServlet(name = "AddControl", urlPatterns = {"/addproduct"})
public class AddControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int status = startsession(request, response);
        if(status!=1){
            response.sendRedirect("Login");
            return;
        }
        
        ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
        ArrayList<Manufacturer> manufacturers = (ArrayList<Manufacturer>) manufacturerDAO.getManufacturers();
        request.setAttribute("manufacturers", manufacturers);
        request.getRequestDispatcher("./View/admin_add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String cancel=request.getParameter("cancel");
        if(null!=cancel){
            response.sendRedirect("AdminHome");
            return;
        }
        
        String productname = request.getParameter("ProductName");
        long unitprice = Long.parseLong(request.getParameter("UnitsPrice"));
        int categoryid = Integer.parseInt(request.getParameter("Category"));
        int unit = Integer.parseInt(request.getParameter("UnitslnStock"));
        String description = request.getParameter("Description");
        int manufacture = Integer.parseInt(request.getParameter("Manufacturer"));
        boolean isContinues =request.getParameter("IsContinue").equals("Yes")?true:false;
        int star =Integer.parseInt(request.getParameter("Star"));
        ProductDao pdao = new ProductDao();
        int result;
        result = pdao.insertProduct(productname, unitprice, categoryid, unit, description, manufacture, isContinues, star);
        if (result == 0) {
            request.setAttribute("mess", "Insert Unsucessfull");
        } else {
            request.setAttribute("mess", "Insert Sucessfull");
        }
        request.getRequestDispatcher("./View/admin_add.jsp").forward(request, response);
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
