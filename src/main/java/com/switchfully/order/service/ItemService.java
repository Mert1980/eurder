package com.switchfully.order.service;

import com.switchfully.order.model.dto.CreateItemRequest;
import com.switchfully.order.model.dto.CreateItemResponse;
import com.switchfully.order.model.dto.CreateItemGroupRequest;
import com.switchfully.order.model.entity.item.Item;
import com.switchfully.order.repository.ItemRepository;
import com.switchfully.order.service.mapper.ItemMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemService {

    final ItemRepository itemRepository;
    final ItemMapper itemMapper;
    final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public CreateItemResponse addItem(CreateItemRequest createItemRequest){
        Item newItem = itemMapper.toItem(createItemRequest);
        itemRepository.addItem(newItem);
        logger.info("New item is created. Item ID: " + newItem.getId());
        return itemMapper.toItemResponse(newItem);
    }

    public boolean isStockAvailable(CreateItemGroupRequest createItemGroupRequest) {
        return itemRepository.getItems().values().stream()
                .filter(itemInStock -> itemInStock.getId().equals(createItemGroupRequest.getItemId()))
                .allMatch(item -> item.getAmount() >= createItemGroupRequest.getAmount());
    }

    public Item getItemById(String itemId){
        return itemRepository.getItemById(itemId);
    }
}
