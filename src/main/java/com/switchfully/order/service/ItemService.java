package com.switchfully.order.service;

import com.switchfully.order.model.dto.*;
import com.switchfully.order.model.entity.item.Item;
import com.switchfully.order.repository.ItemRepository;
import com.switchfully.order.service.mapper.ItemMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemService {

    final ItemRepository itemRepository;
    final UserService userService;
    final ItemMapper itemMapper;
    final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public CreateItemResponse addItem(CreateItemRequest createItemRequest, String adminId){
        userService.assertAuthorizedAdmin(adminId);

        Item newItem = itemMapper.toItem(createItemRequest);
        itemRepository.addItem(newItem);
        logger.info("New item is created. Item ID: " + newItem.getId());
        return itemMapper.toCreateItemResponse(newItem);
    }

    public UpdateItemResponse updateItem(UpdateItemRequest updateItemRequest, String adminId){
        userService.assertAuthorizedAdmin(adminId);

        Item itemToBeUpdated = itemRepository.getItemById(updateItemRequest.getId());
        Item updatedItem = itemMapper.toItem(itemToBeUpdated, updateItemRequest);
        itemRepository.addItem(updatedItem);
        return itemMapper.toUpdateItemResponse(updatedItem);
    }

    public boolean isStockAvailable(CreateItemGroupRequest createItemGroupRequest) {
        return itemRepository.getItems().values().stream()
                .filter(itemInStock -> itemInStock.getId().equals(createItemGroupRequest.getItemId()))
                .allMatch(item -> item.getAmount() >= createItemGroupRequest.getAmount());
    }

    public void adjustAmountOfItemsInStock(HashMap<String, Integer> orderedItemGroups){
        orderedItemGroups.forEach((key, value) -> itemRepository.adjustAmountOfItemInStock(key, value));
    }

    public Item getItemById(String itemId){
        return itemRepository.getItemById(itemId);
    }
}
