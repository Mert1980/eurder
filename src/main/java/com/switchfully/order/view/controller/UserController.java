package com.switchfully.order.view.controller;

import com.switchfully.order.model.dto.CreateCustomerRequest;
import com.switchfully.order.model.dto.CreateCustomerResponse;
import com.switchfully.order.security.request.LoginRequest;
import com.switchfully.order.service.AuthService;
import com.switchfully.order.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "User register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreateCustomerResponse> createNewCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createCustomerAccount(createCustomerRequest));
    }

    @PostMapping("/login")
    @Operation(summary = "User login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest user) {
        return authService.login(user);
    }
}
