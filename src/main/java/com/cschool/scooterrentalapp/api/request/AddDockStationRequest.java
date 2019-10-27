package com.cschool.scooterrentalapp.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class AddDockStationRequest {
    String dockName;
    Integer availablePlace;
}
