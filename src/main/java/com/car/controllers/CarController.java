package com.car.controllers;

import com.car.payload.CarDTO;
import com.car.models.Car;
import com.car.payload.ApiResponse;
import com.car.services.CarServices;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarServices carServices;

    public CarController(CarServices carServices) {
        this.carServices = carServices;
    }

// Get all cars in the system
    @GetMapping()
    public ResponseEntity<ApiResponse<List<CarDTO>>> getAllCars() {
        List<Car> cars = carServices.getAll();
        List<CarDTO> carDTO = new ArrayList<>();
        cars.forEach(car -> {
            carDTO.add(new CarDTO(car));
        });
        return ResponseEntity.ok(ApiResponse.success("All cars", carDTO));
    }

//    sort the cars based upon some field
    @GetMapping("/sort/{field}")
    public ResponseEntity<ApiResponse<List<CarDTO>>> sort(@PathVariable("field") String field) {
        List<Car> cars = carServices.sortBasedUponSomeField(field);
        List<CarDTO> carDTO = new ArrayList<>();
        cars.forEach(car -> {
            carDTO.add(new CarDTO(car));
        });
        return ResponseEntity.ok(ApiResponse.success("Sorted cars", carDTO));
    }

// Pagination of cars
    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<ApiResponse<List<CarDTO>>> pagination(@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize) {
        Page<Car> cars = carServices.getCarsWithPagination(offset, pageSize);
        List<CarDTO> carDTO = new ArrayList<>();
        cars.forEach(car -> {
            carDTO.add(new CarDTO(car));
        });
        return ResponseEntity.ok(ApiResponse.success("Sorted cars", carDTO));
    }

    // Pagination with sorting of cars
    @GetMapping("/paginationwithsorting/{offset}/{pageSize}/{field}")
    public ResponseEntity<ApiResponse<List<CarDTO>>> paginationWithSorting(@PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize, @PathVariable("field") String field) {
        Page<Car> cars = carServices.getCarsWithPaginationAndSort(offset, pageSize, field);
        List<CarDTO> carDTO = new ArrayList<>();
        cars.forEach(car -> {
            carDTO.add(new CarDTO(car));
        });
        return ResponseEntity.ok(ApiResponse.success("Sorted cars", carDTO));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Car>> addCar(@Valid @RequestBody Car car) {
        Car savedCar = carServices.save(car);
        return ResponseEntity.ok(ApiResponse.success("Added car", savedCar));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CarDTO>> getCarById(@PathVariable int id) {

        Optional<Car> car = carServices.getById(id);

        if (car.isPresent()) {
            CarDTO carDTO = new CarDTO(car.get());
            return ResponseEntity.ok(ApiResponse.success("Got Car", carDTO));
        }



        return ResponseEntity.status(404).body(ApiResponse.error("Car not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Car>> deleteCar(@PathVariable int id) {
        Optional<Car> car = carServices.getById(id);
        if (car.isPresent()) {
            carServices.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success("Car deleted", car.get()));
        }
        return ResponseEntity.status(404).body(ApiResponse.error("Car not found", null));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CarDTO>> updateCar(@PathVariable int id, @RequestBody Car car) {
       try{
           Optional<Car> carOptional = carServices.getById(id);

           if (carOptional.isPresent()) {
               Car existingCar = carOptional.get();

               if(car.getBrand() != null) existingCar.setBrand(car.getBrand());
               if(car.getModel() != null) existingCar.setModel(car.getModel());
               if(car.getColor() != null) existingCar.setColor(car.getColor());
               if(car.getYear() != 0) existingCar.setYear(car.getYear());
               if (car.getLicensePlate() != null) existingCar.setLicensePlate(car.getLicensePlate());
               if (car.getRentalPricePerDay() != 0) existingCar.setRentalPricePerDay(car.getRentalPricePerDay());
               if(car.getStatus() != null) existingCar.setStatus(car.getStatus());
               if(car.getPrice() != 0) existingCar.setPrice(car.getPrice());

               Car updatedCar = carServices.save(existingCar);
               CarDTO carDTO = new CarDTO(updatedCar);
               return ResponseEntity.ok(ApiResponse.success("Car updated", carDTO));
           }

           return ResponseEntity.status(404).body(ApiResponse.error("Car not found", null));
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Internal server error: " + e.getMessage(), null));
       }
    }
}
