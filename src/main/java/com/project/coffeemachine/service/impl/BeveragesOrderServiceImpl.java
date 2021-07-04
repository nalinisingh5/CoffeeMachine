package com.project.coffeemachine.service.impl;

import com.project.coffeemachine.model.Beverages;
import com.project.coffeemachine.model.BeverageOrder;
import com.project.coffeemachine.repository.BeveragesOrderRepository;
import com.project.coffeemachine.service.BeveragesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeveragesOrderServiceImpl implements BeveragesOrderService {

    @Autowired
    BeveragesOrderRepository beveragesOrderRepository;
    @Override
    public List<BeverageOrder> addAllBeveragesOrdered(Beverages beverages) {
        return beveragesOrderRepository.addAllBeverages(beverages);
    }
}
