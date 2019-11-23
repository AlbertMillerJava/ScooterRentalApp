package com.cschool.scooterrentalapp.controller;

import com.cschool.scooterrentalapp.api.request.CreateUserAccountRequest;
import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.api.response.CreateUserAccountResponse;
import com.cschool.scooterrentalapp.api.response.UserInfoResponse;
import com.cschool.scooterrentalapp.service.interfaces.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/account-user")
@AllArgsConstructor
public class UserAccountController {

    private UserAccountService userAccountService;

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<CreateUserAccountResponse> createUserAccount(@RequestBody CreateUserAccountRequest request){
        return userAccountService.createUserAccount(request);
    }


    @PutMapping(value = "/{accountId}/recharge", produces = "application/json")
    public ResponseEntity<BasicResponse> rechargeUserAccount(
            @PathVariable Long accountId,
            @RequestParam String amount){
        return userAccountService.rechargeUserAccount(accountId,amount);
    }

    @GetMapping(value = "{email}/scooter", produces = "application/json")
    public ResponseEntity<UserInfoResponse> infoUserHasScooter(@PathVariable String email){
        return userAccountService.ifUserHasScooter(email);
    }

    @GetMapping(value = "{accountId}/balance", produces = "application/json")
    public ResponseEntity<BasicResponse> infoAboutAccountBalance(@PathVariable Long accountId){
        return userAccountService.getBalanceInfo(accountId);
    }

    @DeleteMapping(value = "{email}/delete", produces = "application/json")
    public ResponseEntity<BasicResponse> deleteAccount(@PathVariable String email){
        return userAccountService.deleteAccount(email);
    }

    @PutMapping(value = "{accountId}/changeEmail", produces = "application/json")
    public ResponseEntity<BasicResponse> changeEmail(@PathVariable Long accountId,
                                                     @RequestParam String newEmail){
        return userAccountService.changeEmail(accountId, newEmail);
    }

}
