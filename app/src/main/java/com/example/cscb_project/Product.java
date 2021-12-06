package com.example.cscb_project;

import java.util.Objects;

public class Product {
    private String name;
    private String brand;
    private double price;
    private String storeID;

    public Product() {
        name = "";
        price = 0;
        brand = "";
        storeID = "";
    }

    public Product(String name, String brand, double price, String store) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.storeID = storeID;
    }

    public String getStore() { return storeID; }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStore(String store) { this.storeID = store; }

}
