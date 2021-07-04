package com.project.coffeemachine.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("hotCoffee")
public class HotCoffee {
    private int hotWater;
    private int hotMilk;
    private int sugarSyrup;
    private int coffee;
}
