package com.endava.restraining.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.endava.restraining.entity.dto.UserDto;
import com.endava.restraining.service.UserService;

class RegistrationControllerTest {

    private RegistrationController registrationController;

    private UserService userService = mock(UserService.class);

    @BeforeEach
    void setUp() {
        registrationController = new RegistrationController(userService);
    }


    @Test
    void registerUser() {
        UserDto userDto = new UserDto();

        ResponseEntity<Object> actualResult = registrationController.registerUser(userDto);

        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        verify(userService).registerUser(userDto);
        verifyNoMoreInteractions(userService);
    }

    @Test
    void registerUser_NegativeFlow() {
        doThrow(new RuntimeException()).when(userService).registerUser(any());

        ResponseEntity<Object> actualResult = registrationController.registerUser(null);

        assertEquals(HttpStatus.BAD_REQUEST, actualResult.getStatusCode());
        verify(userService).registerUser(null);
        verifyNoMoreInteractions(userService);
    }

}