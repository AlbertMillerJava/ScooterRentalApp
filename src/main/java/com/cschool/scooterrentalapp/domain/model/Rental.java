package com.cschool.scooterrentalapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_account_id",referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private UserAccount userAccount;


    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="scooter_id",referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Scooter scooter;



}
