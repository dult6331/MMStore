/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Hfyl
 */
public class Odetail {
    private int ProductID;
    private String ProductName;
    private long UnitsPrice;
    private int Quantity;

    public Odetail() {
    }

    public Odetail(int ProductID, String ProductName, long UnitsPrice, int Quantity) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.UnitsPrice = UnitsPrice;
        this.Quantity = Quantity;
    }

    

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public long getUnitsPrice() {
        return UnitsPrice;
    }

    public void setUnitsPrice(long UnitsPrice) {
        this.UnitsPrice = UnitsPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    public String formatPrice() {
        return String.format("%,d", this.UnitsPrice);
    }
}
