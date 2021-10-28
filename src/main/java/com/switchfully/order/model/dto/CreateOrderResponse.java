package com.switchfully.order.model.dto;

import com.switchfully.order.model.entity.item.Price;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateOrderResponse {

    String id;
    List<CreateItemGroupResponse> itemGroups;
    String customerId;
    Price price;
}
