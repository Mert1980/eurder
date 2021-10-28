package com.switchfully.order.service.mapper;

import com.switchfully.order.model.dto.CreateOrderRequest;
import com.switchfully.order.model.dto.CreateOrderResponse;
import com.switchfully.order.model.dto.CreateItemGroupRequest;
import com.switchfully.order.model.dto.CreateItemGroupResponse;
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

    public Order toOrder(List<CreateItemGroupRequest> createItemGroupRequests) {

        Order order = new Order();
        createItemGroupRequests
                .forEach(request -> order.getItemGroups().put(request.getItemId(), request.getAmountOfItemsOrdered()));

        return order;
    }

    public CreateOrderResponse toCreateOrderResponse(Order order) {
        List<CreateItemGroupResponse> createItemGroupResponseList = order.getItemGroups().entrySet().stream()
                .map(entry -> new CreateItemGroupRequest(entry.getKey(), entry.getValue()))
                .map(this::toItemGroupResponse)
                .collect(Collectors.toList());

        return CreateOrderResponse.builder()
                .id(order.getId())
                .itemGroups(createItemGroupResponseList)
                .customerId(order.getCustomer().getId())
                .price(order.getTotalPrice())
                .build();
    }

    private CreateItemGroupResponse toItemGroupResponse(CreateItemGroupRequest createItemGroupRequest) {
        return CreateItemGroupResponse.builder()
                .itemId(createItemGroupRequest.getItemId())
                .amount(createItemGroupRequest.getAmountOfItemsOrdered())
                .shippingDate(itemService.isStockAvailable(createItemGroupRequest) ? LocalDate.now().plusDays(1) : LocalDate.now().plusDays(7))
                .build();
    }
}
