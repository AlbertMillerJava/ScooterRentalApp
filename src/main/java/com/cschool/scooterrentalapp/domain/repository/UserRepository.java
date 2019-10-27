package com.cschool.scooterrentalapp.domain.repository;

import com.cschool.scooterrentalapp.domain.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserAccount,Long> {
    List<UserAccount> findByUserEmail(String userEmail);
}
