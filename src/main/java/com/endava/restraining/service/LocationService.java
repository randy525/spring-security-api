package com.endava.restraining.service;

import com.endava.restraining.dao.LocationDAO;
import com.endava.restraining.entity.LocationEntity;
import com.endava.restraining.entity.dto.LocationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {
    
    private final LocationDAO locationDAO;
    
    public void add(LocationDto locationDto) {
        LocationEntity location = new LocationEntity();
        location.setCity(locationDto.getCity());
        location.setCountry(locationDto.getCountry());
        locationDAO.save(location);
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

    public void delete(Long id) {
        locationDAO.deleteById(id);
    }

    public void update(Long id, LocationDto locationDto) {
        LocationEntity location = locationDAO.getById(id);
        location.setCountry(locationDto.getCountry());
        location.setCity(locationDto.getCity());
        locationDAO.save(location);
    }
}
