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
public class Manufacturer {
    private int ManufacturerID;
    private String ManufacturerName;

    public Manufacturer() {
    }

    public Manufacturer(int ManufacturerID, String ManufacturerName) {
        this.ManufacturerID = ManufacturerID;
        this.ManufacturerName = ManufacturerName;
    }

    public int getManufacturerID() {
        return ManufacturerID;
    }

    public void setManufacturerID(int ManufacturerID) {
        this.ManufacturerID = ManufacturerID;
    }

    public String getManufacturerName() {
        return ManufacturerName;
    }

    public void setManufacturerName(String ManufacturerName) {
        this.ManufacturerName = ManufacturerName;
    }
    
}
