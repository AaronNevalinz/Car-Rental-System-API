package com.car.controllers;

import com.car.models.Car;
import com.car.response.ApiResponse;
import com.car.services.CarServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarServices carServices;

    public CarController(CarServices carServices) {
        this.carServices = carServices;
    }


    @GetMapping()
    public ResponseEntity<ApiResponse<List<Car>>> getAllCars() {
        List<Car> cars = carServices.getAll();
        return ResponseEntity.ok(ApiResponse.success("All cars", cars));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Car>> addCar(@Valid @RequestBody Car car) {
        Car savedCar = carServices.save(car);
        return ResponseEntity.ok(ApiResponse.success("Added car", savedCar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Car>> getCarById(@PathVariable int id) {
        Optional<Car> car = carServices.getById(id);
        return car.map(c -> ResponseEntity.ok(ApiResponse.success("Got Car", c))).orElseGet(()-> ResponseEntity.status(404).body(ApiResponse.error("Car not found", null)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Car>> deleteCar(@PathVariable int id) {
        Optional<Car> car = carServices.getById(id);
        if (car.isPresent()) {
            carServices.deleteById(id);
        }
        return car.map(c->ResponseEntity.ok(ApiResponse.success("Car deleted", c))).orElseGet(()-> ResponseEntity.status(404).body(ApiResponse.error("Car not found", null)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Car>> updateCar(@PathVariable int id, @Valid @RequestBody Car car) {
        Optional<Car> carOptional = carServices.getById(id);
        if (carOptional.isPresent()) {
           Car updatedCar = carServices.update(id, car);
           return ResponseEntity.ok(ApiResponse.success("Car updated", updatedCar));
        }
        return ResponseEntity.status(404).body(ApiResponse.error("Car not found", null));
    }

}
