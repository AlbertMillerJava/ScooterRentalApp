package com.cschool.scooterrentalapp.api.response;

import com.cschool.scooterrentalapp.domain.model.Scooter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoResponse extends BasicResponse {

    private Scooter scooter;

    public UserInfoResponse(Scooter scooter) {
        this.scooter = scooter;
    }

    public UserInfoResponse(String responseMsg, Scooter scooter) {
        super(responseMsg);
        this.scooter = scooter;
    }

    public UserInfoResponse(String errorCode, String errorMsg, Scooter scooter) {
        super(errorCode, errorMsg);
        this.scooter = scooter;
    }
}
