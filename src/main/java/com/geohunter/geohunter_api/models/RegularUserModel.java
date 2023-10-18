package com.geohunter.geohunter_api.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_regularUser")
@Data
public class RegularUserModel {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 15)
    private String firstName;

    @Column(nullable = false, length = 15)
    private String lastName;

    @Column(nullable = false, unique = true, length = 10)
    private String rg;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, unique = true, length = 18)
    private String phoneNumber;
    
    @Column(nullable = false, length = 15)
    @Temporal(TemporalType.DATE)
    private Date birthday;
    
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false)
    private AddressModel address;

}
