package com.switchfully.order.repository;

import com.switchfully.order.model.entity.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRepository {

    final HashMap<String, Item> items;

    public ItemRepository() {
        items = new HashMap<>();
    }

    public Item addItem(Item item){
        Item newItem = items.put(item.getId(), item);
        return newItem;
    }
}
