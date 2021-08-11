package Validate;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alienware 15R3
 */
public class Validate {
    private static String regexname="^[\\w\\d\\@\\.\\p{L}]+$"; 
    private static String regexpass="^[\\w\\d\\@]+$"; 
    private static String regexInt="^\\d+$"; 
    private static String regexDouble="^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$"; 
    private static String regexEmail="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\]"; 
    public static boolean checkName(String name){
        return Pattern.matches(regexname, name.trim());
    }
    public static boolean checkInt(String number){
        number=number.trim();
        if(!number.trim().matches(regexInt)){
            return false;
        }
        try {
            int x=Integer.parseInt(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static boolean checkDouble(String number){
        number=number.trim();
        if(!number.trim().matches(regexDouble)){
            return false;
        }
        try {
            Double x=Double.parseDouble(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public static boolean checkDate(String input) throws Exception {
        if (input.isEmpty()) {
            return false;
        }
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        f.setLenient(false);
        try {
            f.parse(input);
        } catch (ParseException ex) {
            throw new Exception("Please enter date by format dd/MM/yyyy");
        }
        return true;
    }
    public static boolean checkEmail(String input) {
        return input.trim().matches(regexEmail);
    }
    
    /*
        password can not empty 
        password at least 4 digit
        password at least 1 lower character 1 upper character 1 digit
    
    */
    public static boolean checkPass(String pass){
        if(Pattern.matches(regexpass, pass)&&!pass.isEmpty()){
            if(pass.length()<=4){
                return false;
            }
            int lowerCharacter=0;
            int upperCharacter=0;
            int digit=0; 
            for(Character c: pass.toCharArray()){
                if(c.compareTo('a')>=0&&c.compareTo('z')<=0){
                    ++lowerCharacter;
                }else
                if(c.compareTo('A')>=0&&c.compareTo('Z')<=0){
                    ++upperCharacter;
                }else
                if(c.compareTo('0')>=0&&c.compareTo('9')<=0){
                    ++digit;
                }

            }
            if(lowerCharacter<1||upperCharacter<1||digit<1){
                return false;
            }
            return true;
        }else 
        return false ;
    }                                                 
    
}
