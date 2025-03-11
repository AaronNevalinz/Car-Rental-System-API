package com.car.DTO;

import com.car.models.Rental;
import com.car.models.rentalStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentalDTO {
    private Long id;
    private Long carId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal totalCost;
    private rentalStatus rentalStatus;

    public RentalDTO(Rental rental) {
        this.id =rental.getId();
        this.carId = rental.getCar().getId();
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        this.totalCost = rental.getTotalCost();
        this.rentalStatus = rental.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Long getCarId() {
        return carId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public rentalStatus getRentalStatus() {
        return rentalStatus;
    }
}
