package com.endava.restraining.rest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.endava.restraining.dao.LocationDAO;
import com.endava.restraining.dao.UserDAO;
import com.endava.restraining.entity.LocationEntity;
import com.endava.restraining.entity.UserEntity;
import com.endava.restraining.entity.dto.LocationDto;
import com.endava.restraining.entity.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class LocationIntTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private LocationDAO locationDAO;

    @Test
    void testAddLocation() throws Exception {
        UserDto userDto = new UserDto(USERNAME, PASSWORD, ROLE_ADMIN);
        registrationController.registerUser(userDto);

        LocationDto location = new LocationDto();
        location.setCity("Chisinau");
        location.setCountry("Moldova");

        mockMvc.perform(post(LOCATION_ADD_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(location))
                        .header(HttpHeaders.AUTHORIZATION, BASIC_TOKEN))
                .andExpect(status().isOk());

        List<LocationEntity> locationEntity = locationDAO.findByCity(location.getCity());

        assertAll(
                () -> assertThat(locationEntity.get(0).getCity()).isEqualTo(location.getCity()),
                () -> assertThat(locationEntity.get(0).getCountry()).isEqualTo(location.getCountry())
        );
    }

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password123";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String BASIC_TOKEN = "Basic dXNlcm5hbWU6cGFzc3dvcmQxMjM=";
    private static final String LOCATION_URL = "/location";
    private static final String LOCATION_ADD_URL = LOCATION_URL + "/add";


}