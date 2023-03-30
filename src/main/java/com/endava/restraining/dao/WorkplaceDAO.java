package com.endava.restraining.dao;

import com.endava.restraining.entity.WorkplaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface WorkplaceDAO extends JpaRepository<WorkplaceEntity, Long> {
}
