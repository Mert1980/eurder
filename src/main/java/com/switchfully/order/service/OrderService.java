package com.switchfully.order.service;

import com.switchfully.order.model.dto.CreateItemGroupRequest;
import com.switchfully.order.model.dto.CreateOrderResponse;
import com.switchfully.order.model.entity.Order;
import com.switchfully.order.model.entity.item.Currency;
import com.switchfully.order.model.entity.item.Price;
import com.switchfully.order.repository.OrderRepository;
import com.switchfully.order.service.mapper.OrderMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderService {

    final OrderMapper orderMapper;
    final OrderRepository orderRepository;
    final UserService userService;
    final ItemService itemService;
    final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public CreateOrderResponse createOrder(List<CreateItemGroupRequest> createItemGroupRequests, String userId){
        userService.assertAuthorizedCustomer(userId);

        Order newOrder = orderMapper.toOrder(createItemGroupRequests);

        newOrder.setCustomer(userService.getCustomer(userId));
        newOrder.setTotalPrice(calculateTotalPrice(createItemGroupRequests));

        logger.debug("Created order: " + newOrder);

        orderRepository.createOrder(newOrder);
        logger.info("New order created. Order Id: " + newOrder.getId());

        CreateOrderResponse orderResponse = orderMapper.toCreateOrderResponse(newOrder);
        itemService.adjustAmountOfItemsInStock(newOrder.getItemGroups());

        return orderResponse;
    }

    private Price calculateTotalPrice(List<CreateItemGroupRequest> createItemGroupRequests) {

       double totalPrice = createItemGroupRequests.stream()
                    .mapToDouble(itemGroup -> itemService.getItemById(itemGroup.getItemId()).getPrice().getAmount().doubleValue() * itemGroup.getAmount())
                    .sum();

        return new Price(Currency.EUR, BigDecimal.valueOf(totalPrice));
    }
}
