package com.switchfully.order.view.controller;

import com.sun.istack.NotNull;
import com.switchfully.order.exception.AuthorizationException;
import com.switchfully.order.model.dto.CreateItemRequest;
import com.switchfully.order.model.dto.CreateItemResponse;
import com.switchfully.order.model.entity.user.UserRole;
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

    @PostMapping
    public ResponseEntity<CreateItemResponse> createNewItem(@RequestBody @NotNull CreateItemRequest request,
                                                            @RequestHeader(value = "role") String userRole) {

        if (!userRole.equalsIgnoreCase(UserRole.ADMIN.name())) {
            logger.error("Unauthorized request to create a new item.");
            throw new AuthorizationException("You are not authorized as admin");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(itemService.addItem(request));
    }
}
