package com.example.cscb_project;

import java.util.ArrayList;

public class Store {
    private ArrayList<OrderList> orderLists;
    private ArrayList<Product> products;
    private ArrayList<UserAccount> Users;

    /*missing constructor
    *
    *
    *
    *
    *
    *
    * */

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

    public ArrayList<UserAccount> getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<UserAccount> users) {
        Users = users;
    }
}
