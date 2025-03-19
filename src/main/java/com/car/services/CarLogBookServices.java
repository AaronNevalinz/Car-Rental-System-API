package com.car.services;

import com.car.models.CarLogBook;
import com.car.repository.CarLogBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarLogBookServices {
    private final CarLogBookRepository carLogBookRepository;
    public CarLogBookServices(CarLogBookRepository carLogBookRepository) {
        this.carLogBookRepository = carLogBookRepository;
    }

    public List<CarLogBook> findAll() {
        return carLogBookRepository.findAll();
    }
}
