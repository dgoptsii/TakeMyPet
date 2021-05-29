package com.naukma.practice.myPet.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("host")
public class HostController {

    @GetMapping(path = {"/profile"})
    public String hostProfilePage(){
        log.info("host profile");
        return "host-profile";
    }

    @GetMapping(path = {"/profile/edit"})
    public String hostProfileEditPage(){
        log.info("host profile edit");
        return "host-profile-edit";
    }

    @GetMapping(path = {"/posts"})
    public String hostPostsPage(){
        log.info("host posts");
        return "host-posts";
    }

    @GetMapping(path = {"/posts/edit"})
    public String hostPostsEditPage(){
        log.info("host posts edit");
        return "host-posts-edit";
    }

    @GetMapping(path = {"/createPost"})
    public String hostCreatePostPage(){
        log.info("host create post");
        return "host-create-post";
    }

    @GetMapping(path = {"/contracts"})
    public String hostContractsPage(){
        log.info("host contracts");
        return "host-contracts";
    }

    @GetMapping(path = {"/contracts/{id}"})
    public String hostContractsIdPage(@PathVariable int id){
        log.info("host contracts "+id);
        return "host-contracts-id";
    }
}
