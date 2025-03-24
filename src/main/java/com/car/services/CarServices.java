package com.car.services;

import com.car.exceptions.NotFoundException;
import com.car.models.Car;
import com.car.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public List<Car> sortCars(String field, String direction){
        try {
            Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            return carRepository.findAll(Sort.by(sortDirection, field));
        } catch (Exception e) {
             throw new NotFoundException(e.getMessage());
        }
    }

    public List<Car> sortBasedUponSomeField(String field) {
        return carRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<Car> getCarsWithPagination(int offset, int pageSize) {
        return carRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public Page<Car> getCarsWithPaginationAndSort(int offset, int pageSize, String sortField) {
        return carRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC, sortField)));
    }

    public List<Car> findCarsWithSorting(String sortField) {
        return carRepository.findAll(Sort.by(Sort.Direction.ASC, sortField));
    }
}
