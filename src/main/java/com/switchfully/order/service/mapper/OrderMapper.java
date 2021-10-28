package com.switchfully.order.service.mapper;

import com.switchfully.order.model.dto.CreateOrderRequest;
import com.switchfully.order.model.dto.CreateOrderResponse;
import com.switchfully.order.model.dto.ItemGroupRequest;
import com.switchfully.order.model.dto.ItemGroupResponse;
import com.switchfully.order.model.entity.Order;
import com.switchfully.order.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ItemService itemService;

    @Autowired
    public OrderMapper(ItemService itemService) {
        this.itemService = itemService;
    }

    public Order toOrder(CreateOrderRequest createOrderRequest) {

        Order order = new Order();
        createOrderRequest.getItemGroupRequestList().stream()
                .map(itemGroupRequest -> order.getAmountOfItemsByItemId()
                        .put(itemGroupRequest.getItemId(), itemGroupRequest.getAmountOfItemsOrdered()));
        return order;
    }

    public CreateOrderResponse toCreateOrderResponse(Order order) {
        List<ItemGroupResponse> itemGroupResponseList = order.getAmountOfItemsByItemId().entrySet().stream()
                .map(entry -> new ItemGroupRequest(entry.getKey(), entry.getValue()))
                .map(this::toItemGroupResponse)
                .collect(Collectors.toList());

        return CreateOrderResponse.builder()
                .id(order.getId())
                .itemGroupResponseList(itemGroupResponseList)
                .customerId(order.getCustomer().getId())
                .price(order.getTotalPrice())
                .build();

    }

    private ItemGroupResponse toItemGroupResponse(ItemGroupRequest itemGroupRequest) {
        return ItemGroupResponse.builder()
                .itemId(itemGroupRequest.getItemId())
                .amount(itemGroupRequest.getAmountOfItemsOrdered())
                .shippingDate(itemService.isStockAvailable(itemGroupRequest) ? LocalDate.now().plusDays(1) : LocalDate.now().plusDays(7))
                .build();
    }
}
