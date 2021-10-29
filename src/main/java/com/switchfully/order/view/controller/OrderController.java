package com.switchfully.order.view.controller;

import com.sun.istack.NotNull;
import com.switchfully.order.exception.AuthorizationException;
import com.switchfully.order.model.dto.*;
import com.switchfully.order.model.entity.user.UserRole;
import com.switchfully.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
public class OrderController {

    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<CreateOrderResponse> createNewOrder(@RequestBody @NotNull List<CreateItemGroupRequest> request,
                                                             @RequestHeader(value = "id") String userId) {
        logger.info("createNewOrder method is called in Customer Controller. User Id: " + userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(request, userId));
    }
}


