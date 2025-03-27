package com.car.payload;

import com.car.models.Rental;
import com.car.models.rentalStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentalDTO {
    private Long id;
    private Long carId;
    private Long userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal totalCost;
    private rentalStatus rentalStatus;

    public RentalDTO(Rental rental) {
        this.id =rental.getId();
        this.carId = rental.getCar().getId();
        this.userId = rental.getUser().getId();
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public void setRentalStatus(rentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}
