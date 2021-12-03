package com.example.cscb_project;

import java.util.ArrayList;
import java.util.Objects;

public class OrderList {
    private ArrayList<Order> orders;
    private String name; // Maybe ID instead?
    private boolean complete;

    public OrderList() {
        orders = new ArrayList<Order>();
        name = "";
        complete = false;
    }

    public OrderList(String name) {
        orders = new ArrayList<Order>();
        this.name = name;
        complete = false;
    }

    public OrderList(ArrayList<Order> orders, String name) {
        this.orders = orders;
        this.name = name;
        complete = false;
    }

    public OrderList(ArrayList<Order> orders) {
        this.orders = orders;
        complete = false;
    }

    public void addOrder(Order order) {
        // Simplest way to do this
        // Maybe can change how OrderList works if we want it to better
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public void removeOrder(int index) {
        orders.remove(index);
    }

    public void clearList() {
        orders.clear();
    }

    public Order getOrder(int index) {
        return orders.get(index);
    }

    public int indexOf(Product product) {
        // ?? Maybe bad/useless function
        // Returns the index of the first instance an order of said product (-1 if doesn't exist)
        // Bad function if we allow multiple orders of same product in the list (easy/current way)
        // Good if we don't allow repeats of same product (harder??)
        for (Order order: orders) {
            if (order.getProduct().equals(product)) {
                return orders.indexOf(order);
            }
        }
        return -1;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComplete() {
        complete = true;
    }

    public void setIncomplete() {
        complete = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderList orderList = (OrderList) o;
        return isComplete() == orderList.isComplete() && getOrders().equals(orderList.getOrders()) && getName().equals(orderList.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrders(), getName());
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "orders=" + orders +
                ", name='" + name + '\'' +
                ", complete=" + complete +
                '}';
    }
}
