package com.project.coffeemachine.repository;

import com.project.coffeemachine.model.BeverageOrder;

import java.util.List;
import java.util.Map;

public interface DisplayOrderRepository {

    void displayOrders(List<BeverageOrder> beverageOrderList, Map<String, List<String>> unavailableIngredients);
}
