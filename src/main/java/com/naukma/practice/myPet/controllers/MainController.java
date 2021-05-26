package com.naukma.practice.myPet.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainController {


    @GetMapping(path = {"/login"})
    public String loginPage() {
        return "login";
    }

    @GetMapping(path = {"/hello"})
    public String helloPage(){
        log.info("hello");
        return "index";
    }

    @GetMapping(path = {"/host/info"})
    public String hostPage(){
        log.info("host");
        return "host";
    }

    @GetMapping(path = {"/owner/info"})
    public String ownerPage(){
        log.info("owner");
        return "owner";
    }
}
