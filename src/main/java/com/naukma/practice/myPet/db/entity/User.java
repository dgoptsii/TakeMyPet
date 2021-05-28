package com.naukma.practice.myPet.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String pass;
    private String role;
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;



    public static User createUser(String login, String email, String pass) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPass(pass);
        user.setRole("USER");
//        user.setAccess("disable");
        return user;
    }
}
