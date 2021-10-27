package com.switchfully.order.model.dto;

import com.switchfully.order.model.entity.user.Address;
import com.switchfully.order.model.entity.user.Phone;
import com.switchfully.order.model.entity.user.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCustomerResponse {

    String id;
    String firstName;
    String lastName;
    String email;
    Address address;
    Phone phone;
    UserRole role;
}
