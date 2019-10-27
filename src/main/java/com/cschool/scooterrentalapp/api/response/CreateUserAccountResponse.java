package com.cschool.scooterrentalapp.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserAccountResponse extends BasicResponse{
    private Long accountId;

    public CreateUserAccountResponse(String responseMsg, Long accountId){
        super(responseMsg);
        this.accountId = accountId;
    }
}
