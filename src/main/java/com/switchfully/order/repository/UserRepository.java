package com.switchfully.order.repository;

import com.switchfully.order.exception.AuthorizationException;
import com.switchfully.order.model.entity.user.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRepository {

    final HashMap<String, User> users;

    public UserRepository() {
        users = new HashMap<>();
        users.put("85acdc9b-13a3-412f-94de-77f26fcf4f9c",
                User.builder()
                        .id("85acdc9b-13a3-412f-94de-77f26fcf4f9c")
                        .firstName("Mert")
                        .lastName("Demirok")
                        .email("mert@gmail.com")
                        .address(new Address("Street", "10", new City(3000, "Leuven")))
                        .phone(new Phone(32, 1234567))
                        .role(UserRole.CUSTOMER)
                        .build());
    }

    public User createCustomerAccount(User user){
        User newUser = users.put(user.getId(), user);
        return newUser;
    }

    public User getCustomer(String customerId){
        if(!users.containsKey(customerId)) throw new AuthorizationException("You are not authorized for this operation.");
        return users.get(customerId);
    }
}
