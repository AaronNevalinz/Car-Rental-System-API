package com.car.controllers;

import com.car.payload.RentalDTO;
import com.car.models.Car;
import com.car.models.Rental;
import com.car.payload.ApiResponse;
import com.car.services.CarServices;
import com.car.services.RentalServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalServices rentalServices;
    private final CarServices carServices;

    public RentalController(RentalServices rentalServices, CarServices carServices) {
        this.rentalServices = rentalServices;
        this.carServices = carServices;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<RentalDTO>>> getAllRentals() {
        try{
            List<Rental> rentals = rentalServices.getAllRentals();
            List<RentalDTO> rentalDTOs = new ArrayList<RentalDTO>();

            rentals.forEach(rental -> {
                rentalDTOs.add(new RentalDTO(rental));
            });

            if(!rentals.isEmpty()) {
                return ResponseEntity.ok(ApiResponse.success("Got them rentals", rentalDTOs));
            }

            return ResponseEntity.ok(ApiResponse.error("No rentals found", null));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Internal server error: " + e.getMessage(), null));
        }
    }


    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<RentalDTO>> addCarRentalDetails(@Valid  @PathVariable int id, @RequestBody Rental rental) {
        try{
            Optional<Car> car = carServices.getById(id);
            if (car.isPresent()) {
                String carStatus = car.get().getStatus();
                if (carStatus.equals("Rented") || carStatus.equals("rented") || carStatus.equals("RENTED")) {
                    return ResponseEntity.ok(ApiResponse.success("Car already rented", null));
                }

                Rental carRentalDetails = rentalServices.createRental(id, rental);
                return ResponseEntity.ok(ApiResponse.success("Rental added", new RentalDTO(carRentalDetails)));
            }
            return ResponseEntity.status(404).body(ApiResponse.error("Car not found", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.error("Internal server error: " + e.getMessage(), null));
        }
    }
}
