package com.car.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueLicensePlateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface  UniqueLicensePlate {
    String message() default "license plate must be unique";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
