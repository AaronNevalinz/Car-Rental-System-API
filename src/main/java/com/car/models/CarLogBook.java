package com.car.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CarLogBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
