package com.project.coffeemachine.service.impl;

import com.project.coffeemachine.model.BeverageOrder;
import com.project.coffeemachine.repository.DisplayOrderRepository;
import com.project.coffeemachine.service.DisplayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DisplayOrderServiceImpl implements DisplayOrderService {

    @Autowired
    DisplayOrderRepository displayOrderRepository;
    @Override
    public void displayOrders(List<BeverageOrder> beverageOrderList, Map<String, List<String>> unavailableIngredients) {
        displayOrderRepository.displayOrders(beverageOrderList, unavailableIngredients);
    }
}
