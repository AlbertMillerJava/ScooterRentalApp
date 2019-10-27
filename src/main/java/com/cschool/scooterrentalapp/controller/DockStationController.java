package com.cschool.scooterrentalapp.controller;

import com.cschool.scooterrentalapp.api.request.AddDockStationRequest;
import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.domain.model.Scooter;
import com.cschool.scooterrentalapp.service.DockStationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("scooter-dock")
@AllArgsConstructor
public class DockStationController {

    private DockStationService dockStationService;

    @GetMapping(value = "{scooterDockId}/scooters", produces = "application/json")
    public ResponseEntity<Set<Scooter>> getScooters(
            @PathVariable Long scooterDockId) {
        return dockStationService.getAllDockScooters(scooterDockId);
    }

    @PostMapping(value = "addDock", produces = "application/json")
    public ResponseEntity<BasicResponse> addDockStation(@RequestBody AddDockStationRequest request){
        return dockStationService.addNewDockStation(request);
    }
}
