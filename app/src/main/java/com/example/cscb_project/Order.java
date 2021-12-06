package com.example.cscb_project;

import java.util.Objects;

public class Order {
    private String name;
    private int quantity;
    private double subtotal;

    public Order() {
        name = "";
        quantity = 0;
        subtotal = 0;
    }

    public Order(String name, int quantity, double subtotal) {
        this.name = name;
        this.quantity = quantity;
        this.subtotal = subtotal;
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
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return name.equals(order.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
