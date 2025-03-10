package com.car.controllers;

import com.car.models.Rental;
import com.car.response.ApiResponse;
import com.car.services.RentalServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalServices rentalServices;
    public RentalController(RentalServices rentalServices) {
        this.rentalServices = rentalServices;
    }
    @GetMapping()
    public ResponseEntity<ApiResponse<List<Rental>>> hello(){
        List<Rental> rentals = rentalServices.getAllRentals();
        return ResponseEntity.ok(ApiResponse.success("Got them rentals", rentals));
    }


}
