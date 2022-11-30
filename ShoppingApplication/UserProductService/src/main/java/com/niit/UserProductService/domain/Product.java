package com.niit.UserProductService.domain;

import org.springframework.data.annotation.Id;

public class Product {
    @Id
    private int productId;
    private String productName;
    private String productType;
    private String productManufacturer;
    private int yearOfMfg;
    private int yearOfExp;

    public Product(int productId, String productName, String productType, String productManufacturer, int yearOfMfg, int yearOfExp) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productManufacturer = productManufacturer;
        this.yearOfMfg = yearOfMfg;
        this.yearOfExp = yearOfExp;
    }

    public Product() {
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public int getYearOfMfg() {
        return yearOfMfg;
    }

    public void setYearOfMfg(int yearOfMfg) {
        this.yearOfMfg = yearOfMfg;
    }

    public int getYearOfExp() {
        return yearOfExp;
    }

    public void setYearOfExp(int yearOfExp) {
        this.yearOfExp = yearOfExp;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productManufacturer='" + productManufacturer + '\'' +
                ", yearOfMfg=" + yearOfMfg +
                ", yearOfExp=" + yearOfExp +
                '}';
    }
}
