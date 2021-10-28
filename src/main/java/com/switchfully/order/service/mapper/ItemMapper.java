package com.switchfully.order.service.mapper;

import com.switchfully.order.model.dto.CreateItemRequest;
import com.switchfully.order.model.dto.CreateItemResponse;
import com.switchfully.order.model.entity.item.Item;
import com.switchfully.order.model.entity.item.Price;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ItemMapper {

    public Item toItem(CreateItemRequest createItemRequest){
        return Item.builder()
                .id(UUID.randomUUID().toString())
                .name(createItemRequest.getName())
                .description(createItemRequest.getDescription())
                .price(new Price(createItemRequest.getCurrency(), BigDecimal.valueOf(createItemRequest.getPrice())))
                .amount(createItemRequest.getAmount())
                .urgencyIndicator(createItemRequest.getUrgencyIndicator())
                .build();
    }

    public CreateItemResponse toItemResponse(Item item){
        return CreateItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .amount(item.getAmount())
                .build();
    }
}
