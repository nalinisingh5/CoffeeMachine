package com.project.coffeemachine.repository;

import com.project.coffeemachine.model.Ingredients;
import com.project.coffeemachine.model.TotalItemsQuantity;

import java.util.List;
import java.util.Map;

public interface TotalItemsQuantityRepository {
    List<Ingredients> addAllIngredients(TotalItemsQuantity totalItemsQuantity);
    List<Ingredients> restockIngredients(Map<String, List<String>> unavailableIngredients, List<Ingredients> oldIngredientsList);
}
