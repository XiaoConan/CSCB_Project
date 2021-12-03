package com.example.cscb_project;

import java.util.ArrayList;
import java.io.Serializable;

public class CustomerAccount extends UserAccount implements Serializable{
    // fields??
    private ArrayList<OrderList> orders;
    private ArrayList<Order> cart;

    public CustomerAccount() {
        super();
        orders = new ArrayList<OrderList>();
        cart = new ArrayList<Order>();
    }

    public CustomerAccount(String username, String password) {
        super(username, password);
        orders = new ArrayList<OrderList>();
    }

    public void sendOrder(){
        orders.add(new OrderList(cart));
        cart = new ArrayList<Order>();
    }

    public void addProduct(Product product, int amount){
        cart.add(new Order(product, amount));
    }

}
