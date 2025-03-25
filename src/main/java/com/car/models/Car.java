package com.car.models;

import com.car.validators.UniqueLicensePlate;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Must not be Empty and null")
    private String brand;
    @NotBlank(message = "Must not be Empty and null")
    private String model;
    @NotBlank(message = "Must not be Empty and null")
    private String color;
    private int year;
    private int price;

//    @NotBlank(message = "Must not be Empty and null")
    @UniqueLicensePlate
    private String licensePlate;
    private int rentalPricePerDay;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Rental> rentals = new ArrayList<>();

    @OneToOne(mappedBy = "car") // this tells jpa that the relationship is mapped in the carLogBook entity
    @JsonManagedReference
    private CarLogBook carLogBook;

    public Car() {}

    public Car(String brand, String model, String color, int year, int price, String licensePlate, int rentalPricePerDay, String status) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
        this.licensePlate = licensePlate;
        this.rentalPricePerDay = rentalPricePerDay;
        this.status = status;
        this.created_at = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    @PrePersist
    protected void onCreate() {
        created_at = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    public CarLogBook getCarLogBook() {
        return carLogBook;
    }

    public void setCarLogBook(CarLogBook carLogBook) {
        this.carLogBook = carLogBook;
    }

    public String getStatus() {
        return status;
    }

    public int getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setRentalPricePerDay(int rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
