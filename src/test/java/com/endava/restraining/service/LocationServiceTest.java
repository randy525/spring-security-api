package com.endava.restraining.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.endava.restraining.dao.LocationDAO;
import com.endava.restraining.entity.LocationEntity;
import com.endava.restraining.entity.dto.LocationDto;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private LocationDAO locationDAO;

    @InjectMocks
    private LocationService locationService;

    @Captor
    private ArgumentCaptor<LocationEntity> argumentCaptor;

    @Test
    void whenAddNewLocation_ThenIsInsertedInTheDatabase() {
        LocationDto locationDto = new LocationDto();
        locationDto.setCity("Chisinau");
        locationDto.setCountry("Moldova");

        locationService.add(locationDto);

        verify(locationDAO).save(argumentCaptor.capture());
        LocationEntity locationEntity = argumentCaptor.getValue();
        assertThat(locationEntity.getCity()).isEqualTo(locationDto.getCity());
        assertThat(locationEntity.getCountry()).isEqualTo(locationDto.getCountry());
    }

    @Test
    void whenUpdate_ThenDataIsSavedInDB() {
        long id = 123;
        LocationDto locationDto = new LocationDto();
        locationDto.setCity("Chisinau");
        locationDto.setCountry("Moldova");
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(id);
        locationEntity.setCity("Chisinau");
        locationEntity.setCountry("Moldova");

        when(locationDAO.getById(any())).thenReturn(locationEntity);

        locationService.update(id, locationDto);

        verify(locationDAO).save(argumentCaptor.capture());
        LocationEntity capturedLocationEntity = argumentCaptor.getValue();
        assertThat(locationEntity).isEqualTo(capturedLocationEntity);
    }

    @ParameterizedTest
    @NullSource
    @CsvSource("5")
    void testGetAll_success(Long limit) {
        //prerequisits
        // Long limit = 5L;
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setId(123L);
        locationEntity.setCity("Chisinau");
        locationEntity.setCountry("Moldova");
        LocationDto locationDto = new LocationDto();
        locationDto.setId(123L);
        locationDto.setCity("Chisinau");
        locationDto.setCountry("Moldova");
        List<LocationEntity> list = List.of(locationEntity, locationEntity, locationEntity, locationEntity, locationEntity,locationEntity);
        List<LocationDto> expected = List.of(locationDto, locationDto, locationDto, locationDto, locationDto);

        //when
        when(locationDAO.findAll()).thenReturn(list);

        //call to method
        List<LocationDto> actual = locationService.getAll(limit);

        //assertions
        verify(locationDAO).findAll();
        assertEquals(expected, actual);
        assertThat(expected).hasSize(actual.size());
    }

    public List<LocationDto> getAll(Long limit) {
        List<LocationDto> locations = new ArrayList<>();
        List<LocationEntity> daoLocations = locationDAO.findAll();
        if (limit != null) {
            for (int i=0; i<limit; i++) {
                LocationDto location = new LocationDto();
                location.setId(daoLocations.get(i).getId());
                location.setCity(daoLocations.get(i).getCity());
                location.setCountry(daoLocations.get(i).getCountry());
                locations.add(location);
                if (i == daoLocations.size()-1) {
                    break;
                }
            }
        } else {
            for (LocationEntity daoLocation : daoLocations) {
                LocationDto location = new LocationDto();
                location.setId(daoLocation.getId());
                location.setCountry(daoLocation.getCountry());
                location.setCity(daoLocation.getCity());
                locations.add(location);
            }
        }
        return locations;
    }

}