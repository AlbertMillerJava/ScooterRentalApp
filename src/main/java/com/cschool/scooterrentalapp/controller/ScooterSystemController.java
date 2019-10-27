package com.cschool.scooterrentalapp.controller;

import com.cschool.scooterrentalapp.api.request.AddScooterRequest;
import com.cschool.scooterrentalapp.api.response.AddScooterResponse;
import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.service.ScooterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("scooter")
@AllArgsConstructor
public class ScooterSystemController {
    private ScooterService scooterService;

    @PostMapping(value = "{scooterId}/scooter", produces = "application/json")
    public ResponseEntity<AddScooterResponse> addScooter(@RequestBody AddScooterRequest request) {
        return scooterService.addScooter(request);
    }

    @PutMapping(value = "{scooterId}/removeFromDock", produces = "application/json")
    public ResponseEntity<BasicResponse> removeScooterFromDock(@PathVariable Long scooterId){
        return scooterService.removeScooterFromDock(scooterId);
    }

}
