package com.switchfully.order.model.dto;

import com.switchfully.order.model.entity.item.Price;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateItemRequest {

    String id;
    String name;
    String description;
    Price price;
    int amount;
}
