package com.endava.restraining.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.endava.restraining.dao.UserDAO;
import com.endava.restraining.entity.UserEntity;
import com.endava.restraining.entity.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDAO userDAO;

    @Test
    void testUserIsRegistered() throws Exception {
        UserDto userDto = new UserDto(USERNAME, PASSWORD, ROLE_ADMIN);
        mockMvc.perform(post(REGISTER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

        UserEntity userEntity = userDAO.findByUsername(USERNAME);

        assertAll(
                () -> assertThat(userEntity.getUsername()).isEqualTo(userDto.getUsername()),
                () -> assertThat(userEntity.getPassword()).isNotEqualToIgnoringCase(userDto.getPassword()),
                () -> assertThat(userEntity.getRole()).isEqualTo(userDto.getRole())
        );
    }

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password123";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String REGISTER_URL = "/register";

}