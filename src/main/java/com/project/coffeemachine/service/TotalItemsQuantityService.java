package com.project.coffeemachine.service;

import com.project.coffeemachine.model.Ingredients;
import com.project.coffeemachine.model.TotalItemsQuantity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface TotalItemsQuantityService {

    List<Ingredients> addAllIngredients(TotalItemsQuantity totalItemsQuantity);

    List<Ingredients> restockIngredients(Map<String, List<String>> unavailableIngredients, List<Ingredients> oldIngredientsList);
}
