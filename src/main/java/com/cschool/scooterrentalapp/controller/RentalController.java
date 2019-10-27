package com.cschool.scooterrentalapp.controller;

import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.domain.model.Rental;
import com.cschool.scooterrentalapp.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("rental")
@AllArgsConstructor
public class RentalController {

    private RentalService rentalService;


    @PutMapping(value = "{scooterId}/scooter", produces = "application/json")
    public ResponseEntity<BasicResponse> createRental(
            @PathVariable Long scooterId,
            @RequestParam Long accountId) {
        return rentalService.rentScooter(scooterId, accountId);
    }


    @PutMapping(value = "/return", produces = "application/json")
    public ResponseEntity<BasicResponse> cancelRental(
            @RequestParam Long accountId,
            @RequestParam Long dockStationId){
        return rentalService.cancelRental( accountId, dockStationId);
    }

    @GetMapping(value = "getAllRentals", produces = "application/json")
    public List<Rental> getAllRentals(){
        return rentalService.getAllRentals();
    }
}


