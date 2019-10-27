package com.cschool.scooterrentalapp.api.request;

import lombok.Data;

@Data
public class CreateUserAccountRequest {
    private String ownerUserName;
    private String ownerEmail;
    private Integer ownerAge;
}
