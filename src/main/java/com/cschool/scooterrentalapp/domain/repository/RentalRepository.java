package com.cschool.scooterrentalapp.domain.repository;

import com.cschool.scooterrentalapp.domain.model.Rental;
import com.cschool.scooterrentalapp.domain.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    Rental findByUserAccount(UserAccount userAccount);
}
