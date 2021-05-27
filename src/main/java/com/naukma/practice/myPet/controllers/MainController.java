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

    //____________________________________________ HOST

    @GetMapping(path = {"/host/profile"})
    public String hostProfilePage(){
        log.info("host profile");
        return "host-profile";
    }

    @GetMapping(path = {"/host/profile/edit"})
    public String hostProfileEditPage(){
        log.info("host profile edit");
        return "host-profile-edit";
    }

    @GetMapping(path = {"/host/posts"})
    public String hostPostsPage(){
        log.info("host posts");
        return "host-posts";
    }

    @GetMapping(path = {"/host/posts/edit"})
    public String hostPostsEditPage(){
        log.info("host posts edit");
        return "host-posts-edit";
    }

    @GetMapping(path = {"/host/createPost"})
    public String hostCreatePostPage(){
        log.info("host create post");
        return "host-create-post";
    }

    @GetMapping(path = {"/host/contracts"})
    public String hostContractsPage(){
        log.info("host contracts");
        return "host-contracts";
    }

    @GetMapping(path = {"/host/contracts/id"})
    public String hostContractsIdPage(){
        log.info("host contracts id");
        return "host-contracts-id";
    }

    //____________________________________________ OWNER

    @GetMapping(path = {"/owner/profile"})
    public String ownerProfilePage(){
        log.info("owner profile");
        return "owner-profile";
    }

    @GetMapping(path = {"/owner/profile/edit"})
    public String ownerProfileEditPage(){
        log.info("owner profile edit");
        return "owner-profile-edit";
    }

    @GetMapping(path = {"/owner/posts"})
    public String ownerPostsPage(){
        log.info("owner posts");
        return "owner-posts";
    }

    @GetMapping(path = {"/owner/posts/id"})
    public String ownerPostsIdPage(){
        log.info("owner posts id");
        return "owner-posts-id";
    }

    @GetMapping(path = {"/owner/createContract"})
    public String ownerCreateContractPage(){
        log.info("owner create contract");
        return "owner-create-contract";
    }

    @GetMapping(path = {"/owner/contracts"})
    public String ownerContractsPage(){
        log.info("owner contracts");
        return "owner-contracts";
    }

    @GetMapping(path = {"/owner/contracts/id"})
    public String ownerContractsIdPage(){
        log.info("owner contracts id");
        return "owner-contracts-id";
    }

}
