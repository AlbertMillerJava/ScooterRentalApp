package com.cschool.scooterrentalapp.service.interfaces;

import com.cschool.scooterrentalapp.api.request.AddScooterRequest;
import com.cschool.scooterrentalapp.api.response.AddScooterResponse;
import com.cschool.scooterrentalapp.api.response.BasicResponse;
import org.springframework.http.ResponseEntity;

public interface ScooterService {
    ResponseEntity<AddScooterResponse> addScooter(AddScooterRequest request);

    ResponseEntity<BasicResponse> removeScooterFromDock(Long scooterId);

}
