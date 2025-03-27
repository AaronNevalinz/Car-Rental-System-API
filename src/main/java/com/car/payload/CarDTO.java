package com.car.payload;

import com.car.models.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CarDTO {
    private Long id;
    private String brand;
    private String model;
    private String color;
    private int year;
    private int price;
    private String licensePlate;
    private int rentalPricePerDay;
    private String status;

    public  CarDTO(Car car){
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.color = car.getColor();
        this.year = car.getYear();
        this.price = car.getPrice();
        this.licensePlate = car.getLicensePlate();
        this.rentalPricePerDay = car.getRentalPricePerDay();
        this.status = car.getStatus();
    }
    public Long getId() {
        return id;
    }
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public int getPrice() {
        return price;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public String getStatus() {
        return status;
    }
}
