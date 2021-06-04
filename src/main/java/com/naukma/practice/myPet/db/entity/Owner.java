package com.naukma.practice.myPet.db.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@DynamicUpdate
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
    private String region;

    public static Owner createOwner(String login, String name, String surname,
                                   String region, String city,
                                   String phone) {
        Owner owner = new Owner();
        owner.setLogin(login);
        owner.setName(name);
        owner.setSurname(surname);
        owner.setPhone(phone);
        owner.setRegion(region);
        owner.setCity(city);
        return owner;
    }



}
