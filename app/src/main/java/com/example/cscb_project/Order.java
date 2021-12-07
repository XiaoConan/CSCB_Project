package com.example.cscb_project;

import java.util.Objects;

public class Order {
    private String productID;
    private String name;
    private int quantity;
    private double subtotal;

    public Order() {
        productID = "";
        name = "";
        quantity = 0;
        subtotal = 0;
    }

    public Order(String productID, String name, int quantity, double subtotal) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return Math.round(subtotal*100.0)/100.0;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return productID.equals(order.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }
}
