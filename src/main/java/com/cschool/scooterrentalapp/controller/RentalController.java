package com.cschool.scooterrentalapp.controller;

import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.domain.model.Rental;
import com.cschool.scooterrentalapp.service.interfaces.RentalService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rental")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;


    @PutMapping(value = "{scooterId}/scooter", produces = "application/json")
    public ResponseEntity<BasicResponse> createRental(
            @PathVariable Long scooterId,
            @RequestParam Long accountId) {
        return rentalService.rentScooter(scooterId, accountId);
    }


    @PutMapping(value = "/cancel", produces = "application/json")
    public ResponseEntity<BasicResponse> cancelRental(
            @RequestParam Long accountId,
            @RequestParam Long dockStationId){
        return rentalService.cancelRental( accountId, dockStationId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/getAllRentals", produces = "application/json")
    public List<Rental> getAllRentals(){
        return rentalService.getAllRentals();
    }
}


