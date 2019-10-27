package com.cschool.scooterrentalapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Scooter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    private String modelName;

    private int maxSpeed;

    private BigDecimal price;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dock_station_id")
    @EqualsAndHashCode.Exclude
    private DockStation dockStation;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_account_id", referencedColumnName = "id")
    @ToString.Exclude
    private UserAccount userAccount;

    @OneToOne(mappedBy = "scooter")
    private Rental rental;
}
