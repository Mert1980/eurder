package com.switchfully.order.view.controller;

import com.sun.istack.NotNull;
import com.switchfully.order.model.dto.*;
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
                                                             @RequestHeader(value = "id") String customerId) {
        logger.info("createNewOrder method is called in Order Controller. User Id: " + customerId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(request, customerId));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CreateReportResponse> viewReportOfOrders(@RequestHeader(value = "id") String customerId) {
        logger.info("viewReportOfOrders method is called in Order Controller. User Id: " + customerId);
        return orderService.createReport(customerId);
    }
}


