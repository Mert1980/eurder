package com.switchfully.order.service;

import com.switchfully.order.model.dto.CreateOrderRequest;
import com.switchfully.order.model.dto.CreateOrderResponse;
import com.switchfully.order.model.entity.Order;
import com.switchfully.order.repository.OrderRepository;
import com.switchfully.order.service.mapper.OrderMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderService {

    final OrderMapper orderMapper;
    final OrderRepository orderRepository;
    final UserService userService;
    final ItemService itemService;
    final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest, String userId){
        userService.assertAuthorizedCustomer(userId);

        Order newOrder = orderMapper.toOrder(createOrderRequest);
        newOrder.setCustomer(userService.getCustomer(userId));

        orderRepository.createOrder(newOrder);
        return orderMapper.toCreateOrderResponse(newOrder);
    }



}
