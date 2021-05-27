package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.services.AuthenticationServiceInterface;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Login command.
 *
 * @author D.Kolesnikov
 */
@Controller
@Slf4j
public class RegistrationController {

    @Autowired
    private AuthenticationServiceInterface serviceInterface;


    @GetMapping(path = {"/registration"})
    public String registrationPage() {
        return "registration";
    }

    @PostMapping(path = {"/registration"})
    public void doRegistration(@RequestParam(name = "login") String login,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "password_confirmation") String password_confirm,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {

        String role = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication().
                getAuthorities().stream().
                findAny().orElse(null)).
                toString();

        System.out.println(role);
        serviceInterface.registrationUser(login, password, email, password_confirm, request);

        System.out.println("registration done");
        response.sendRedirect(request.getContextPath() + "/login");
    }
}