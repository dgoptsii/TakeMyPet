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
    private String password;
    private String role;
    private String email;


    @Column(name="user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;



    public static User createUser(String login, String email, String password) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("USER");
//        user.setAccess("disable");
        return user;
    }
}
