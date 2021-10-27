package com.switchfully.order.model.entity;

import com.switchfully.order.model.entity.item.Price;
import com.switchfully.order.model.entity.user.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })
public class Order {

    String id = UUID.randomUUID().toString();
    HashMap<String, Integer> amountOfItemsByItemId = new HashMap<>();
    Price totalPrice;
    User customer;
    LocalDate shippingDate;
}
