package com.naukma.practice.myPet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.rmi.AccessException;

@Controller
public class ErrorPageController {

    @GetMapping("/noAccess")
    public void doError() throws Exception {
        throw new AccessException("You do not have permission to access the requested resource");
    }

}
