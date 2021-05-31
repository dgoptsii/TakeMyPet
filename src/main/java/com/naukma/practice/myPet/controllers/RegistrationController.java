package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.services.AuthenticationServiceInterface;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

                               @RequestParam(name = "name") String name,
                               @RequestParam(name = "surname") String surname,
                               @RequestParam(name = "telephone") String telephone,
                               @RequestParam(name = "region") String region,
                               @RequestParam(name = "city") String city,
                               @RequestParam(name = "radios") String role,

                               HttpServletRequest request, HttpServletResponse response) throws Exception {

//        String springRole = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication().
//                getAuthorities().stream().
//                findAny().orElse(null)).
//                toString();

//        System.out.println(springRole+" "+role);

        serviceInterface.registrationUser(login, password, email, password_confirm,
                                                name,surname,telephone, region,city,role,
                                                      request);

        System.out.println("registration done");
        response.sendRedirect(request.getContextPath() + "/login");
    }
}