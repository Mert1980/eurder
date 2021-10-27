package com.switchfully.order.service;

import com.switchfully.order.model.dto.CreateCustomerRequest;
import com.switchfully.order.model.dto.CreateCustomerResponse;
import com.switchfully.order.model.entity.user.User;
import com.switchfully.order.model.entity.user.UserRole;
import com.switchfully.order.repository.UserRepository;
import com.switchfully.order.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserMapper userMapper;
    final UserRepository userRepository;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public CreateCustomerResponse createCustomerAccount(CreateCustomerRequest createCustomerRequest){
        User user = userMapper.toUser(createCustomerRequest);
        userRepository.createCustomerAccount(user);
        return userMapper.toCreateCustomerResponse(user);
    }
}
