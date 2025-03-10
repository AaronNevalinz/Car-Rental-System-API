package com.car.services;

import com.car.models.Rental;
import com.car.repository.CarRepository;
import com.car.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServices {
    private final RentalRepository rentalRepository;

    public RentalServices(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
}
