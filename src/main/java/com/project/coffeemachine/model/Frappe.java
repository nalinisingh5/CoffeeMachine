package com.project.coffeemachine.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("frappe")
public class Frappe {
    private int coldWater;
    private int coldMilk;
    private int sugarSyrup;
    private int coffee;
    private int cream;
    private int ice;
    private int iceCream;
}
