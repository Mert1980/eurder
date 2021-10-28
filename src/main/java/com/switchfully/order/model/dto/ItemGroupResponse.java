package com.switchfully.order.model.dto;

import com.switchfully.order.model.entity.item.Price;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemGroupResponse {

    String itemId;
    int amount;
    LocalDate shippingDate;

}
