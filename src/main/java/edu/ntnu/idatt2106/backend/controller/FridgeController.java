package edu.ntnu.idatt2106.backend.controller;

import edu.ntnu.idatt2106.backend.model.fridge.FridgeItem;
import edu.ntnu.idatt2106.backend.model.fridge.FridgeItemRequest;
import edu.ntnu.idatt2106.backend.model.user.User;
import edu.ntnu.idatt2106.backend.service.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/fridge")
public class FridgeController {

    private final FridgeService fridgeService;

    @Autowired
    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<FridgeItem>> getFridgeItemsByUserId(@AuthenticationPrincipal User user) {
        return fridgeService.getFridgeItemsByUserId(user);
    }

    @GetMapping("/get/number")
    public ResponseEntity<Map<String, Integer>> getNumberOfFridgeItemsByUserID(@AuthenticationPrincipal User user) {
        return fridgeService.getNumberOfFridgeItemsByUserID(user);
    }

    @GetMapping("/get/expired")
    public ResponseEntity<List<FridgeItem>> getExpiredFridgeItemsByUserId(@AuthenticationPrincipal User user) {
        return fridgeService.getExpiredFridgeItemsByUserId(user);
    }

    @GetMapping("/get/notification")
    public ResponseEntity<List<FridgeItem>> getNotificationItems(@AuthenticationPrincipal User user) {
        return fridgeService.getExpiredAndAlmostExpiredFridgeItemsByUser(user);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFridgeItems(@RequestBody List<FridgeItemRequest> fridgeItemRequests,
                                                 @AuthenticationPrincipal User user) {
        return fridgeService.addListOfFridgeItems(user, fridgeItemRequests);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFridgeItems(@RequestBody List<Long> fridgeItemIds,
                                                    @AuthenticationPrincipal User user) {
        return fridgeService.removeListOfFridgeItems(fridgeItemIds, user);
    }

    @PutMapping("/edit/{fridgeItemId}")
    public ResponseEntity<FridgeItem> editFridgeItem(@PathVariable Long fridgeItemId
            , @RequestBody FridgeItemRequest updatedFridgeItem
            , @AuthenticationPrincipal User user) {
        return fridgeService.editFridgeItem(fridgeItemId, updatedFridgeItem, user);
    }

    @PostMapping("remove/byRecipe")
    public ResponseEntity<String> removeFridgeItemsByRecipe(@RequestBody List<FridgeItemRequest> items,
                                                            @AuthenticationPrincipal User user) {
        return fridgeService.removeFridgeItemsByRecipe(items, user);
    }

    @GetMapping("fridge-items/list/date")
    public ResponseEntity<List<FridgeItem>> getItemsByDate(){
        return fridgeService.expirationDate();
    }
}
