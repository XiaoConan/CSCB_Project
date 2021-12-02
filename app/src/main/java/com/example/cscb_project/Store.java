package com.example.cscb_project;

import java.util.ArrayList;

public class Store {
    private ArrayList<OrderList> orderLists;
    private ArrayList<Product> products;

    public Store(){
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

}
