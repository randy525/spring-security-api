package com.endava.restraining.dao;

import com.endava.restraining.entity.LocationEntity;
import com.endava.restraining.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeDAO extends JpaRepository<OfficeEntity, Long> {
    List<OfficeEntity> findAllByLocation(LocationEntity location);
}
