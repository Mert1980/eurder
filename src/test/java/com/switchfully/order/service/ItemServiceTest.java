package com.switchfully.order.service;

import com.switchfully.order.model.dto.CreateItemRequest;
import com.switchfully.order.model.dto.CreateItemResponse;
import com.switchfully.order.model.entity.item.Currency;
import com.switchfully.order.model.entity.item.UrgencyIndicator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Item Service Test")
class ItemServiceTest {

    private final ItemService itemService;
    private static final String DEFAULT_ADMIN_ID = "85acdc9b-13a3-412f-94de-77f26fcf4f8f";


    ItemServiceTest(ItemService itemService) {
        this.itemService = itemService;
    }


    void givenCreateItemRequest_whenAddItem_ThenReturnCreateItemResponse() {
        // GIVEN
        CreateItemRequest createItemRequest = CreateItemRequest.builder()
                .name("Grill")
                .description("Multi functional grill")
                .currency(Currency.EUR)
                .price(150.99)
                .amount(2)
                .urgencyIndicator(UrgencyIndicator.STOCK_HIGH)
                .build();

        //WHEN
        CreateItemResponse actual = itemService.addItem(createItemRequest, DEFAULT_ADMIN_ID);

        //THEN
        assertNotNull(actual.getId());
        assertEquals("Grill", actual.getName());
        assertEquals("Multi functional grill", actual.getDescription());
        assertEquals("EUR", actual.getPrice().getCurrency().name());
        assertEquals(150.99, actual.getPrice().getAmount().doubleValue());
        assertEquals(2, actual.getAmount());

    }
}