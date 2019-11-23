package com.cschool.scooterrentalapp.api.request;

import lombok.Data;


@Data
public class AddDockStationRequest {
    String dockName;
    Integer availablePlace;
}
