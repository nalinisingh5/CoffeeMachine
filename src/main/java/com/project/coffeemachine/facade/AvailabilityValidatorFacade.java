package com.project.coffeemachine.facade;

import com.project.coffeemachine.model.BeverageOrder;
import com.project.coffeemachine.model.Ingredients;
import com.project.coffeemachine.repository.impl.BeveragesOrderRepositoryImpl;
import com.project.coffeemachine.repository.impl.TotalItemsQuantityRepositoryImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Component
public class AvailabilityValidatorFacade {


    @Autowired
    BeveragesOrderRepositoryImpl beveragesOrderRepositoryImpl;

    @Autowired
    TotalItemsQuantityRepositoryImpl totalItemsQuantityRepositoryImpl;

    public Map<String, List<String>> availabilityValidator(List<BeverageOrder> beverageOrderList, List<Ingredients> inventoryIngredientsList) {
        Map<String, List<String>> unavailableIngredients = new HashMap<>();
        for (BeverageOrder beverageOrder : beverageOrderList) {
            Map<String, Integer> measurements = beverageOrder.getQuantity();
            List<String> ingrelist = new ArrayList<>();
            for (Ingredients ingredients : inventoryIngredientsList) {
                if (measurements.containsKey(ingredients.getName()) && ingredients.getStock() < measurements.get(ingredients.getName()))
                    ingrelist.add(ingredients.getName());
            }
            if (ingrelist.size() > 0) {
                unavailableIngredients.put(beverageOrder.getBeverageName(), ingrelist);
                beverageOrder.setAvailable(false);
            } else {
                beverageOrder.setAvailable(true);
            }
        }
        return unavailableIngredients;
    }

    public boolean isAnyAvailable(List<BeverageOrder> beverageOrderList, List<Ingredients> ingredientList, Map<String, List<String>> unavailableIngredients) {
        updateAvailability(beverageOrderList, unavailableIngredients, ingredientList);
        List<Boolean> isAvailableList = new ArrayList<>();
        for (BeverageOrder beverageOrder : beverageOrderList) {
            if (beverageOrder.isAvailable())
                isAvailableList.add(beverageOrder.isAvailable());
        }
        if (isAvailableList.size() > 0)
            return true;
        else return false;
    }

    public void updateAvailability(List<BeverageOrder> beverageOrderList, Map<String, List<String>> unavailableIngredients, List<Ingredients> ingredientList) {
        updateAvailability(beverageOrderList, unavailableIngredients, ingredientList, false);
    }

    public Map<String, List<String>> updateIngredientsAvailability(List<BeverageOrder> beverageOrderList, List<String> unavailableList, Map<String, List<String>> unavailableIngredients) {
        for (BeverageOrder beverage : beverageOrderList) {
            if (!beverage.isAvailable()) {
                List<String> outOfStockIngredients = new ArrayList<>();
                for (Map.Entry<String, Integer> entry : beverage.getQuantity().entrySet()) {
                    if (unavailableList.contains(entry.getKey())) {
                        outOfStockIngredients.add(entry.getKey());
                    }
                }

                if (unavailableIngredients.containsKey(beverage.getBeverageName())) {
                    List<String> list = outOfStockIngredients.stream()
                            .filter(e -> !unavailableIngredients.get(beverage.getBeverageName()).contains(e))
                            .collect(Collectors.toList());
                    List<String> list1 = unavailableIngredients.get(beverage.getBeverageName());
                    list1.addAll(list);
                    unavailableIngredients.put(beverage.getBeverageName(), list1);
                } else {
                    unavailableIngredients.put(beverage.getBeverageName(), outOfStockIngredients);
                }
            }
        }
        return unavailableIngredients;
    }

    public void updateAvailability(List<BeverageOrder> beverageOrderList, Map<String, List<String>> unavailableIngredients, List<Ingredients> ingredientList, boolean isRestock) {
        for (BeverageOrder beverage : beverageOrderList) {
            Map<String, Integer> currRecipe = beverage.getQuantity();
            for (Ingredients i : ingredientList) {
                if (currRecipe.containsKey(i.getName()) && unavailableIngredients.containsKey(beverage.getBeverageName()) && i.getStock() < currRecipe.get(i.getName())) {
                    beverage.setAvailable(false);
                    break;
                } else {
                    beverage.setAvailable(true);
                    if (isRestock)
                        unavailableIngredients.remove(beverage.getBeverageName());
                }
            }
        }
    }
}
