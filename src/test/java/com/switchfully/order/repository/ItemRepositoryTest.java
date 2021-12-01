package com.switchfully.order.repository;

import com.switchfully.order.exception.NotAvailableStockException;
import com.switchfully.order.model.entity.item.Currency;
import com.switchfully.order.model.entity.item.Item;
import com.switchfully.order.model.entity.item.Price;
import com.switchfully.order.model.entity.item.UrgencyIndicator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Items Repository Test")
class ItemRepositoryTest {

    private final ItemRepository itemRepository;


    ItemRepositoryTest(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @AfterEach
    void afterEach(){
        itemRepository.getAllItems().put("d47ffb0f-7779-43ca-8606-f5d6c7097f1d",
                new Item("d47ffb0f-7779-43ca-8606-f5d6c7097f1d", "Grill", "Multi Functional",
                        new Price(Currency.EUR, BigDecimal.valueOf(100.5)), 2, UrgencyIndicator.STOCK_HIGH));
    }


    void given_Two_ItemsInStock_when_One_IsOrdered_ThenRemainingAmountInStockIs_One() {
        //GIVEN
        String itemIdOrdered = "d47ffb0f-7779-43ca-8606-f5d6c7097f1d";
        int amountOrdered = 1;

        //WHEN
        itemRepository.adjustAmountOfItemInStock(itemIdOrdered, amountOrdered);
        int amountOfItemInStockAfterOrder = itemRepository.getItemById(itemIdOrdered).getAmount();

        //THEN
        assertThat(amountOfItemInStockAfterOrder).isEqualTo(1);
    }


    void given_Two_ItemsInStock_when_Two_IsOrdered_ThenRemainingAmountInStockIs_Zero() {
        //GIVEN
        String itemId = "d47ffb0f-7779-43ca-8606-f5d6c7097f1d";
        int amountOrdered = 2;

        //WHEN
        itemRepository.adjustAmountOfItemInStock(itemId, amountOrdered);
        int amountOfItemInStockAfterOrder = itemRepository.getItemById(itemId).getAmount();

        //THEN
        assertThat(amountOfItemInStockAfterOrder).isEqualTo(0);
    }


    void given_Two_ItemsInStock_when_Three_IsOrdered_ThenThrowsNotAvailableStockException() {
        //GIVEN
        String itemId = "d47ffb0f-7779-43ca-8606-f5d6c7097f1d";
        int amountOrdered = 3;

        //WHEN
        NotAvailableStockException exception = assertThrows(NotAvailableStockException.class,
                ()-> itemRepository.adjustAmountOfItemInStock(itemId,amountOrdered));

        //THEN
        assertEquals("Grill is not available in stock. Requested:3 Available:2", exception.getMessage());
    }
}