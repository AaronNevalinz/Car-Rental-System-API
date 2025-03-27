package com.car.services;

import com.car.exceptions.NotFoundException;
import com.car.models.Car;
import com.car.models.CarLogBook;
import com.car.repository.CarLogBookRepository;
import com.car.repository.CarRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarLogBookServices {
    private final CarLogBookRepository carLogBookRepository;
    private final CarRepository carRepository;
    public CarLogBookServices(CarLogBookRepository carLogBookRepository, CarRepository carRepository) {
        this.carLogBookRepository = carLogBookRepository;
        this.carRepository = carRepository;
    }

    public List<CarLogBook> getAllBooks() {
        return carLogBookRepository.findAll();
    }

    public CarLogBook uploadCarLogBook(int id, CarLogBook logBook) {
        Optional<Car> car = carRepository.findById(id);


        logBook.setCar(car.get());

        try{
            return carLogBookRepository.save(logBook);
        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("Logbook violates unique constraints.");
        }
    }
}
