package com.switchfully.order.view.controller;

import com.sun.istack.NotNull;
import com.switchfully.order.exception.AuthorizationException;
import com.switchfully.order.model.dto.CreateItemRequest;
import com.switchfully.order.model.dto.CreateItemResponse;
import com.switchfully.order.model.dto.CreateOrderRequest;
import com.switchfully.order.model.dto.CreateOrderResponse;
import com.switchfully.order.model.entity.user.UserRole;
import com.switchfully.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createNewOrder(@RequestBody @NotNull CreateOrderRequest request,
                                                             @RequestHeader(value = "id") String userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(request, userId));
    }
}
