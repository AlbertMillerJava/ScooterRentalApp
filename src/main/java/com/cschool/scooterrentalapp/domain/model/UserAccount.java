package com.cschool.scooterrentalapp.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String userName;

    private String userEmail;

    private int age;

    private BigDecimal accountBalance;

    private LocalDateTime createDate;

    @OneToOne(mappedBy = "userAccount")
    private Scooter scooter;

    @OneToOne(mappedBy = "userAccount")
    private Rental rental;

    public UserAccount(Long id,
                       String userName,
                       String userEmail,
                       int age,
                       BigDecimal accountBalance,
                       LocalDateTime createDate) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.age = age;
        this.accountBalance = accountBalance;
        this.createDate = createDate;
    }
}
