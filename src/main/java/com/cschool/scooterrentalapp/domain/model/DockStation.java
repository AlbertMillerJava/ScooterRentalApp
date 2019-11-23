package com.cschool.scooterrentalapp.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
@Builder
@Entity
@Data
public class DockStation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String dockName;

    private Integer availablePlace;

    @OneToMany(mappedBy = "dockStation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Scooter> scooters;
}
