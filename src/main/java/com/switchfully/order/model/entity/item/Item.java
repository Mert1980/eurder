package com.switchfully.order.model.entity.item;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })
public class Item {
    String id = UUID.randomUUID().toString();
    String name;
    String description;
    Price price;
    UrgencyIndicator urgencyIndicator;
}
