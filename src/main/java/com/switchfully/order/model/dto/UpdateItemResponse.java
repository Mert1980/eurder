package com.switchfully.order.model.dto;

import com.switchfully.order.model.entity.item.Price;
import com.switchfully.order.model.entity.item.UrgencyIndicator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateItemResponse {

    String id;
    String name;
    String description;
    Price price;
    int amount;
    UrgencyIndicator urgencyIndicator;
}
