package com.naukma.practice.myPet.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("owner")
public class OwnerController {

    @GetMapping(path = {"/profile"})
    public String ownerProfilePage(){
        log.info("owner profile");
        return "owner-profile";
    }

    @GetMapping(path = {"/profile/edit"})
    public String ownerProfileEditPage(){
        log.info("owner profile edit");
        return "owner-profile-edit";
    }

    @GetMapping(path = {"/posts"})
    public String ownerPostsPage(){
        log.info("owner posts");
        return "owner-posts";
    }

    @GetMapping(path = {"/posts/{id}"})
    public String ownerPostsIdPage(@PathVariable int id){
        log.info("owner posts id");
        return "owner-posts-id";
    }

    @GetMapping(path = {"/createContract"})
    public String ownerCreateContractPage(){
        log.info("owner create contract");
        return "owner-create-contract";
    }

    @GetMapping(path = {"/contracts"})
    public String ownerContractsPage(){
        log.info("owner contracts");
        return "owner-contracts";
    }

    @GetMapping(path = {"/contracts/{id}"})
    public String ownerContractsIdPage(@PathVariable int id){
        log.info("owner contracts "+id);
        return "owner-contracts-id";
    }

}
