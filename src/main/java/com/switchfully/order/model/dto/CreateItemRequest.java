package com.switchfully.order.model.dto;

import com.switchfully.order.model.entity.item.Currency;
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
    Currency currency;
    double price;
    int amount;
    UrgencyIndicator urgencyIndicator;
}
