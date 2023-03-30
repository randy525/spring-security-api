package com.endava.restraining.entity;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class OfficeEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private LocationEntity location;
}
