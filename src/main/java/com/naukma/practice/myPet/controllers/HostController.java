package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.ContractRepository;
import com.naukma.practice.myPet.db.DTO.HostDTO;
import com.naukma.practice.myPet.db.HostRepository;
import com.naukma.practice.myPet.db.PostRepository;
import com.naukma.practice.myPet.db.UserRepository;
import com.naukma.practice.myPet.db.entity.Contract;
import com.naukma.practice.myPet.db.entity.Host;
import com.naukma.practice.myPet.db.entity.Post;
import com.naukma.practice.myPet.db.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/host")
public class HostController {

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ContractRepository contractRepository;

    @GetMapping(path = {"/profile"})
    public String hostProfilePage(Model model,  HttpServletRequest request){

        String login = (String) request.getSession().getAttribute("userLogin");

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
    public String hostPostsPage(Model model){
       List<Post> posts = postRepository.findAll();
        System.out.println(posts.toString());
        if(posts.size()==0){
            model.addAttribute("message","Oops. No posts...");
        }else{
            model.addAttribute("postsList",posts);
        }
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
    public String hostContractsPage(Model model){
        log.info("host contracts");
        List<Contract> contracts = contractRepository.findAll();
        System.out.println(contracts.toString());
        if(contracts.size()==0){
            model.addAttribute("message","Oops. No contracts...");
        }else{
            model.addAttribute("contractsList",contracts);
        }
        return "host-contracts";
    }


    @GetMapping(path = {"/contracts/{id}"})
    public String hostContractsIdPage(@PathVariable Long id){
        log.info("host contracts "+id);
        return "host-contracts-id";
    }
}
