package com.switchfully.order.view.controller;

import com.switchfully.order.model.dto.*;
import com.switchfully.order.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Order Controller Test")
class OrderControllerTest {

    private final OrderService orderService;
    private static final String DEFAULT_CUSTOMER_ID = "85acdc9b-13a3-412f-94de-77f26fcf4f9c";

    @Autowired
    OrderControllerTest(OrderService orderService) {
        this.orderService = orderService;
    }

   /* @Test
    void givenCreateOrderRequest_whenPostRequestWithCustomerRole_ThenReturnCreateOrderResponse() {
        // GIVEN
        List<ItemGroupRequest> itemGroupRequests = List.of(new ItemGroupRequest("d47ffb0f-7779-43ca-8606-f5d6c7097f1d", 2),
                new ItemGroupRequest("d47ffb0f-7779-43ca-8606-f5d6c7097f1e", 1));

        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .itemGroups(itemGroupRequests)
                .build();

        List<ItemGroupResponse> expectedItemGroupResponse = List.of(
                new ItemGroupResponse("d47ffb0f-7779-43ca-8606-f5d6c7097f1d", 2, LocalDate.now().plusDays(1)),
                new ItemGroupResponse("d47ffb0f-7779-43ca-8606-f5d6c7097f1e", 1, LocalDate.now().plusDays(1)));

        //WHEN
        CreateOrderResponse actual = orderService.createOrder(createOrderRequest, DEFAULT_CUSTOMER_ID);
        System.out.println("length of actual CreateOrderResponse: " + actual.getItemGroupResponseList().size());

        //THEN
        assertNotNull(actual.getId());
        assertNotNull(actual.getCustomerId());
        assertThat(expectedItemGroupResponse).containsExactlyInAnyOrderElementsOf(actual.getItemGroupResponseList());
        assertEquals(401.5, actual.getPrice().getAmount().doubleValue());

    }*/
}