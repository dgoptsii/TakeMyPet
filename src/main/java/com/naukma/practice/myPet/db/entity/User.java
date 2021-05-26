package com.naukma.practice.myPet.db.entity;

import lombok.Data;

//@Entity
@Data
public class User {

//    @Id
    private Long id;
    private String login;
    private String password;
    private String role;
    private String email;

    public static User createUser(String login, String email, String password ) {
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("USER");
//        user.setAccess("disable");
        return user;
    }
}
