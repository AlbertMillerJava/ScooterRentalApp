package com.cschool.scooterrentalapp.service;

import com.cschool.scooterrentalapp.api.request.CreateUserAccountRequest;
import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.api.response.CreateUserAccountResponse;
import com.cschool.scooterrentalapp.api.response.UserInfoResponse;
import com.cschool.scooterrentalapp.common.ConstErrorMsg;
import com.cschool.scooterrentalapp.common.MsgSource;
import com.cschool.scooterrentalapp.domain.model.Scooter;
import com.cschool.scooterrentalapp.domain.model.UserAccount;
import com.cschool.scooterrentalapp.domain.repository.UserRepository;
import com.cschool.scooterrentalapp.exception.CommonBadRequestException;
import com.cschool.scooterrentalapp.exception.CommonConflictException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.cschool.scooterrentalapp.validation.ValidationUtils.*;

@Service
public class UserAccountServiceImpl extends AbstractCommonService implements UserAccountService {


    private UserRepository userRepository;


    public UserAccountServiceImpl(MsgSource msgSource, UserRepository userRepository) {
        super(msgSource);
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> changeEmail(Long accountId, String newEmail) {
        UserAccount userAccount = extractUserFromRepository(accountId);
        if (isUncorrectedEmail(newEmail)){
            throw new CommonConflictException(msgSource.ERR002);
        }
        UserAccount newAccount = new UserAccount();
        try{
            BeanUtils.copyProperties(userAccount,newAccount);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        userRepository.delete(userAccount);
        userRepository.save(newAccount);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK007));
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> deleteAccount(String email) {
        List<UserAccount> accounts = getUserFromEmail(email);
        UserAccount userAccount = accounts.get(0);
        userRepository.delete(userAccount);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK006));
    }

    @Override
    public ResponseEntity<BasicResponse> getBalanceInfo(Long accountId) {
        UserAccount userAccount = extractUserFromRepository(accountId);
        BigDecimal amount = userAccount.getAccountBalance();
        return ResponseEntity.ok(BasicResponse.of("stan konta wynosi: " + amount.toString()));
    }

    @Override
    @Transactional
    public ResponseEntity<UserInfoResponse> ifUserHasScooter(String email) {
        List<UserAccount> userAccounts = getUserFromEmail(email);
        if (userAccounts.get(0).getScooter() == null) {
            throw new CommonBadRequestException(msgSource.ERR014);
        }
        Scooter scooter = userAccounts.get(0).getScooter();
        return ResponseEntity.ok(new UserInfoResponse(scooter));
    }


    @Override
    @Transactional
    public ResponseEntity<CreateUserAccountResponse> createUserAccount(CreateUserAccountRequest request) {
        validateCreateAccountRequest(request);
        checkEmailAlreadyExists(request.getOwnerEmail());
        UserAccount addedUserAccount = addUserAccountToDataSource(request);
        return ResponseEntity.ok(new CreateUserAccountResponse(msgSource.OK001, addedUserAccount.getId()));
    }

    private UserAccount extractUserFromRepository(Long accountId) {
        Optional<UserAccount> optionalUserAccount = userRepository.findById(accountId);
        if (optionalUserAccount.isEmpty()) {
            throw new CommonBadRequestException(msgSource.ERR006);
        }
        return optionalUserAccount.get();
    }

    private List<UserAccount> getUserFromEmail(String email) {
        if (isUncorrectedEmail(email)) {
            throw new CommonBadRequestException(msgSource.ERR009);
        }
        List<UserAccount> userAccounts = userRepository.findByUserEmail(email);
        if (userAccounts.isEmpty()) {
            throw new CommonBadRequestException(new ConstErrorMsg("ERROR", "dla podanego adresu nie ma użytkownika"));
        }
        return userAccounts;
    }

    private void validateCreateAccountRequest(CreateUserAccountRequest request) {
        if (isNullOrEmpty(request.getOwnerUserName())
                || isNullOrEmpty(request.getOwnerEmail())
                || isNull(request.getOwnerAge())) {
            throw new CommonBadRequestException(msgSource.ERR001);
        }
        if (isUncorrectedEmail(request.getOwnerEmail())) {
            throw new CommonBadRequestException(msgSource.ERR002);
        }
        if (isUncorrectedAge(request.getOwnerAge())) {
            throw new CommonConflictException(msgSource.ERR003);
        }
    }


    private void checkEmailAlreadyExists(String email) {
        List<UserAccount> userAccounts = userRepository.findByUserEmail(email);
        if (!userAccounts.isEmpty()) {
            throw new CommonConflictException(msgSource.ERR004);
        }
    }

    private UserAccount addUserAccountToDataSource(CreateUserAccountRequest request) {
        UserAccount userAccount = new UserAccount();
        try{
            BeanUtils.copyProperties(request,userAccount);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        userAccount.setCreateDate(LocalDateTime.now());
        userAccount.setId(null);
        userAccount.setAccountBalance(new BigDecimal(0.0));
        return userRepository.save(userAccount);
    }


    @Override
    public ResponseEntity<BasicResponse> rechargeUserAccount(Long userId, String amount) {
        BigDecimal rechargeAccount = extractAmountToBigDecimal(amount);
        addRechargeAmountToUserAccountBalance(userId, rechargeAccount);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK002));
    }

    private BigDecimal extractAmountToBigDecimal(String amount) {
        try {
            return new BigDecimal(amount);
        } catch (NumberFormatException nfe) {
            throw new CommonBadRequestException(msgSource.ERR005);
        }
    }

    private void addRechargeAmountToUserAccountBalance(Long userId, BigDecimal rechargeAmount) {
        Optional<UserAccount> userData = userRepository.findById(userId);
        if (userData.isEmpty()) {
            throw new CommonConflictException(msgSource.ERR006);
        }
        UserAccount userAccount = userData.get();
        userAccount.setAccountBalance(userAccount.getAccountBalance().add(rechargeAmount));
        userRepository.save(userAccount);
    }

}









