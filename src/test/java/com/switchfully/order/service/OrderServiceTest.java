package com.switchfully.order.service;

import static org.junit.jupiter.api.Assertions.*;

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
@DisplayName("Order Service Test")
class OrderServiceTest {

    private final OrderService orderService;
    private static final String DEFAULT_CUSTOMER_ID = "85acdc9b-13a3-412f-94de-77f26fcf4f9c";

    @Autowired
    OrderServiceTest(OrderService orderService) {
        this.orderService = orderService;
    }

    @Test
    void givenCreateOrderRequest_whenPostRequestWithCustomerRole_ThenReturnCreateOrderResponse() {
        // GIVEN
        List<CreateItemGroupRequest> itemGroupRequests = List.of(new CreateItemGroupRequest("d47ffb0f-7779-43ca-8606-f5d6c7097f1d", 2),
                new CreateItemGroupRequest("d47ffb0f-7779-43ca-8606-f5d6c7097f1e", 1));

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        itemGroupRequests.forEach(request -> createOrderRequest.getItemGroups().add(request));


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
}