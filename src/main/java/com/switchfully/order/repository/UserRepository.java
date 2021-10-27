package com.switchfully.order.repository;

import com.switchfully.order.model.dto.CreateCustomerRequest;
import com.switchfully.order.model.entity.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
    }

    public User createCustomerAccount(User user){
        User newUser = users.put(user.getId(), user);
        return newUser;
    }
}
