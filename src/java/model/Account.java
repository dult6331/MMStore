/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Alienware 15R3
 */
public class Account implements Serializable{
    private int acountId;
    private String userName;
    private String passWord;
    private boolean isAdministrator;

    public Account() {
    }

    public Account(int acountId, String userName, String passWord, boolean isAdministrator) {
        this.acountId = acountId;
        this.userName = userName;
        this.passWord = passWord;
        this.isAdministrator = isAdministrator;
    }

    public int getAcountId() {
        return acountId;
    }

    public void setAcountId(int acountId) {
        this.acountId = acountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator(boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    @Override
    public String toString() {
        return "Account{" + "acountId=" + acountId + ", userName=" + userName + ", passWord=" + passWord + ", isAdministrator=" + isAdministrator + '}';
    }
    
    
    
}

