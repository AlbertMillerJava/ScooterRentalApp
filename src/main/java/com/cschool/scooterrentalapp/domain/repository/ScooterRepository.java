package com.cschool.scooterrentalapp.domain.repository;

import com.cschool.scooterrentalapp.domain.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScooterRepository extends JpaRepository<Scooter,Long> {
}
