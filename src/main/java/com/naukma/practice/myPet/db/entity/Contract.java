package com.naukma.practice.myPet.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "contact")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

    private Integer days;

    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;

}
