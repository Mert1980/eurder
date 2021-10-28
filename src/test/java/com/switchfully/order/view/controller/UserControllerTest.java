package com.switchfully.order.view.controller;

import com.switchfully.order.model.dto.CreateCustomerRequest;
import com.switchfully.order.model.dto.CreateCustomerResponse;
import com.switchfully.order.model.entity.user.Address;
import com.switchfully.order.model.entity.user.City;
import com.switchfully.order.model.entity.user.Phone;
import com.switchfully.order.model.entity.user.UserRole;
import com.switchfully.order.repository.UserRepository;
import com.switchfully.order.service.UserService;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("User Controller Test")
class UserControllerTest {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    UserControllerTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Test
    void givenCreateCustomerDto_whenPostRequest_ThenReturnCreateCustomerResponse(){
        // GIVEN
        CreateCustomerRequest createCustomerRequest = CreateCustomerRequest.builder()
                .firstName("Mert")
                .lastName("Demirok")
                .email("mert@gmail.com")
                .address(new Address("Street","10", new City(3000, "Leuven")))
                .phone(new Phone(32, 1234567))
                .role(UserRole.CUSTOMER)
                .build();

        //WHEN
        CreateCustomerResponse createCustomerResponse = userService.createCustomerAccount(createCustomerRequest);

        //THEN
        assertNotNull(createCustomerResponse.getId());
        assertEquals("Mert", createCustomerResponse.getFirstName());
        assertEquals("Demirok", createCustomerResponse.getLastName());
        assertEquals("mert@gmail.com", createCustomerResponse.getEmail());
        assertEquals("Street", createCustomerResponse.getAddress().getStreet());
        assertEquals("10", createCustomerResponse.getAddress().getHouseNumber());
        assertEquals(3000, createCustomerResponse.getAddress().getCity().getPostalCode());
        assertEquals("Leuven", createCustomerResponse.getAddress().getCity().getName());
        assertEquals(32, createCustomerResponse.getPhone().getCountryCode());
        assertEquals(1234567, createCustomerResponse.getPhone().getNationalNumber());
    }
}