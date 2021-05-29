package com.naukma.practice.myPet.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String name;
    private String surname;
    private String phone;

    private String city;
    private String country;

    public static Owner createOwner(String login, String name, String surname, String phone) {
        Owner owner = new Owner();
        owner.setLogin(login);
        owner.setName(name);
        owner.setSurname(surname);
        owner.setPhone(phone);
        return owner;
    }



}
