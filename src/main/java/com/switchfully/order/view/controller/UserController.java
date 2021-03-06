package com.switchfully.order.view.controller;

import com.switchfully.order.model.dto.CreateCustomerRequest;
import com.switchfully.order.model.dto.CreateCustomerResponse;
import com.switchfully.order.model.entity.user.User;
import com.switchfully.order.security.request.LoginRequest;
import com.switchfully.order.service.AuthService;
import com.switchfully.order.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "User register")
    public ResponseEntity<CreateCustomerResponse> createNewCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createCustomerAccount(createCustomerRequest));
    }

    @PostMapping("/login")
    @Operation(summary = "User login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest user) {
        return authService.login(user);
    }

    @GetMapping
    @Operation(summary = "Find all customers")
    public ResponseEntity<List<CreateCustomerResponse>> getAllCustomers(@RequestHeader(value = "id") String adminId){
        List<CreateCustomerResponse> users = userService.getAllCustomers(adminId);

        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find single customer")
    public ResponseEntity<CreateCustomerResponse> getCustomer(
            @PathVariable(value = "id") String customerId,
            @RequestHeader(value = "adminId") String adminId){

        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.getCustomerResponse(customerId, adminId));
    }
}
