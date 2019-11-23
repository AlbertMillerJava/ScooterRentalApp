package com.cschool.scooterrentalapp.domain.repository;

import com.cschool.scooterrentalapp.domain.model.Rental;
import com.cschool.scooterrentalapp.domain.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    Rental findByUserAccount(UserAccount userAccount);
    @Query(value = "Select * from rental where (end, user_account_id) = (null, ?)" ,nativeQuery = true)
    Rental findByUserWithNullDateEnd(UserAccount userAccount);
}
