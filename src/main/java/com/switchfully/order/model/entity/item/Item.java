package com.switchfully.order.model.entity.item;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
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
