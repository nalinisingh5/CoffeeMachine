package com.project.coffeemachine.service;

import com.project.coffeemachine.model.Beverages;
import com.project.coffeemachine.model.BeverageOrder;

import java.util.List;

public interface BeveragesOrderService {

    List<BeverageOrder> addAllBeveragesOrdered(Beverages beverages);
}
