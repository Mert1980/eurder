package com.switchfully.order.service;

import com.switchfully.order.exception.AuthorizationException;
import com.switchfully.order.model.dto.CreateCustomerRequest;
import com.switchfully.order.model.dto.CreateCustomerResponse;
import com.switchfully.order.model.entity.user.User;
import com.switchfully.order.model.entity.user.UserRole;
import com.switchfully.order.repository.UserRepository;
import com.switchfully.order.service.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

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

    public User getCustomer(String customerId){
        return userRepository.getCustomer(customerId);
    }

    public void assertAuthorizedCustomer(String userId) {
        if(!userRepository.getCustomer(userId).getRole().name().equalsIgnoreCase("CUSTOMER")){
            logger.error("Unauthorized request to create a customer.");
            throw new AuthorizationException("You are not authorized as a customer.");
        }
    }

}
