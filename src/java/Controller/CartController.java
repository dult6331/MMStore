/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Cookie.CookieClass;
import dao.AccountDao;
import dao.ProductDao;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Product;
import model.ProductVirtual;

/**
 *
 * @author Hfyl
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/Cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int status = startsession(request, response);
        if(status ==1){
            response.sendRedirect("AdminHome");
            return;
        }
        ArrayList<ProductVirtual> productVirtuals = getAllidOfProductInCookie(request, response);
        ArrayList<Product> products = getProducts(productVirtuals);
        BigDecimal totalAmount = getTotalAmount(products, productVirtuals);
        BigDecimal ship = BigDecimal.ZERO;
        BigDecimal vat = getVat(totalAmount);
        BigDecimal totalPayment = getTotalPayment(totalAmount, ship, vat);

        // create new cookie
        if (productVirtuals != null) {
            if (!productVirtuals.isEmpty()) {
                CookieClass cookieClass = new CookieClass();
                Cookie cookie = cookieClass.getValueInCookie("productID", productVirtuals, 180, true);
                response.addCookie(cookie);
            }

        }

        //set attribute and send to jsp
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("ship", ship);
        request.setAttribute("vat", vat);
        request.setAttribute("totalPayment", totalPayment);
        request.setAttribute("products", products);
        request.setAttribute("productVirtuals", productVirtuals);
        request.getRequestDispatcher("./View/Cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sub = request.getParameter("-");
        String add = request.getParameter("+");
        String idDelete = request.getParameter("idDelete");
        ArrayList<ProductVirtual> productVirtuals = null;
        if (null != sub) {
            productVirtuals = getAllidOfProductInCookie(request, response, sub, false);
        }
        if (null != add) {
            productVirtuals = getAllidOfProductInCookie(request, response, add, true);
        }
        if (null != idDelete) {
            productVirtuals = getAllidOfProductInCookie(request, response, idDelete);
        }

        // create products 
        ArrayList<Product> products = getProducts(productVirtuals);
        BigDecimal totalAmount = getTotalAmount(products, productVirtuals);
        BigDecimal ship = BigDecimal.ZERO;
        BigDecimal vat = getVat(totalAmount);
        BigDecimal totalPayment = getTotalPayment(totalAmount, ship, vat);

        // create new cookie 
        CookieClass cookieClass = new CookieClass();
        Cookie cookie = null;
        if (productVirtuals.isEmpty() || productVirtuals == null) {
            cookie = cookieClass.getValueInCookie("productID", productVirtuals, 0, true);
        } else {
            cookie = cookieClass.getValueInCookie("productID", productVirtuals, 180, true);
        }

        response.addCookie(cookie);

        //set attribute and send to jsp
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("ship", ship);
        request.setAttribute("vat", vat);
        request.setAttribute("totalPayment", totalPayment);
        request.setAttribute("products", products);
        request.setAttribute("productVirtuals", productVirtuals);
        request.getRequestDispatcher("./View/Cart.jsp").forward(request, response);
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

    protected ArrayList<ProductVirtual> getAllidOfProductInCookie(HttpServletRequest request, HttpServletResponse response)
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

    protected ArrayList<ProductVirtual> getAllidOfProductInCookie(HttpServletRequest request, HttpServletResponse response, String id)
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
                            if (productID == Integer.parseInt(id)) {
                                continue;
                            }
                            getAll.add(new ProductVirtual(productID, amount));
                        }
                        cookie.setMaxAge(0);
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

    private ArrayList<Product> getProducts(ArrayList<ProductVirtual> productVirtuals) {
        ArrayList<Product> products = new ArrayList<>();
        if (productVirtuals != null) {
            ProductDao productDao = new ProductDao();
            for (ProductVirtual ii : productVirtuals) {
                Product product = productDao.getProduct(ii.getProductId());
                products.add(product);
            }

        } else {

        }
        return products;
    }

    private ArrayList<ProductVirtual> getAllidOfProductInCookie(HttpServletRequest request, HttpServletResponse response, String id, boolean addOrSub) {
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
                            if (productID == Integer.parseInt(id)) {
                                /*
                                    if user click add amout 
                                 */
                                if (addOrSub) {
                                    int idInt = Integer.parseInt(id);
                                    ProductDao productDao = new ProductDao();
                                    Product product = productDao.getProduct(idInt);
                                    amount = Math.min(amount + 1, product.getUnitsInStock());
                                } else {
                                    amount = Math.max(0, amount - 1);
                                }
                            }
                            getAll.add(new ProductVirtual(productID, amount));
                        }
                        cookie.setMaxAge(0);
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

    private BigDecimal getTotalAmount(ArrayList<Product> products, ArrayList<ProductVirtual> productVirtuals) {
        BigDecimal ans = BigDecimal.ZERO;
        for (int i = 0; i < products.size(); ++i) {
            ans = ans.add(BigDecimal.valueOf(products.get(i).getUnitsPrice() * productVirtuals.get(i).getAmount()));
        }
        return ans;
    }

    private BigDecimal getVat(BigDecimal totalAmount) {
        return totalAmount.divide(BigDecimal.TEN);
    }

    private BigDecimal getTotalPayment(BigDecimal totalAmount, BigDecimal ship, BigDecimal vat) {
        return totalAmount.add(ship).add(vat);
    }

}
