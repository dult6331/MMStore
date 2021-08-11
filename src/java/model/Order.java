/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Alienware 15R3
 */
public class Order implements Serializable{
    
    private int orderId;
    private int accountId;
    private Date OrderDate;
    private ArrayList<Odetail> lst;

    public ArrayList<Odetail> getLst() {
        return lst;
    }

    public void setLst(ArrayList<Odetail> lst) {
        this.lst = lst;
    }

    public Order() {
    }

    public Order(int orderId, int accountId, Date OrderDate) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.OrderDate = OrderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", accountId=" + accountId + ", OrderDate=" + OrderDate + '}';
    }
    
    
    
}
