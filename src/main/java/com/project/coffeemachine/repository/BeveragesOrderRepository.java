package com.project.coffeemachine.repository;

import com.project.coffeemachine.model.Beverages;
import com.project.coffeemachine.model.BeverageOrder;

import java.util.List;

public interface BeveragesOrderRepository {

    List<BeverageOrder> addAllBeverages(Beverages beverages);
}
