package com.project.coffeemachine.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.coffeemachine.model.Ingredients;
import com.project.coffeemachine.model.TotalItemsQuantity;
import com.project.coffeemachine.repository.TotalItemsQuantityRepository;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Data
@Repository
public class TotalItemsQuantityRepositoryImpl implements TotalItemsQuantityRepository {

    /**
     * This method is for initial stocking of all the ingredients
     */
    @Override
    public List<Ingredients> addAllIngredients(TotalItemsQuantity totalItemsQuantity) {
        List<Ingredients> list = new ArrayList<>();
        ObjectMapper mapObject = new ObjectMapper();
        Map<String, Integer> mapObj = mapObject.convertValue(totalItemsQuantity, Map.class);
        for (Map.Entry<String, Integer> entry : mapObj.entrySet()) {
            list.add(new Ingredients(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    /**
     * This method is for restocking ingredients when all drinks are unavailable
     */
    @Override
    public List<Ingredients> restockIngredients(Map<String, List<String>> unavailableIngredients, List<Ingredients> oldIngredientsList) {

        for (Ingredients i : oldIngredientsList) {
                i.setStock(i.getStock() + randomFillValue());
        }
        return oldIngredientsList;
    }

    private int randomFillValue() {
        int lowerbound = 100;
        int upperbound = 200;
        Random random = new Random();
        return random.ints(lowerbound, upperbound)
                .findFirst()
                .getAsInt();
    }

}
