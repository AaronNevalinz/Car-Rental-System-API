package com.car.services;

import com.car.exceptions.NotFoundException;
import com.car.models.Car;
import com.car.models.Rental;
import com.car.models.User;
import com.car.models.rentalStatus;
import com.car.repository.CarRepository;
import com.car.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalServices {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final UserService userService;

    public RentalServices(RentalRepository rentalRepository, CarRepository carRepository, UserService userService) {
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
        this.userService = userService;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental createRental(int carId, Long userId, Rental rental) {
        Optional<Car> car = carRepository.findById(carId);
        Optional<User> user = userService.getUserById(userId);

        if (car.isPresent() && user.isPresent()) {
            rental.setCar(car.get());
            rental.setUser(user.get());
            rental.setStatus(rental.setStatus(rentalStatus.ACTIVE));
        } else {
            throw new NotFoundException("Car not found");
        }



        return rentalRepository.save(rental);
    }
}
