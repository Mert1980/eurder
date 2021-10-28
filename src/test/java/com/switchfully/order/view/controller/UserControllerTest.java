package com.switchfully.order.view.controller;

import com.switchfully.order.exception.AuthorizationException;
import com.switchfully.order.model.dto.CreateCustomerRequest;
import com.switchfully.order.model.dto.CreateCustomerResponse;
import com.switchfully.order.model.entity.user.Address;
import com.switchfully.order.model.entity.user.City;
import com.switchfully.order.model.entity.user.Phone;
import com.switchfully.order.model.entity.user.UserRole;
import com.switchfully.order.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("User Controller Test")
class UserControllerTest {

    private final UserService userService;

    @Autowired
    UserControllerTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    void givenCreateCustomerDto_whenPostRequestWithCustomerRole_ThenReturnCreateCustomerResponse() {
        // GIVEN
        CreateCustomerRequest createCustomerRequest = CreateCustomerRequest.builder()
                .firstName("Mert")
                .lastName("Demirok")
                .email("mert@gmail.com")
                .address(new Address("Street", "10", new City(3000, "Leuven")))
                .phone(new Phone(32, 1234567))
                .role(UserRole.CUSTOMER)
                .build();

        //WHEN
        CreateCustomerResponse actual = userService.createCustomerAccount(createCustomerRequest);

        //THEN
        assertNotNull(actual.getId());
        assertEquals("Mert", actual.getFirstName());
        assertEquals("Demirok", actual.getLastName());
        assertEquals("mert@gmail.com", actual.getEmail());
        assertEquals("Street", actual.getAddress().getStreet());
        assertEquals("10", actual.getAddress().getHouseNumber());
        assertEquals(3000, actual.getAddress().getCity().getPostalCode());
        assertEquals("Leuven", actual.getAddress().getCity().getName());
        assertEquals(32, actual.getPhone().getCountryCode());
        assertEquals(1234567,actual.getPhone().getNationalNumber());
    }

    @Test
    void givenCreateCustomerDto_whenPostRequestWithAdminRole_ThenThrowAuthorizationException() {
        // GIVEN
        CreateCustomerRequest createCustomerRequest = CreateCustomerRequest.builder()
                .firstName("Mert")
                .lastName("Demirok")
                .email("mert@gmail.com")
                .address(new Address("Street", "10", new City(3000, "Leuven")))
                .phone(new Phone(32, 1234567))
                .role(UserRole.ADMIN)
                .build();

        //THEN
        assertThrows(AuthorizationException.class, () -> userService.createCustomerAccount(createCustomerRequest));
    }
}