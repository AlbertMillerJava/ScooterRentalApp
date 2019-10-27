package com.cschool.scooterrentalapp.service;

import com.cschool.scooterrentalapp.api.request.CreateUserAccountRequest;
import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.api.response.CreateUserAccountResponse;
import com.cschool.scooterrentalapp.api.response.UserInfoResponse;
import org.springframework.http.ResponseEntity;

public interface UserAccountService {
    ResponseEntity<CreateUserAccountResponse> createUserAccount(CreateUserAccountRequest request);
    ResponseEntity<BasicResponse> rechargeUserAccount( Long userId, String amount);

    ResponseEntity<UserInfoResponse> ifUserHasScooter(String email);

    ResponseEntity<BasicResponse> getBalanceInfo(Long accountId);

    ResponseEntity<BasicResponse> deleteAccount(String email);

    ResponseEntity<BasicResponse> changeEmail(Long accountId, String newEmail);

}
