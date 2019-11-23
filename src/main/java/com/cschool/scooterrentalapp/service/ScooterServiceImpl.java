package com.cschool.scooterrentalapp.service;

import com.cschool.scooterrentalapp.api.request.AddScooterRequest;
import com.cschool.scooterrentalapp.api.response.AddScooterResponse;
import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.common.MsgSource;
import com.cschool.scooterrentalapp.domain.model.DockStation;
import com.cschool.scooterrentalapp.domain.model.Scooter;
import com.cschool.scooterrentalapp.domain.repository.DockStationRepository;
import com.cschool.scooterrentalapp.domain.repository.ScooterRepository;
import com.cschool.scooterrentalapp.exception.CommonBadRequestException;
import com.cschool.scooterrentalapp.exception.CommonConflictException;
import com.cschool.scooterrentalapp.service.interfaces.ScooterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

import static com.cschool.scooterrentalapp.validation.ValidationUtils.*;

@Service
public class ScooterServiceImpl extends AbstractCommonService implements ScooterService {

    private ScooterRepository scooterRepository;
    private DockStationRepository dockStationRepository;

    public ScooterServiceImpl(MsgSource msgSource, ScooterRepository scooterRepository, DockStationRepository dockStationRepository) {
        super(msgSource);
        this.scooterRepository = scooterRepository;
        this.dockStationRepository = dockStationRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> removeScooterFromDock(Long scooterId) {
        checkIsScooterExists(scooterId);
        Scooter scooter = scooterRepository.findById(scooterId).get();
        scooter.setDockStation(null);
        scooterRepository.save(scooter);
        return ResponseEntity.ok(BasicResponse.of(msgSource.OK008));
    }

    @Override
    @Transactional
    public ResponseEntity<AddScooterResponse> addScooter(AddScooterRequest request) {
        validateAddScooterRequest(request);
        DockStation dockStation = extractDockStationFromRepository(request.getDockStationId());
        checkAvailablePlaceInDock(dockStation);
        Scooter addedScooter = addScooterToDatasource(request,dockStation);
        return ResponseEntity.ok(new AddScooterResponse(msgSource.OK003, addedScooter.getId()));
    }

    private void checkIsScooterExists(Long scooterId){
        if(scooterRepository.findById(scooterId).isEmpty()){
            throw new CommonBadRequestException(msgSource.ERR010);
        }
    }

    private Scooter addScooterToDatasource(AddScooterRequest request, DockStation dockStation){
        return scooterRepository.save(Scooter.builder()
                .modelName(request.getModelName())
                .dockStation(dockStation)
                .maxSpeed(request.getMaxSpeed())
                .price(new BigDecimal(request.getRentalPrice()))
                .build());
    }

    private void checkAvailablePlaceInDock(DockStation dockStation){
        if(dockStation.getAvailablePlace() <= dockStation.getScooters().size()){
            throw new CommonConflictException(msgSource.ERR009);
        }
    }
    private DockStation extractDockStationFromRepository(Long dockId){
        Optional<DockStation> dockStationOptional = dockStationRepository.findById(dockId);
        if( dockStationOptional.isEmpty()){
            throw new CommonConflictException(msgSource.ERR008);
        }
        return dockStationOptional.get();
    }



    private void validateAddScooterRequest(AddScooterRequest request) {
        if (isNullOrEmpty(request.getModelName())
                || isNull(request.getRentalPrice())
                || isNull(request.getMaxSpeed())
                || isNull(request.getDockStationId())) {
            throw new CommonBadRequestException(msgSource.ERR001);
        }
        if(isUncorrectedSpeed(request.getMaxSpeed())){
            throw new CommonConflictException(msgSource.ERR007);
        }
    }
}
