package com.naukma.practice.myPet.db.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
