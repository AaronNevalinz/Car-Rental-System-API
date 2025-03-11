package com.car.DTO;

public class CarDTO {
    private String brand;
    private String model;
    private String color;
    private int year;
    private int price;
    private String licensePlate;
    private int rentalPricePerDay;
    private String status;

    public  CarDTO(String brand, String model, String color, int year, int price, String licensePlate, int rentalPricePerDay, String status){
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
        this.licensePlate = licensePlate;
        this.rentalPricePerDay = rentalPricePerDay;
        this.status = status;
    }

    public CarDTO(String brand, String model){
        this.brand = brand;
        this.model = model;
    }
}
