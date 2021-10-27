package com.switchfully.order.model.dto;

import com.switchfully.order.model.entity.user.Address;
import com.switchfully.order.model.entity.user.Phone;
import com.switchfully.order.model.entity.user.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
public class CreateCustomerRequest {

    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Phone phone;
    private UserRole role;
}
