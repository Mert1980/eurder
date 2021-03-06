package com.switchfully.order.service;

import com.switchfully.order.model.dto.*;
import com.switchfully.order.model.entity.item.Currency;
import com.switchfully.order.model.entity.item.Item;
import com.switchfully.order.model.entity.item.Price;
import com.switchfully.order.model.entity.item.UrgencyIndicator;
import com.switchfully.order.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Order Service Test")
class OrderServiceTest {

    private final OrderService orderService;
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private List<CreateItemGroupRequest> itemGroupRequests;
    private static final String DEFAULT_CUSTOMER_ID = "85acdc9b-13a3-412f-94de-77f26fcf4f9c";


    OrderServiceTest(OrderService orderService, ItemService itemService, ItemRepository itemRepository) {
        this.orderService = orderService;
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }


    void beforeEach(){
        itemGroupRequests = List.of(new CreateItemGroupRequest("d47ffb0f-7779-43ca-8606-f5d6c7097f1d", 2),
                new CreateItemGroupRequest("d47ffb0f-7779-43ca-8606-f5d6c7097f1e", 1));
    }


    void afterEach(){
        itemRepository.getAllItems().put("d47ffb0f-7779-43ca-8606-f5d6c7097f1d",
                new Item("d47ffb0f-7779-43ca-8606-f5d6c7097f1d", "Grill", "Multi Functional",
                        new Price(Currency.EUR, BigDecimal.valueOf(100.5)), 2, UrgencyIndicator.STOCK_HIGH));
        itemRepository.getAllItems().put("d47ffb0f-7779-43ca-8606-f5d6c7097f1e",
                new Item("d47ffb0f-7779-43ca-8606-f5d6c7097f1e", "Microwave", "Black",
                        new Price(Currency.EUR, BigDecimal.valueOf(200.5)), 1, UrgencyIndicator.STOCK_LOW));
    }


    @DisplayName("when create order request, then returned order should contain orderID, customerId, item groups and total price")
    void givenListOfItemGroups_whenCreateOrder_ThenReturnCreatedOrder() {
        //GIVEN
        List<CreateItemGroupResponse> expectedItemGroupResponse = List.of(
                new CreateItemGroupResponse("d47ffb0f-7779-43ca-8606-f5d6c7097f1d", 2, LocalDate.now().plusDays(1)),
                new CreateItemGroupResponse("d47ffb0f-7779-43ca-8606-f5d6c7097f1e", 1, LocalDate.now().plusDays(1)));
        //WHEN
        CreateOrderResponse actual = orderService.createOrder(itemGroupRequests, DEFAULT_CUSTOMER_ID);

        //THEN
        assertNotNull(actual.getId());
        assertNotNull(actual.getCustomerId());
        assertThat(expectedItemGroupResponse).containsExactlyInAnyOrderElementsOf(actual.getItemGroups());
        assertEquals(401.5, actual.getPrice().getAmount().doubleValue());
    }


    @DisplayName("when create order request, then the amount of items in stock is updated")
    void givenListOfItemGroups_whenCreateOrder_ThenStockOfOrderedItemsUpdated() {
        //WHEN
        orderService.createOrder(itemGroupRequests, DEFAULT_CUSTOMER_ID);

        //THEN
        assertEquals(0, itemService.getItemById("d47ffb0f-7779-43ca-8606-f5d6c7097f1d").getAmount());
        assertEquals(0, itemService.getItemById("d47ffb0f-7779-43ca-8606-f5d6c7097f1e").getAmount());
    }


    @DisplayName("when create order with zero amount of items, then throws IllegalArgumentException")
    void given_Two_ItemsInStock_when_Zero_IsOrdered_ThenThrowsIllegalArgumentException() {
        //GIVEN
        itemGroupRequests.forEach(request -> request.setAmount(0));

        //WHEN
        Exception exception = assertThrows(IllegalArgumentException.class,
                ()-> orderService.createOrder(itemGroupRequests, DEFAULT_CUSTOMER_ID));
        String expectedMessage = "Requested amount should not be 0 or smaller than 0. Requested:0";
        String actualMessage = exception.getMessage();

        //THEN
        assertTrue(actualMessage.contains(expectedMessage));
    }
}