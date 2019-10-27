package com.cschool.scooterrentalapp.service;

import com.cschool.scooterrentalapp.api.request.AddDockStationRequest;
import com.cschool.scooterrentalapp.api.response.BasicResponse;
import com.cschool.scooterrentalapp.common.MsgSource;
import com.cschool.scooterrentalapp.domain.model.DockStation;
import com.cschool.scooterrentalapp.domain.model.Scooter;
import com.cschool.scooterrentalapp.domain.repository.DockStationRepository;
import com.cschool.scooterrentalapp.domain.repository.DockStationRepository;
import com.cschool.scooterrentalapp.exception.CommonConflictException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
@Service
public class DockStationServiceImpl extends AbstractCommonService implements DockStationService {

    private DockStationRepository dockStationRepository;

    public DockStationServiceImpl(MsgSource msgSource, DockStationRepository dockStationRepository) {
        super(msgSource);
        this.dockStationRepository = dockStationRepository;
    }

    @Override
    @Transactional
    public ResponseEntity<BasicResponse> addNewDockStation(AddDockStationRequest request) {

        DockStation dockStation = new DockStation();
        dockStation.setAvailablePlace(request.getAvailablePlace());
        dockStation.setDockName(request.getDockName());
        dockStationRepository.save(dockStation);
        return ResponseEntity.ok(new BasicResponse(msgSource.OK009, dockStation.toString()));
    }

    @Override
    public ResponseEntity<Set<Scooter>> getAllDockScooters(Long dockStationId) {
        Optional<DockStation> optionalDockStation = dockStationRepository.findById(dockStationId);
        if(optionalDockStation.isEmpty()){
            throw new CommonConflictException(msgSource.ERR008);
        }
        return ResponseEntity.ok(optionalDockStation.get().getScooters());
    }
}
