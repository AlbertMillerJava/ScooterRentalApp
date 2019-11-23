package com.cschool.scooterrentalapp.service;

import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.common.MsgSource;
import com.cschool.scooterrentalapp.domain.model.DockStation;
import com.cschool.scooterrentalapp.domain.model.Rental;
import com.cschool.scooterrentalapp.domain.model.Scooter;
import com.cschool.scooterrentalapp.domain.model.UserAccount;
import com.cschool.scooterrentalapp.domain.repository.DockStationRepository;
import com.cschool.scooterrentalapp.domain.repository.RentalRepository;
import com.cschool.scooterrentalapp.domain.repository.ScooterRepository;
import com.cschool.scooterrentalapp.domain.repository.UserRepository;
import com.cschool.scooterrentalapp.exception.CommonBadRequestException;
import com.cschool.scooterrentalapp.exception.CommonConflictException;
import com.cschool.scooterrentalapp.service.interfaces.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl extends AbstractCommonService implements RentalService {

    private UserRepository userRepository;
    private ScooterRepository scooterRepository;
    private DockStationRepository dockStationRepository;
    private RentalRepository rentalRepository;

    public RentalServiceImpl(MsgSource msgSource,
                             UserRepository userRepository,
                             ScooterRepository scooterRepository,
                             DockStationRepository dockStationRepository,
                             RentalRepository rentalRepository) {
        super(msgSource);
        this.userRepository = userRepository;
        this.scooterRepository = scooterRepository;
        this.dockStationRepository = dockStationRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> rentScooter(Long scooterId, Long userId) {


        UserAccount userAccount = extractUserFromRepository(userId);
        Scooter scooter = extractScooterFromRepository(scooterId);
        checkScooterIsAvailable(scooter);
        checkUserAccountAlreadyHaveAnyRental(userAccount);
        checkUserHaveEnoughMoney(userAccount, scooter.getPrice());
        finalizeScooterRental(userAccount, scooter);
        startRental(scooter,userAccount);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK004));
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> cancelRental(Long accountId, Long dockStationId) {
        UserAccount userAccount = extractUserFromRepository(accountId);
        checkUserHasRentScooter(userAccount);
        Scooter scooter = extractScooterFromRepository(userAccount.getScooter().getId());
        DockStation dockStation = extractDockStationFromRepository(dockStationId);
        checkFreePlaceInDockStation(dockStation);
        cancelScooterRental(userAccount, scooter, dockStation);
        Rental rental = rentalRepository.findByUserAccount(userAccount);
        endRental(rental);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK005));

    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    private void startRental(Scooter scooter, UserAccount userAccount) {
        rentalRepository.save(Rental.builder()
                .scooter(scooter)
                .userAccount(userAccount)
                .rentalStart(LocalDateTime.now())
                .build());
    }

    private void endRental(Rental rental){
        rental.setRentalEnd(LocalDateTime.now());
        rentalRepository.save(rental);
    }

    private void checkUserHasRentScooter(UserAccount userAccount) {
        if (userAccount.getScooter() == null) {
            throw new CommonBadRequestException(msgSource.ERR014);
        }
    }

    private void cancelScooterRental(UserAccount userAccount, Scooter scooter, DockStation dockStation) {
        scooter.setDockStation(dockStation);
        scooter.setUserAccount(null);
        userAccount.setScooter(null);
        scooterRepository.save(scooter);
    }

    private void checkFreePlaceInDockStation(DockStation dockStation) {
        if (dockStation.getAvailablePlace() <= dockStation.getScooters().size()) {
            throw new CommonBadRequestException(msgSource.ERR009);
        }
    }

    private DockStation extractDockStationFromRepository(Long dockStationId) {
        Optional<DockStation> optionalDockStation = dockStationRepository.findById(dockStationId);
        if (optionalDockStation.isEmpty()) {
            throw new CommonConflictException(msgSource.ERR008);
        }
        return optionalDockStation.get();
    }

    private UserAccount extractUserFromRepository(Long userId) {
        Optional<UserAccount> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new CommonConflictException(msgSource.ERR006);
        }
        return optionalUser.get();
    }

    private Scooter extractScooterFromRepository(Long scooterId) {
        Optional<Scooter> optionalScooter = scooterRepository.findById(scooterId);
        if (optionalScooter.isEmpty()) {
            throw new CommonConflictException(msgSource.ERR010);
        }
        return optionalScooter.get();
    }

    private void checkScooterIsAvailable(Scooter scooter) {
        if (scooter.getDockStation() == null || scooter.getUserAccount() != null) {
            throw new CommonConflictException(msgSource.ERR011);
        }
    }

    private void checkUserAccountAlreadyHaveAnyRental(UserAccount userAccount) {
        if (userAccount.getScooter() != null) {
            throw new CommonConflictException(msgSource.ERR012);
        }
    }

    private void checkUserHaveEnoughMoney(UserAccount userAccount, BigDecimal rentalPrice) {
        if (userAccount.getAccountBalance().compareTo(rentalPrice) < 0) {
            throw new CommonConflictException(msgSource.ERR013);
        }
    }

    private void finalizeScooterRental(UserAccount userAccount, Scooter scooter) {
        userAccount.setAccountBalance(userAccount.getAccountBalance().subtract(scooter.getPrice()));
        scooter.setDockStation(null);
        scooter.setUserAccount(userAccount);
        scooterRepository.save(scooter);
    }


}
