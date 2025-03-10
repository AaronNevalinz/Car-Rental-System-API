package com.car.services;

import com.car.models.Car;
import com.car.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServices {
    private final CarRepository carRepository;

    public CarServices(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAll() {
        return carRepository.findAll();
    }
    public Optional<Car> getById(int id) {
        return carRepository.findById(id);
    }
    public Car save(Car car) {
        return carRepository.save(car);
    }
    public void deleteById(int id) {
        carRepository.deleteById(id);
    }
    public Car update(int id, Car carDetails) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setBrand(carDetails.getBrand());
        car.setColor(carDetails.getColor());
        car.setColor(carDetails.getColor());
        car.setYear(carDetails.getYear());
        car.setPrice(carDetails.getPrice());
        car.setLicensePlate(carDetails.getLicensePlate());
        car.setRentalPricePerDay(carDetails.getRentalPricePerDay());
        car.setStatus(carDetails.getStatus());
        return carRepository.save(car);
    }
}
