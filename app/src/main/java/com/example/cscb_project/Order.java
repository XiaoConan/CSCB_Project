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

}
