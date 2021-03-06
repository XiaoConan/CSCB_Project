package com.example.cscb_project;

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

    public Product(String name, String brand, double price, String storeID) {
        this.name = name;
        this.price = Math.round(price*100.0)/100.0;;
        this.brand = brand;
        this.storeID = storeID;
    }

    public String getStoreID() { return storeID; }

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
        this.price = Math.round(price*100.0)/100.0;;
    }

    public void setStoreID(String store) { this.storeID = store; }

}
