package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.DTO.OwnerDTO;
import com.naukma.practice.myPet.db.OwnerRepository;
import com.naukma.practice.myPet.db.UserRepository;
import com.naukma.practice.myPet.db.entity.Owner;
import com.naukma.practice.myPet.db.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("owner")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = {"/profile"})
    public String ownerProfilePage(Model model, HttpServletRequest request){

        String login = (String) request.getSession().getAttribute("userLogin");
//        System.out.println(login);
        Owner owner = ownerRepository.findOwnerByLogin(login).get();
        User user = userRepository.findUserByLogin(login).get();
        model.addAttribute("ownerInfo", OwnerDTO.createOwner(owner,user));
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
