package com.cschool.scooterrentalapp.api.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CreateUserAccountRequest {
    private String ownerUserName;
    private String ownerEmail;
    private Integer ownerAge;
}
