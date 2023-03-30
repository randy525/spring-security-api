package com.endava.restraining.dao;

import com.endava.restraining.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationDAO extends JpaRepository<LocationEntity, Long> {
    List<LocationEntity> findByCity(String city);
}
