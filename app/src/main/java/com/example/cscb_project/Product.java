package com.example.cscb_project;

import java.util.Objects;

public class Product {
    private String name;
    private double price;
    private String brand;
    private Store store;

    public Product() {
        name = "";
        price = 0;
        brand = "";
        store = null;
    }

    public Product(String name, double price, String brand, Store store) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.store = store;
    }

    public Store getStore() { return store; }

    public void setStore(Store store) { this.store = store; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name) && Objects.equals(brand, product.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, brand);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=$" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}
