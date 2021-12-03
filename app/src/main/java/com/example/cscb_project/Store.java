package com.example.cscb_project;

import java.io.Serializable;
import java.util.ArrayList;

public class Store implements Serializable {
    private OwnerAccount owner;
    private ArrayList<OrderList> orderLists;
    private ArrayList<Product> products;

    public Store(){
        this.orderLists = new ArrayList<OrderList>();
        this.products = new ArrayList<Product>();
    }

    public OwnerAccount getOwner() {
        return owner;
    }

    public void setOwner(OwnerAccount owner) {
        this.owner = owner;
    }

    public Store(OwnerAccount owner){
        this.owner = owner;
        this.orderLists = new ArrayList<OrderList>();
        this.products = new ArrayList<Product>();
    }

    public ArrayList<OrderList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(ArrayList<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){ products.add(product); }

    public void receiveOrder(OrderList newOrder){orderLists.add(newOrder); }

}
