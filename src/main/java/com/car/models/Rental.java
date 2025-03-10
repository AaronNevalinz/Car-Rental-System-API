package com.car.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal totalCost;

    @Enumerated(EnumType.STRING)
    private rentalStatus status;

}

enum rentalStatus {
    PENDING, ACTIVE, COMPLETED, CANCELLED;
}
