package com.switchfully.order.service;

import com.switchfully.order.model.dto.CreateItemRequest;
import com.switchfully.order.model.dto.CreateItemResponse;
import com.switchfully.order.model.entity.item.Item;
import com.switchfully.order.repository.ItemRepository;
import com.switchfully.order.service.mapper.ItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public CreateItemResponse addItem(CreateItemRequest createItemRequest){
        Item newItem = itemMapper.toItem(createItemRequest);
        itemRepository.addItem(newItem);
        logger.info("New item is created. Item ID: " + newItem.getId());
        return itemMapper.toItemResponse(newItem);
    }
}
