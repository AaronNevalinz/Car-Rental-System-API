package com.car.validators;

import com.car.repository.CarRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class UniqueLicensePlateValidator implements ConstraintValidator<UniqueLicensePlate, String> {

    private static CarRepository carRepository;

    @Autowired
    public void setCarRepository(CarRepository repository) {
        carRepository = repository;
    }


    @Override
    public boolean isValid(String licensePlate, ConstraintValidatorContext context) {
        if (licensePlate == null) {
            System.out.println("truthy");

            return true; // Allow empty or null values
        } else {
            try {
                System.out.println("Working");
                return !carRepository.existsByLicensePlate(licensePlate);
            } catch (ValidationException e) {
                throw new ValidationException(e.getMessage());
            }
        }
    }
}
