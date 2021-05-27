package com.naukma.practice.myPet.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Table(name="")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
