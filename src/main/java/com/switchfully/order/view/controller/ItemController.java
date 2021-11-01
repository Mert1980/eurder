package com.switchfully.order.view.controller;

import com.sun.istack.NotNull;
import com.switchfully.order.model.dto.CreateItemRequest;
import com.switchfully.order.model.dto.CreateItemResponse;
import com.switchfully.order.model.dto.UpdateItemRequest;
import com.switchfully.order.model.dto.UpdateItemResponse;
import com.switchfully.order.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/items")
public class ItemController {

    private final ItemService itemService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CreateItemResponse> createNewItem(@RequestBody @NotNull CreateItemRequest request,
                                                            @RequestHeader(value = "id") String adminId) {
        logger.info("createNewItem method is called in Item Controller. Admin Id: " + adminId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itemService.addItem(request, adminId));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UpdateItemResponse> updateItem(@RequestBody @NotNull UpdateItemRequest request,
                                                            @RequestHeader(value = "id") String adminId) {
        logger.info("updateItem method is called in Item Controller. Admin Id: " + adminId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itemService.updateItem(request, adminId));
    }
}
