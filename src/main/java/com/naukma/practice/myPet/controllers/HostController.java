package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.DTO.HostDTO;
import com.naukma.practice.myPet.db.HostRepository;
import com.naukma.practice.myPet.db.UserRepository;
import com.naukma.practice.myPet.db.entity.Host;
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
@RequestMapping("/host")
public class HostController {

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = {"/profile"})
    public String hostProfilePage(Model model,  HttpServletRequest request){

        String login = (String) request.getSession().getAttribute("userLogin");
//        System.out.println(login);
        Host host = hostRepository.findHostByLogin(login).get();
        User user = userRepository.findUserByLogin(login).get();
        model.addAttribute("hostInfo", HostDTO.createHost(host,user));
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
