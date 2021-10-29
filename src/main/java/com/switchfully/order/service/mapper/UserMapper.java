package com.switchfully.order.service.mapper;

import com.switchfully.order.model.dto.CreateCustomerRequest;
import com.switchfully.order.model.dto.CreateCustomerResponse;
import com.switchfully.order.model.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toUser(CreateCustomerRequest createCustomerRequest){
        return User.builder()
                .id(UUID.randomUUID().toString())
                .password(passwordEncoder.encode(createCustomerRequest.getPassword()))
                .firstName(createCustomerRequest.getFirstName())
                .lastName(createCustomerRequest.getLastName())
                .email(createCustomerRequest.getEmail())
                .address(createCustomerRequest.getAddress())
                .phone(createCustomerRequest.getPhone())
                .role(createCustomerRequest.getRole())
                .build();
    }

    public CreateCustomerResponse toCreateCustomerResponse(User user){
        return CreateCustomerResponse.builder()
                .id(UUID.randomUUID().toString())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }

}
