package com.endava.restraining.service;

import com.endava.restraining.dao.LocationDAO;
import com.endava.restraining.dao.OfficeDAO;
import com.endava.restraining.entity.LocationEntity;
import com.endava.restraining.entity.OfficeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OfficeService {

    private final OfficeDAO officeDAO;
    private final LocationDAO locationDAO;



    public void add(Long id){

        LocationEntity locationEntity = locationDAO.findById(id).get();
        OfficeEntity officeEntity = new OfficeEntity();
        officeEntity.setLocation(locationEntity);
        officeDAO.save(officeEntity);
    }

    public List<Long> findByCity(String city){
        List<LocationEntity> locationList = locationDAO.findByCity(city);
        List<Long> officeList = new ArrayList<>();
        locationList.forEach(location -> officeDAO
                .findAllByLocation(location)
                .forEach(office -> officeList.add(office.getId())));
        return officeList;
    }


}
