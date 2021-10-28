package com.switchfully.order.model.entity.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Phone {
    int countryCode;
    int nationalNumber;
}
