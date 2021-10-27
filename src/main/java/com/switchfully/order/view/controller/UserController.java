package com.switchfully.order.view.controller;

import com.switchfully.order.model.dto.CreateCustomerRequest;
import com.switchfully.order.model.dto.CreateCustomerResponse;
import com.switchfully.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCustomerResponse createNewCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        return userService.createCustomerAccount(createCustomerRequest);
    }
}
