package com.naukma.practice.myPet.db.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name="host")
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String name;
    private String surname;


    private String city;
    private String region;
    private String address;
    private Double rating = 0.0;

    @Column(name="max_animals")
    private Integer maxAnimals = 1;
    private String phone;


    public static Host createHost(String login, String name, String surname,
                                  String region, String city,
                                  String phone
//                                  Double rating, Integer maxAnimals, String address,
                                  ) {
        Host host = new Host();
        host.setLogin(login);
        host.setName(name);
        host.setSurname(surname);
        host.setRegion(region);
        host.setCity(city);

//        host.setAddress(address);
//        host.setRating(rating);
//        host.setMaxAnimals(maxAnimals);
        host.setPhone(phone);
        return host;
    }

}
