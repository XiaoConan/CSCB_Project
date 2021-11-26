package com.example.cscb_project;

import java.util.ArrayList;

public class CustomerAccount extends UserAccount {
    // fields??
    private ArrayList<OrderList> orders;

    public CustomerAccount() {
        super();
        orders = new ArrayList<OrderList>();
    }

    public CustomerAccount(String username, String password) {
        super(username, password);
        orders = new ArrayList<OrderList>();
    }

}
