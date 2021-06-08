package com.naukma.practice.myPet.services;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationServiceInterface {

    /**
     * Checks identity of user
     * @param request where data contains
     * @param login of logged user
     * @return where to forward
     */
    String loginUser(String login,
                     HttpServletRequest request) throws Exception;

    /**
     * Register new user
     * @param request where data lies
     */
    void registrationUser(String login, String password, String email, String password_confirm,
                          String name, String surname, String telephone,
                          String country, String city,
                          String role,
                          HttpServletRequest request) throws Exception;

    /**
     * Checks if login and password valid
     * @param login user's login
     * @param password user's password
     */
    boolean validateData(String login, String password);

    /**
     * Checks if user's data is valid
     */
    boolean validateData(String login, String password, String email, String password_confirm,
                         String name, String surname, String telephone,
                         String country, String city,
                         String role);
}
