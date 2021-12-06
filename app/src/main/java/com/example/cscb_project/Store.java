package com.example.cscb_project;

import java.io.Serializable;
import java.util.ArrayList;

public class Store {
    public static final String DEFAULT_NAME = "Unnamed Store";
    private String ownerUsername;
    private String storeName;
    private ArrayList<String> orderIDs;
    private ArrayList<String> productIDs;

    public Store(String username) {
        ownerUsername = username;
        storeName = DEFAULT_NAME;
        orderIDs = new ArrayList<>();
        productIDs = new ArrayList<>();
    }

    public void setOwner(String owner) {
        this.ownerUsername = owner;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public ArrayList<String> getOrderIDs() {
        return orderIDs;
    }

    public ArrayList<String> getProductIDs() {
        return productIDs;
    }

    public void addProductID(String productID) {
        productIDs.add(productID);
    }

    public void addOrderID(String orderID) {orderIDs.add(orderID); }

}
