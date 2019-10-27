package com.cschool.scooterrentalapp.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddScooterResponse extends BasicResponse {

    private Long scooterId;

    public AddScooterResponse(String responseMsg, Long scooterId){
        super(responseMsg);
        this.scooterId = scooterId;
    }
}
