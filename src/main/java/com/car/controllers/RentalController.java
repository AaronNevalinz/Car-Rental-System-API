package com.car.controllers;

import com.car.exceptions.NotFoundException;
import com.car.models.User;
import com.car.payload.RentalDTO;
import com.car.models.Car;
import com.car.models.Rental;
import com.car.payload.ApiResponse;
import com.car.services.CarServices;
import com.car.services.RentalServices;
import com.car.services.UserService;
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
    private final UserService userService;

    public RentalController(RentalServices rentalServices, CarServices carServices, UserService userService) {
        this.rentalServices = rentalServices;
        this.carServices = carServices;
        this.userService = userService;
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


    @PostMapping("/{user_id}/{car_id}")
    public ResponseEntity<ApiResponse<RentalDTO>> addCarRentalDetails(@Valid  @PathVariable Long user_id, @PathVariable int car_id, @RequestBody Rental rental) {
        try{
            Optional<Car> car = carServices.getById(car_id);
            Optional<User> user = userService.getUserById(user_id);

            if (car.isPresent() && user.isPresent()) {
                String carStatus = car.get().getStatus();
                if (carStatus.equals("Rented") || carStatus.equals("rented") || carStatus.equals("RENTED")) {
                    return ResponseEntity.ok(ApiResponse.error("Car already rented", null));
                }
                car.get().setStatus("Rented");

                Rental carRentalDetails = rentalServices.createRental(car_id, user_id, rental);
                return ResponseEntity.ok(ApiResponse.success("Rental added", new RentalDTO(carRentalDetails)));
            } else{
                throw new NotFoundException("Invalid credentials.....");
            }
//            return ResponseEntity.status(404).body(ApiResponse.error("Car not found", null));
        } catch (Exception e) {
            return ResponseEntity.ok(ApiResponse.error(e.getMessage(), null));
        }
    }
}
