package com.car.services;

import com.car.models.Car;
import com.car.models.Rental;
import com.car.repository.CarRepository;
import com.car.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalServices {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;

    public RentalServices(RentalRepository rentalRepository, CarRepository carRepository) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;

    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental createRental(int carId, Rental rental) {
        Optional<Car> car = carRepository.findById(carId);

        rental.setCar(car.get());

        return rentalRepository.save(rental);
    }
}
