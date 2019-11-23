package com.cschool.scooterrentalapp.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDateTime rentalStart;

    private LocalDateTime rentalEnd;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name ="scooter_id")
    private Scooter scooter;



}
