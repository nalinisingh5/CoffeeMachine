package com.project.coffeemachine.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("mocha")
public class Mocha {
    private int hotWater;
    private int hotMilk;
    private int sugarSyrup;
    private int coffee;
    private int cream;
    private int chocolateSyrup;
}
