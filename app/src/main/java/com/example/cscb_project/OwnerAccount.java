package com.example.cscb_project;

import java.util.ArrayList;

public class OwnerAccount extends UserAccount {
    private ArrayList<Product> products;
    private ArrayList<OrderList> orders;

    public OwnerAccount() {
        super();
        products = new ArrayList<Product>();
        orders = new ArrayList<OrderList>();
    }

    public OwnerAccount(String username, String password) {
        super(username, password);
        products = new ArrayList<Product>();
        orders = new ArrayList<OrderList>();
    }

}
