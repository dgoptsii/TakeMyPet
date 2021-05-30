package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.ContractRepository;
import com.naukma.practice.myPet.db.DTO.OwnerDTO;
import com.naukma.practice.myPet.db.OwnerRepository;
import com.naukma.practice.myPet.db.PostRepository;
import com.naukma.practice.myPet.db.UserRepository;
import com.naukma.practice.myPet.db.entity.Contract;
import com.naukma.practice.myPet.db.entity.Owner;
import com.naukma.practice.myPet.db.entity.Post;
import com.naukma.practice.myPet.db.entity.User;
import javassist.NotFoundException;
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
@RequestMapping("owner")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ContractRepository contractRepository;

    @GetMapping(path = {"/profile"})
    public String ownerProfilePage(Model model, HttpServletRequest request){

        String login = (String) request.getSession().getAttribute("userLogin");
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
    public String ownerPostsPage(Model model){
        List<Post> posts = postRepository.findAll();
        System.out.println(posts.toString());
        if(posts.size()==0){
            model.addAttribute("message","Oops. No posts...");
        }else{
            model.addAttribute("postsList",posts);
        }
        return "owner-posts";
    }

    @GetMapping(path = {"/posts/{id}"})
    public String ownerPostsIdPage(@PathVariable Long id, Model model) throws NotFoundException {
        Post post;
        if(postRepository.findById(id).isPresent()){
            post = postRepository.findById(id).get();
        }else{
            //TODO add custom exception
            throw new NotFoundException("Post with this id doesn't exist");
        }
        model.addAttribute("postInfo",post);
        return "owner-posts-id";
    }

    @GetMapping(path = {"/createContract"})
    public String ownerCreateContractPage(){
        log.info("owner create contract");
        return "owner-create-contract";
    }

    @GetMapping(path = {"/contracts"})
    public String ownerContractsPage(Model model){
        List<Contract> contracts = contractRepository.findAll();
        System.out.println(contracts.toString());
        if(contracts.size()==0){
            model.addAttribute("message","Oops. No contracts...");
        }else{
            model.addAttribute("contractsList",contracts);
        }
        return "owner-contracts";
    }

    @GetMapping(path = {"/contracts/{id}"})
    public String ownerContractsIdPage(@PathVariable int id){
        log.info("owner contracts "+id);
        return "owner-contracts-id";
    }

}
