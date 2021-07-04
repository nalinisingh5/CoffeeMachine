package com.project.coffeemachine.repository.impl;

import com.project.coffeemachine.model.BeverageOrder;
import com.project.coffeemachine.repository.DisplayOrderRepository;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Repository
public class DisplayOrderRepositoryImpl implements DisplayOrderRepository {

    /**
     * This method displays order status
     */
    @Override
    public void displayOrders(List<BeverageOrder> beverageOrderList, Map<String, List<String>> unavailableIngredients) {
        for (BeverageOrder beverageOrder : beverageOrderList) {
            boolean isOrderAvailable = beverageOrder.isAvailable();
            String orderDetails = "";
            if (isOrderAvailable) {
                orderDetails = beverageOrder.getBeverageName() + " is prepared ";
            } else {
                StringBuilder items = new StringBuilder(unavailableIngredients.get(beverageOrder.getBeverageName()).stream()
                        .collect(Collectors.joining(",")).replaceAll("\\,", ", "));
                items = items.toString().contains(",") ? items.replace(items.lastIndexOf(","), items.lastIndexOf(",") + 1, " and") : items;
                orderDetails = unavailableIngredients.get(beverageOrder.getBeverageName()).size() > 1 ? beverageOrder.getBeverageName() + " cannot be prepared because " + items + " are not available " : beverageOrder.getBeverageName() + " cannot be prepared because " + items + " is not available ";
            }
            System.out.println(orderDetails);
        }
    }
}
