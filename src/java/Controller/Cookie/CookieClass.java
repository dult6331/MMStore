/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Cookie;

import java.util.ArrayList;
import javax.servlet.http.Cookie;
import model.ProductVirtual;

/**
 *
 * @author Alienware 15R3
 */
public class CookieClass {
    private static String encryption ="%&%";
    
    
    public static String getEncryption() {
        return encryption;
    }

    public static void setEncryption(String encryption) {
        CookieClass.encryption = encryption;
    }

    public String getValueInCookie(ArrayList<Integer> cookie){
        StringBuilder sb=new StringBuilder();
        for(Integer ii : cookie){
            sb.append(ii).append(encryption);
        }
        return sb.toString() ;
    }
    public Cookie getValueInCookie(String name,ArrayList<Integer> cookie,int age){
        StringBuilder sb=new StringBuilder();
        for(Integer ii : cookie){
            sb.append(ii).append(encryption);
        }
        Cookie cookie1=new Cookie(name,sb.toString());
        cookie1.setMaxAge(age);
        return cookie1 ;
    }


    public Cookie getValueInCookie(String productID, ArrayList<ProductVirtual> allidOfProductInCookie, int i, boolean b) {
         StringBuilder sb=new StringBuilder();
        for(ProductVirtual ii : allidOfProductInCookie){
            sb.append(ii.getProductId()).append(encryption).append(ii.getAmount()).append(encryption);
        }
        Cookie cookie1=new Cookie(productID,sb.toString());
        cookie1.setMaxAge(i);
        return cookie1 ;
    }
   
    
}
