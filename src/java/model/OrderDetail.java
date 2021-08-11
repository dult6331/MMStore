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
public class OrderDetail implements Serializable{
    
    private int orderId;
    private int productId;
    private int quanity;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int productId, int quanity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quanity = quanity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }
    
    
    
}
