package com.project.coffeemachine.facade;

import com.project.coffeemachine.model.BeverageOrder;
import com.project.coffeemachine.model.Ingredients;
import com.project.coffeemachine.service.DisplayOrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Component
public class PrepareBeveragesFacade {

    @Autowired
    AvailabilityValidatorFacade availabilityValidator;

    @Autowired
    DisplayOrderService displayOrderService;


    /**
     * This method takes orderedDrinkLists, inventoryIngredientsList and prepares the beverages
     * for which isAvailable flag is true and updates inventory and unavailableIngredients
     * @return
     */
    public Map<String, List<String>> prepareBeverages(List<BeverageOrder> beverageOrderList, List<Ingredients> inventoryIngredientsList, Map<String, List<String>> unavailableIngredients) {
        try {

            for (BeverageOrder beverage : beverageOrderList) {
                List<String> unavailableList = new ArrayList<>();
                for (Ingredients i : inventoryIngredientsList) {
                    if (beverage.getQuantity().containsKey(i.getName())) {
                        if (i.getStock() >= beverage.getQuantity().get(i.getName()))
                            beverage.setAvailable(true);
                        else {
                            beverage.setAvailable(false);
                            unavailableList.add(i.getName());
                            break;
                        }
                    }
                }

                if (beverage.isAvailable()) {
                    for (Ingredients i : inventoryIngredientsList) {
                        if (beverage.getQuantity().containsKey(i.getName())) {
                            i.setStock(i.getStock() - beverage.getQuantity().get(i.getName()));
                        }
                    }
                } else {
                    unavailableIngredients = availabilityValidator.updateIngredientsAvailability(beverageOrderList, unavailableList, unavailableIngredients);
                }

            }
            displayOrderService.displayOrders(beverageOrderList, unavailableIngredients);
            return unavailableIngredients;
        } catch (Exception e) {
            System.out.println(e);
        }
        return unavailableIngredients;
    }
}
