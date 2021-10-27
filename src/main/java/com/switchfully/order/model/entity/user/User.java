package com.switchfully.order.model.entity.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })
public class User {
    String id;
    String firstName;
    String lastName;
    String email;
    Address address;
    Phone phone;
    UserRole role;
}
