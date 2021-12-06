package com.example.cscb_project;

import java.io.Serializable;
import java.util.ArrayList;

public class Store {
    private String ownerUsername;
    private ArrayList<OrderList> orders;
    private ArrayList<Product> products;

    public Store() {
        this.orders = new ArrayList<OrderList>();
        this.products = new ArrayList<Product>();
    }

    public Store(String username) {
        ownerUsername = username;
        this.orders = new ArrayList<OrderList>();
        this.products = new ArrayList<Product>();
    }

    public String getOwner() {
        return ownerUsername;
    }

    public void setOwner(String owner) {
        this.ownerUsername = owner;
    }

    public ArrayList<OrderList> getOrderLists() {
        return orders;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setOrders(ArrayList<OrderList> orderLists) {
        this.orders = orderLists;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void receiveOrder(OrderList newOrder) {orders.add(newOrder); }

}
