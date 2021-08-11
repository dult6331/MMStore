/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Alienware 15R3
 */
public class ProductVirtual {
    
    private int productId;
    private int amount;

    public ProductVirtual() {
    }

    public ProductVirtual(int productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }
    
    

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
    
}
