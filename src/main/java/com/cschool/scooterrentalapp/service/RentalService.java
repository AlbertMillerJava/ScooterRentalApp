package com.cschool.scooterrentalapp.service;

import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.domain.model.Rental;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RentalService {
    ResponseEntity<BasicResponse> rentScooter(Long scooterId, Long userId);

    ResponseEntity<BasicResponse> cancelRental(Long accountId, Long dockStationId);

    List<Rental> getAllRentals();

}
