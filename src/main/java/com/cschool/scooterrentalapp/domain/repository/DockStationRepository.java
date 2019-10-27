package com.cschool.scooterrentalapp.domain.repository;

import com.cschool.scooterrentalapp.domain.model.DockStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DockStationRepository extends JpaRepository<DockStation,Long> {
}
