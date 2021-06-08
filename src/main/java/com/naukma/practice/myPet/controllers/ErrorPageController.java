package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.exceptions.InvalidDataException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.rmi.AccessException;

@Controller
public class ErrorPageController {

    @GetMapping("/noAccess")
    public void doError() throws Exception {
        throw new AccessException("You do not have permission to access the requested resource");
    }
    @GetMapping("/login/invalidPassword")
    public void loginPasswordError() throws Exception {
        throw new InvalidDataException("Password or login is not correct");
    }
}
