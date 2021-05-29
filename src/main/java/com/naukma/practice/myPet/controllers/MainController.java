package com.naukma.practice.myPet.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class MainController {

    @GetMapping(path = {"/hello"})
    public String helloPage(){
        log.info("hello");
        return "index";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello() {
        return "homepage";
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
