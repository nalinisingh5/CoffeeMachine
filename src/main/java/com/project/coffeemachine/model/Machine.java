package com.project.coffeemachine.model;

import lombok.Data;

@Data
public class Machine {
    public Outlets outlets;
    public TotalItemsQuantity totalItemsQuantity;
    public Beverages beverages;
}
