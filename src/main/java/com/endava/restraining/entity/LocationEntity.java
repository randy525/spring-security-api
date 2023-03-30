package com.endava.restraining.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LocationEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;
    
    private String city;
}
