package com.car.controllers;

import com.car.DTO.RentalDTO;
import com.car.models.Car;
import com.car.models.Rental;
import com.car.response.ApiResponse;
import com.car.services.CarServices;
import com.car.services.RentalServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalServices rentalServices;
    private final CarServices carServices;

    public RentalController(RentalServices rentalServices, CarServices carServices) {
        this.rentalServices = rentalServices;
        this.carServices = carServices;
    }
//    @GetMapping()
//    public ResponseEntity<ApiResponse<List<RentalDTO>>> getAllRentals() {
//        List<Rental> rentals = rentalServices.getAllRentals();
//        return ResponseEntity.ok(ApiResponse.success("Got them rentals", new RentalDTO(rentals)));
//    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<RentalDTO>>> getAllRentals() {
        List<Rental> rentals = rentalServices.getAllRentals();
        List<RentalDTO> rentalDTOs = rentals.stream()
                .map(RentalDTO::new) // Convert each Rental to RentalDTO
                .collect(Collectors.toList());

        return ResponseEntity.ok(ApiResponse.success("Got them rentals", rentalDTOs));
    }


    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<RentalDTO>> addCarRentalDetails(@Valid  @PathVariable int id, @RequestBody Rental rental) {
        Optional<Car> car = carServices.getById(id);
        if (car.isPresent()) {
            Rental carRentalDetials = rentalServices.createRental(id, rental);
            return ResponseEntity.ok(ApiResponse.success("Rental added", new RentalDTO(carRentalDetials)));
        }
        return ResponseEntity.status(404).body(ApiResponse.error("Car not found", null));
    }
}
