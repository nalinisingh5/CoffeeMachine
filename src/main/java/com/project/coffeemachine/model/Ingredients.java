package com.project.coffeemachine.model;

import lombok.Data;

@Data
public class Ingredients implements Comparable<Ingredients>{
    private String name;
    private int stock;

    public Ingredients(String name, int stock){
        this.name = name;
        this.stock = stock;
    }

    public int compareTo(Ingredients Ingredients) {
        return name.compareTo(Ingredients.getName());
    }
}
