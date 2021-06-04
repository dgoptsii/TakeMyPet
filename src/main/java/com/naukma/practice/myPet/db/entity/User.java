package com.naukma.practice.myPet.db.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@DynamicUpdate
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
    private String status = "ACTIVE";
//    @Enumerated(EnumType.STRING)
//    private UserStatus status;



    public static User createUser(String login, String email, String password,String role) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
//        user.setStatus("ACTIVE");
        return user;
    }
}
