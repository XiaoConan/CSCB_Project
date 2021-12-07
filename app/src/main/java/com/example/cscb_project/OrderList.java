package com.example.cscb_project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OrderList {
    private Map<String, Integer> productToQuantity;
    private String customerID;
    private String storeID;
    private boolean complete;

    public OrderList() {
        productToQuantity = new HashMap<>();
        customerID = "";
        storeID = "";
        complete = false;
    }

    public OrderList(String customerID, String storeID) {
        productToQuantity = new HashMap<>();
        this.customerID = customerID;
        this.storeID = storeID;
        complete = false;
    }

    public  OrderList(String customerID, String storeID, Map<String, Integer> map) {
        productToQuantity = map;
        this.customerID = customerID;
        this.storeID = storeID;
        complete = false;
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

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Map<String, Integer> getProductToQuantity() {
        return productToQuantity;
    }

    public void setProductToQuantity(Map<String, Integer> productToQuantity) {
        this.productToQuantity = productToQuantity;
    }
}
