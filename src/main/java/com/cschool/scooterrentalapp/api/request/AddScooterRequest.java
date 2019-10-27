package com.cschool.scooterrentalapp.api.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddScooterRequest {
    private String modelName;
    private int maxSpeed;
    private String rentalPrice;
    private Long dockStationId;

}
