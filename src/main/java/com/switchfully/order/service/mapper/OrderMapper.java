package com.switchfully.order.service.mapper;

import com.switchfully.order.model.dto.*;
import com.switchfully.order.model.entity.Order;
import com.switchfully.order.model.entity.item.Currency;
import com.switchfully.order.model.entity.item.Price;
import com.switchfully.order.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
                .forEach(request -> order.getItemGroups().put(request.getItemId(), request.getAmount()));

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
                .amount(createItemGroupRequest.getAmount())
                .shippingDate(itemService.isStockAvailable(createItemGroupRequest) ? LocalDate.now().plusDays(1) : LocalDate.now().plusDays(7))
                .build();
    }

    public CreateReportResponse toCreateReportResponse(List<Order> orders) {
        List<CreateOrderReportResponse> orderReportList = new ArrayList<>();

        orders.forEach(order -> orderReportList.add(toCreateOrderReportResponse(order)));
        double totalPrice = orders.stream().mapToDouble(order -> order.getTotalPrice().getAmount().doubleValue()).sum();
        Currency currency = orders.stream().findFirst().get().getTotalPrice().getCurrency();

        return CreateReportResponse.builder()
                .orders(orderReportList)
                .totalPrice(new Price(currency, BigDecimal.valueOf(totalPrice)))
                .build();
    }

    private CreateOrderReportResponse toCreateOrderReportResponse(Order order) {
        List<CreateItemGroupReportResponse> itemGroupReportList = new ArrayList<>();
        order.getItemGroups().forEach
                ((itemId, amount) -> itemGroupReportList.add(toCreateItemGroupReportResponse(itemId, amount)));

        return CreateOrderReportResponse.builder()
                .id(order.getId())
                .itemGroups(itemGroupReportList)
                .totalPrice(order.getTotalPrice())
                .build();
    }

    private CreateItemGroupReportResponse toCreateItemGroupReportResponse(String itemId, Integer amount) {
        return CreateItemGroupReportResponse.builder()
                .itemName(itemService.getItemById(itemId).getName())
                .amountOfOrderedItems(amount)
                .totalPrice(new Price(itemService.getItemById(itemId).getPrice().getCurrency(),
                        BigDecimal.valueOf(itemService.getItemById(itemId).getPrice().getAmount().doubleValue() * amount)))
                .build();
    }
}
