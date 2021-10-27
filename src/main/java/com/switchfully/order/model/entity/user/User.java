package com.switchfully.order.model.entity.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })
public class User {
    String id = UUID.randomUUID().toString();
    String firstName;
    String lastName;
    String email;
    Address address;
    Phone phone;
    UserRole role;
}
