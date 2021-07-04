package com.project.coffeemachine.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.coffeemachine.model.Beverages;
import com.project.coffeemachine.model.BeverageOrder;
import com.project.coffeemachine.repository.BeveragesOrderRepository;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Repository
public class BeveragesOrderRepositoryImpl implements BeveragesOrderRepository {

    /**
     * This method adds all the ingredients from DTO TotalItemsQuantity before machine is started
     */
    @Override
    public List<BeverageOrder> addAllBeverages(Beverages beverages) {
        List<BeverageOrder> beverageOrderList = new ArrayList<>();
        ObjectMapper mapObject = new ObjectMapper();
        Map<String, Map<String, Integer>> mapObj = mapObject.convertValue(beverages, Map.class);
        for (Map.Entry<String, Map<String, Integer>> entry : mapObj.entrySet()) {
            beverageOrderList.add(new BeverageOrder(entry.getKey(), entry.getValue()));
        }

        return beverageOrderList;
    }

}
