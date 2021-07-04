package com.project.coffeemachine.service.impl;

import com.project.coffeemachine.model.Ingredients;
import com.project.coffeemachine.model.TotalItemsQuantity;
import com.project.coffeemachine.repository.TotalItemsQuantityRepository;
import com.project.coffeemachine.service.TotalItemsQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TotalItemsQuantityServiceImpl implements TotalItemsQuantityService {

    @Autowired
    TotalItemsQuantityRepository totalItemsQuantityRepository;

    @Override
    public List<Ingredients> addAllIngredients(TotalItemsQuantity totalItemsQuantity) {
        return totalItemsQuantityRepository.addAllIngredients(totalItemsQuantity);
    }

    @Override
    public List<Ingredients> restockIngredients(Map<String, List<String>> unavailableIngredients, List<Ingredients> oldIngredientsList) {
        return totalItemsQuantityRepository.restockIngredients(unavailableIngredients, oldIngredientsList);
    }
}
