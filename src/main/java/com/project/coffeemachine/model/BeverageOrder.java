package com.project.coffeemachine.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BeverageOrder implements Comparable<BeverageOrder> {
    private Map<String, Integer> quantity = new HashMap<>();
    private String beverageName;
    private boolean available = false;

    public BeverageOrder(String name, Map<String, Integer> quantity) {
        this.beverageName = name;
        setQuantity(quantity);
    }

    public int compareTo(BeverageOrder beverageOrder) {
        return beverageName.compareTo(beverageOrder.getBeverageName());
    }

    public void setQuantity(Map<String, Integer> quantity) {
        for (Map.Entry<String, Integer> s : quantity.entrySet()) {
            if (this.quantity.containsKey(s.getKey())) {
                this.quantity.put(s.getKey(), this.quantity.get(s) + s.getValue());
            } else {
                this.quantity.put(s.getKey(), s.getValue());
            }
        }
    }

}