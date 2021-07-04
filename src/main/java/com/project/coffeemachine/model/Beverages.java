package com.project.coffeemachine.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
public class Beverages{
    @Autowired
    HotChocolate hotChocolate;
    @Autowired
    HotCoffee hotCoffee;
    @Autowired
    Cappuccino cappuccino;
    @Autowired
    Mocha mocha;
    @Autowired
    Frappe frappe;
    @Autowired
    BlackCoffee blackCoffee;
}
