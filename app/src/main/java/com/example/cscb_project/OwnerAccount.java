package com.example.cscb_project;

import java.util.ArrayList;
import java.io.Serializable;

public class OwnerAccount extends UserAccount implements Serializable{
    private Store store;

    public OwnerAccount() {
        super();
        store = new Store();
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public OwnerAccount(String username, String password) {
        super(username, password);
        store = new Store();
    }

    public void addProduct(String name, String brand, double price ){
        store.addProduct(new Product(name, price, brand, store));
    }

    public void CompleteOrder(OrderList order){
        order.setComplete();
    }
}
