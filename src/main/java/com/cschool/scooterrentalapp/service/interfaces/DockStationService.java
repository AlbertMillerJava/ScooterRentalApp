package com.cschool.scooterrentalapp.service.interfaces;

import com.cschool.scooterrentalapp.api.request.AddDockStationRequest;
import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.domain.model.Scooter;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface DockStationService {
    ResponseEntity<Set<Scooter>> getAllDockScooters( Long dockStationId);

    ResponseEntity<BasicResponse> addNewDockStation(AddDockStationRequest request);

}
