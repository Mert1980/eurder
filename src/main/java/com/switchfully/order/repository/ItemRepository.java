package com.switchfully.order.repository;

import com.switchfully.order.exception.NotAvailableStockException;
import com.switchfully.order.model.entity.item.Currency;
import com.switchfully.order.model.entity.item.Item;
import com.switchfully.order.model.entity.item.Price;
import com.switchfully.order.model.entity.item.UrgencyIndicator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRepository {

    final HashMap<String, Item> items;

    public ItemRepository() {
        items = new HashMap<>();
        items.put("d47ffb0f-7779-43ca-8606-f5d6c7097f1d",
                new Item("d47ffb0f-7779-43ca-8606-f5d6c7097f1d", "Grill", "Multi Functional",
                        new Price(Currency.EUR, BigDecimal.valueOf(100.5)), 4, UrgencyIndicator.STOCK_HIGH));
        items.put("d47ffb0f-7779-43ca-8606-f5d6c7097f1e",
                new Item("d47ffb0f-7779-43ca-8606-f5d6c7097f1e", "Microwave", "Black",
                        new Price(Currency.EUR, BigDecimal.valueOf(200.5)), 2, UrgencyIndicator.STOCK_LOW));
    }

    public HashMap<String, Item> getAllItems(){
        return items;
    }

    public Item addItem(Item item){
        Item newItem = items.put(item.getId(), item);
        return newItem;
    }

    public Item getItemById(String itemId) {
        return items.get(itemId);
    }

    public void adjustAmountOfItemInStock(String itemId, int amount){
        assertAvailableStockLevel(itemId, amount);

        getItemById(itemId).setAmount(getItemById(itemId).getAmount() - amount);
    }

    private void assertAvailableStockLevel(String itemId, int amount) {
        if(getItemById(itemId).getAmount() < amount){
            throw new NotAvailableStockException(getItemById(itemId).getName() +
                    " is not available in stock. Requested:" + amount +
                    " Available:" + getItemById(itemId).getAmount());
        }
    }
}
