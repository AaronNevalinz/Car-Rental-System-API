package com.car.repository;

import com.car.models.CarLogBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarLogBookRepository extends JpaRepository<CarLogBook, Long> {
}
