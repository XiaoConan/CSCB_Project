package com.example.cscb_project;

import java.util.ArrayList;
import java.util.Objects;

public class OrderList {
    private ArrayList<Order> orders;
    private String customerID;
    private String storeID;
    private boolean complete;

    public OrderList() {
        orders = new ArrayList<Order>();
        customerID = "";
        storeID = "";
        complete = false;
    }

    public OrderList(ArrayList<Order> orders) {
        this.orders = orders;
        customerID = "";
        storeID = "";
        complete = false;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public void setCustomerID(String userID) {
        this.customerID = userID;
    }

    public String getStoreID() {
        return storeID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void clearList() {
        orders.clear();
    }

    public Order getOrder(int index) {
        return orders.get(index);
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete() {
        complete = true;
    }

    public void setIncomplete() {
        complete = false;
    }

}
