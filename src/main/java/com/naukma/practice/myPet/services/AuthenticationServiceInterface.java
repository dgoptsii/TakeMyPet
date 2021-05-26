package com.naukma.practice.myPet.services;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationServiceInterface {
    String loginUser(String login,
                     HttpServletRequest request) throws Exception;

    void registrationUser(String login, String password, String email, String password_confirm,
                          HttpServletRequest request) throws Exception;

    boolean validateData(String login, String password);

    boolean validateData(String login, String password, String email, String password_confirm);
}
