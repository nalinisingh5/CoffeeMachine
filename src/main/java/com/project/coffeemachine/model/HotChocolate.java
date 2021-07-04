package com.project.coffeemachine.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("hotChocolate")
public class HotChocolate {
    private int hotWater;
    private int hotMilk;
    private int sugarSyrup;
    private int chocolateSyrup;
}
