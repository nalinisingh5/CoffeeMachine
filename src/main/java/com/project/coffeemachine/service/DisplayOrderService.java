package com.project.coffeemachine.service;

import com.project.coffeemachine.model.BeverageOrder;

import java.util.List;
import java.util.Map;

public interface DisplayOrderService {
    void displayOrders(List<BeverageOrder> beverageOrderList, Map<String, List<String>> unavailableIngredients);
}
