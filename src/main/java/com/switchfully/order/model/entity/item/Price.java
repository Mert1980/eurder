package com.switchfully.order.model.entity.item;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price {
    Currency currency;
    BigDecimal amount;
}
