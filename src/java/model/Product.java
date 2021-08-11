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
public class Product implements Serializable {

    private int productId;
    private String productName;
    private long unitsPrice;
    private int categoryId;
    private int unitsInStock;
    private String description;
    private int manufacturerID;
    private boolean isContinues;
    private int stars;

    public Product() {
    }

    public Product(int productId, String productName, long unitsPrice, int categoryId, int unitsInStock, String description, int manufacturerID, boolean isContinues, int stars) {
        this.productId = productId;
        this.productName = productName;
        this.unitsPrice = unitsPrice;
        this.categoryId = categoryId;
        this.unitsInStock = unitsInStock;
        this.description = description;
        this.manufacturerID = manufacturerID;
        this.isContinues = isContinues;
        this.stars = stars;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getUnitsPrice() {
        return unitsPrice;
    }

    public void setUnitsPrice(long unitsPrice) {
        this.unitsPrice = unitsPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public boolean isIsContinues() {
        return isContinues;
    }

    public void setIsContinues(boolean isContinues) {
        this.isContinues = isContinues;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String formatPrice() {
        return String.format("%,d", this.unitsPrice);
    }
}
