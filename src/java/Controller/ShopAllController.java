/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dao.AccountDao;
import dao.CategoryDao;
import dao.ManufacturerDAO;
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
import model.Category;
import model.Manufacturer;
import model.Product;
import model.ProductImage;

/**
 *
 * @author Hfyl
 */
@WebServlet(name = "ShopAllController", urlPatterns = {"/ShopAll"})
public class ShopAllController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int status = startsession(request, response);
        if(status ==1){
            response.sendRedirect("AdminHome");
            return;
        }
        //get sort by
        int sortby = 1;
        if (null != request.getParameter("sort")) {
            sortby = Integer.parseInt(request.getParameter("sort"));
        }
        switch (sortby) {
            case 1: {
                request.setAttribute("sortname", "selected");
                break;
            }
            case 2: {
                request.setAttribute("sortasc", "selected");
                break;
            }
            case 3: {
                request.setAttribute("sortdesc", "selected");
                break;
            }
            default: {
                request.setAttribute("sortnew", "selected");
                break;
            }
        }
        request.setAttribute("sortabc", sortby);
        //get selected trademark
        int trademarkIDchecked = 0;
        if (null != request.getParameter("trademark")) {
            trademarkIDchecked = Integer.parseInt(request.getParameter("trademark"));
        }
        //get price range
        int beginprice = 0;
        int endprice = 0;
        if (request.getParameter("beginp") != null && !"".equals(request.getParameter("beginp"))) {
            beginprice = Integer.parseInt(request.getParameter("beginp"));
            request.setAttribute("beginprice", beginprice);
        }
        if (request.getParameter("endp") != null && !"".equals(request.getParameter("endp"))) {
            endprice = Integer.parseInt(request.getParameter("endp"));
            request.setAttribute("endprice", endprice);
        }
        //get search by name
        String name = "";
        if(request.getParameter("search")!=null){
            if(Validate.Validate.checkName(request.getParameter("search").trim())){
                name=request.getParameter("search").trim();
            }
        }
        request.setAttribute("searchname", name);

        //create DAO class
        ProductDao pdao = new ProductDao();
        ProductImageDao pidao = new ProductImageDao();
        CategoryDao cdao = new CategoryDao();
        ManufacturerDAO mdao = new ManufacturerDAO();
        //get selected cartegory
        int carteck = 0;
        if (null != request.getParameter("carte")) {
            carteck = Integer.parseInt(request.getParameter("carte"));
        }
        request.setAttribute("carte", carteck);

        //load cartegory
        List<Category> carte = new ArrayList<>();
        Category all = new Category();
        all.setCategoryID(0);
        all.setCategoryName("All");
        carte.add(all);
        for (Category category : cdao.getCategories()) {
            carte.add(category);
        }
        request.setAttribute("Category", carte);
        //load trademark
        List<Manufacturer> trademarks = new ArrayList<Manufacturer>();
        trademarks.add(new Manufacturer(0, "All"));
        List<Manufacturer> temp = mdao.getManufacturers();
        for (Manufacturer manufacturer : temp) {
            trademarks.add(manufacturer);
        }
        request.setAttribute("trademarks", trademarks);

        //get and pagecount
        int pagecount = pdao.getPageCount(trademarkIDchecked, carteck, beginprice, endprice, name);
        request.setAttribute("numberPage", pagecount);
        //get and set curent page
        int page = 1;
        if (null != request.getParameter("page")) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        request.setAttribute("pageCurrent", page);
        //paging calculation
        int beginPage = page - 1;
        int endPage = page + 1;
        if (page < 3) {
            beginPage = 1;
            endPage = 3;
            if (endPage > pagecount) {
                endPage = pagecount;
            }
        } else {
            if (page > pagecount - 2) {
                endPage = pagecount;
                beginPage = pagecount - 2;
            }
        }

        //get product and product image
        List<Product> product = pdao.getProducts(page - 1, sortby, trademarkIDchecked, carteck, beginprice, endprice, name);
        ArrayList<ProductImage> productimg = new ArrayList<>();

        for (int i = 0; i < product.size(); i++) {
            productimg.add(pidao.getFirstProductImages(product.get(i).getProductId()));
        }

        //set atttribute
        request.setAttribute("checkedid", trademarkIDchecked);
        request.setAttribute("beginPage", beginPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("proimgs", productimg);
        request.setAttribute("lstzsize", product.size() - 1);
        request.setAttribute("listP", product);
        request.getRequestDispatcher("./View/ShopAll.jsp").forward(request, response);
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
