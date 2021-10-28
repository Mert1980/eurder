package com.switchfully.order.model.dto;

import com.switchfully.order.model.entity.item.Price;
import com.switchfully.order.model.entity.item.UrgencyIndicator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateItemRequest {
    String name;
    String description;
    Price price;
    UrgencyIndicator urgencyIndicator;
    int amount;
}
