package com.example.cscb_project;

import java.util.Objects;

public class Order {
    private Product product;
    private int quantity;

    public Order() {
        product = new Product();
        quantity = 0;
    }

    public Order(Product product) {
        this.product = product;
    }

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }

    public void setProduct(Product newProduct) {
        product = newProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getQuantity() == order.getQuantity() && getProduct().equals(order.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    @Override
    public String toString() {
        return "Order{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}