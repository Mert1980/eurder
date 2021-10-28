package com.switchfully.order.model.entity.item;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })
public class Item {
    String id;
    String name;
    String description;
    Price price;
    int amount;
    UrgencyIndicator urgencyIndicator;
}
