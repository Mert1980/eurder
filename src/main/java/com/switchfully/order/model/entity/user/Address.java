package com.switchfully.order.model.entity.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {
    String street;
    String houseNumber;
    City city;
}
