package com.project.coffeemachine;

import com.google.gson.Gson;
import com.project.coffeemachine.dto.CoffeeMachineRequestDto;
import com.project.coffeemachine.facade.AvailabilityValidatorFacade;
import com.project.coffeemachine.facade.PrepareBeveragesFacade;
import com.project.coffeemachine.model.BeverageOrder;
import com.project.coffeemachine.model.Ingredients;
import com.project.coffeemachine.service.BeveragesOrderService;
import com.project.coffeemachine.service.DisplayOrderService;
import com.project.coffeemachine.service.TotalItemsQuantityService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class CoffeeMachineAutomaticUtil implements CommandLineRunner {

    @Autowired
    BeveragesOrderService beveragesOrderService;

    @Autowired
    TotalItemsQuantityService totalItemsQuantityService;

    @Autowired
    DisplayOrderService displayOrderService;

    @Autowired
    AvailabilityValidatorFacade availabilityValidatorFacade;

    @Autowired
    PrepareBeveragesFacade prepareBeveragesFacade;

    Map<String, List<String>> unavailableIngredients = new HashMap<>();
    List<BeverageOrder> beverageOrderList = new ArrayList<>();
    List<Ingredients> ingredientList = new ArrayList<>();

    @PostConstruct
    public void init() {
        CoffeeMachineRequestDto coffeeMachineRequestDto = readInput();
        ingredientList = totalItemsQuantityService.addAllIngredients(coffeeMachineRequestDto.getMachine().getTotalItemsQuantity());
        beverageOrderList = beveragesOrderService.addAllBeveragesOrdered(coffeeMachineRequestDto.getMachine().getBeverages());
        unavailableIngredients = availabilityValidatorFacade.availabilityValidator(beverageOrderList, ingredientList);
    }

    public CoffeeMachineRequestDto readInput() {

        String data = "";
        try {
            data = IOUtils.toString(CoffeeMachineAutomaticUtil.class.getResourceAsStream("/data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        CoffeeMachineRequestDto coffeeMachineRequestDto = new Gson().fromJson(data, CoffeeMachineRequestDto.class);
        return coffeeMachineRequestDto;
    }


    public class Prepare {
        public void prepareOrder(Map<String, List<String>> unavailableIngredients) {
            prepareBeveragesFacade.prepareBeverages(beverageOrderList, ingredientList, unavailableIngredients);
        }
    }

//    public void prepareOrderedBeverages() {
//        try {
//            prepareScheduler.scheduleAtFixedRate(new Runnable() {
//                @Override
//                public void run() {
//                    displayOrderService.displayOrders(beverageOrderList, ingredientList, unavailableIngredients);
//                    try {
//                        new Prepare().prepareOrder();
//                    } catch (Exception e) {
//                    }
//                }
//            }, 0, 1, TimeUnit.MINUTES);
//            //scheduledExecutorService.shutdown();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Machine needs repair/restart. Please call 9850270387");
//        }
//    }

    /**
     * This method is responsible for preparing N orders where every order takes T amount of time.
     * Here, I assuming that the number of beverage orders that are received is same as Outlets N and as mentioned
     * each beverage takes T amount of time and N orders are prepared at intervals if I.
     * <p>
     * 1. Check if any of the beverages can be prepared.
     * 2. If yes, then call makeBeverages
     * 3. The method makeBeverages internally updates availabilityValidator and displays OrderStatus
     * 4. If isAnyMakeable is not true then restock all the ingredientsList, updateDrinkAvailableStatus
     */
    @Override
    public void run(String... args) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable periodicTask =
                () -> {
                    boolean isRestock;
                    boolean isAnyAvailable = availabilityValidatorFacade.isAnyAvailable(beverageOrderList, ingredientList, unavailableIngredients);
                    if (isAnyAvailable) {
                        System.out.println("\n\nPreparing orders....");
                        System.out.println();
                        new Prepare().prepareOrder(unavailableIngredients);
                    } else {
                        isRestock = true;
                        System.out.println("\n\nRestocking the Coffee Machine....");
                        System.out.println();
                        ingredientList = totalItemsQuantityService.restockIngredients(unavailableIngredients, ingredientList);
                        availabilityValidatorFacade.updateAvailability(beverageOrderList, unavailableIngredients, ingredientList, isRestock);
                    }
                };
        executor.scheduleAtFixedRate(periodicTask, 0, 10, TimeUnit.SECONDS);
    }
}
