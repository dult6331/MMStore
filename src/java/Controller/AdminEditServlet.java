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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.*;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import model.*;

/**
 *
 * @author Alienware 15R3
 */
@WebServlet(name = "AdminEditServlet", urlPatterns = {"/AdminEdit"})
public class AdminEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int status = startsession(request, response);
        if(status!=1){
            response.sendRedirect("Login");
            return;
        }
        
        response.setContentType("text/html;charset=UTF-8");
        String id=(String) request.getParameter("pid");
        ProductDao  edao=new ProductDao();
        ManufacturerDAO   manufacturerDAO=new ManufacturerDAO();
        Product product=edao.getProduct(Integer.parseInt(id));
        ArrayList<Manufacturer> manufacturers=(ArrayList<Manufacturer>) manufacturerDAO.getManufacturers();
        request.setAttribute("product", product);
        request.setAttribute("manufacturers", manufacturers);
        request.getRequestDispatcher("./View/admin_add_edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String cancel=request.getParameter("cancel");
        if(null!=cancel){
            response.sendRedirect("AdminHome");
        }else{
            
            int id =Integer.parseInt(request.getParameter("ProductID"));
            String name =request.getParameter("ProductName");
            long unitsPrice =Long.parseLong(request.getParameter("UnitsPrice"));
            int categoryId =Integer.parseInt(request.getParameter("Category"));
            int unitsInStock =Integer.parseInt(request.getParameter("UnitslnStock"));
            String description =request.getParameter("Description");
            int manufacturer =Integer.parseInt(request.getParameter("Manufacturer"));
            boolean isContinues =request.getParameter("IsContinue").equals("Yes")?true:false;
            int star =Integer.parseInt(request.getParameter("Star"));
            Product product=new Product(id, name, unitsPrice, categoryId, unitsInStock, description, manufacturer, isContinues, star);

            ProductDao  productDao=new ProductDao();
            int numberRowUpdate=productDao.update(product);
            if(numberRowUpdate!=0){
                request.setAttribute("messUpdate", "Update success!!");
            }else{
                request.setAttribute("messUpdate", "Update fail!!");
            }
            
            request.getRequestDispatcher("./View/admin_add_edit.jsp").forward(request, response);
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
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
