package com.ongiao.wis.pojo;

public class WarehouseInventory {
    private String productName;
    private String productCode;
    private String locationName;
    private int quanty;
    private double perWeight;

    public WarehouseInventory () {
    }

    public WarehouseInventory (String productName, String productCode, String locationName, int quanty, double perWeight) {
        this.productName = productName;
        this.productCode = productCode;
        this.locationName = locationName;
        this.quanty = quanty;
        this.perWeight = perWeight;
    }

    public String getProductName () {
        return productName;
    }

    public void setProductName (String productName) {
        this.productName = productName;
    }

    public String getProductCode () {
        return productCode;
    }

    public void setProductCode (String productCode) {
        this.productCode = productCode;
    }

    public String getLocationName () {
        return locationName;
    }

    public void setLocationName (String locationName) {
        this.locationName = locationName;
    }

    public int getQuanty () {
        return quanty;
    }

    public void setQuanty (int quanty) {
        this.quanty = quanty;
    }

    public double getPerWeight () {
        return perWeight;
    }

    public void setPerWeight (double perWeight) {
        this.perWeight = perWeight;
    }

    @Override
    public String toString () {
        return "WarehouseInventory{" +
                "productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", locationName='" + locationName + '\'' +
                ", quanty=" + quanty +
                ", perWeight=" + perWeight +
                '}';
    }
}
