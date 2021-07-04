package com.project.coffeemachine.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("blackCoffee")
public class BlackCoffee {
    private int hotWater;
    private int sugarSyrup;
    private int coffee;
}
