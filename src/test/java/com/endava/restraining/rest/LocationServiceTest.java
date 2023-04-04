package com.endava.restraining.rest;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.endava.restraining.dao.LocationDAO;
import com.endava.restraining.entity.LocationEntity;
import com.endava.restraining.entity.dto.LocationDto;
import com.endava.restraining.service.LocationService;

class LocationServiceTest {

    LocationDAO locationDAO = mock(LocationDAO.class);

    LocationService locationService = new LocationService(locationDAO);

    @Test
    void update() {
        final long id = 123;
        LocationDto locationDto = new LocationDto();
        locationDto.setCountry(ANOTHER_COUNTRY);
        locationDto.setCity(ANOTHER_CITY);
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setCity(CITY);
        locationEntity.setCountry(COUNTRY);
        locationEntity.setId(id);
        ArgumentCaptor<LocationEntity> argumentCaptor = ArgumentCaptor.forClass(LocationEntity.class);
        when(locationDAO.getById(id)).thenReturn(locationEntity);

        locationService.update(id, locationDto);

        verify(locationDAO).save(argumentCaptor.capture());
        LocationEntity updatedLocationEntity = argumentCaptor.getValue();
        assertThat(updatedLocationEntity.getCountry()).isEqualTo(locationDto.getCountry());
        assertThat(updatedLocationEntity.getCity()).isEqualTo(locationDto.getCity());
        assertThat(updatedLocationEntity.getId()).isEqualTo(id);
        assertThat(updatedLocationEntity).isEqualTo(locationEntity);
    }

    private static final String CITY = "Chisinau";
    private static final String COUNTRY = "Moldova";
    private static final String ANOTHER_CITY = "London";
    private static final String ANOTHER_COUNTRY = "England";

}
