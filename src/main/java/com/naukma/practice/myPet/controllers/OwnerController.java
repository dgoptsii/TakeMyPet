package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.*;
import com.naukma.practice.myPet.db.DTO.OwnerDTO;
import com.naukma.practice.myPet.db.entity.*;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("owner")
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private AnimalRepository animalRepository;

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
    public String ownerPostsPage(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size,
                                 @RequestParam(defaultValue = "0", name = "animal") String animal,
                                 @RequestParam(defaultValue = "1", name = "maxDays") String maxDaysId,
                                 Model model, HttpServletRequest request) throws Exception {

        String login = (String) request.getSession().getAttribute("userLogin");
        Owner owner = ownerRepository.findOwnerByLogin(login).get();

        String city = owner.getCity();
        String country = owner.getCountry();

        if (page > 0) {
            page -= 1;
        }
        long animalId = Long.parseLong(animal);
        int maxDays = Integer.parseInt(maxDaysId);
//        System.out.println("maxDays " + maxDays);
        try {
            List<Post> posts;
            Pageable paging = PageRequest.of(page, size);

            Page<Post> pagePosts = null;

            if (animalId != 0 && maxDays != 0) {
//                pagePosts = postRepository.findDistinctByAnimalIdAndMaxDaysGreaterThanEqual(animalId, maxDays, paging);
            } else if (animalId != 0) {
                pagePosts = postRepository.findDistinctByAnimalId(animalId, paging);
            } else if (maxDays != 0) {
                pagePosts = postRepository.findByMaxDaysGreaterThanEqual(maxDays, paging);
            } else {
                pagePosts = postRepository.findAll(paging);
            }

                posts = pagePosts.getContent();

                if (posts.size() == 0) {
                    model.addAttribute("message", "Oops. No results founded ...");
                } else {
                    posts = posts
                            .stream()
                            .filter(p -> p.getHost().getCity().equals(city)
                                    && p.getHost().getCountry().equals(country))
                            .collect(Collectors.toList());
                    model.addAttribute("posts", posts);
                }

                model.addAttribute("currentPage", page + 1);
                model.addAttribute("animals", animalRepository.findAll());
                model.addAttribute("animal", animal);
                model.addAttribute("maxDays", maxDays);
                model.addAttribute("totalItems", pagePosts.getTotalElements());
                model.addAttribute("totalPages", pagePosts.getTotalPages());

        } catch (Exception e) {
            //TODO add custom exception
            throw new Exception("ERROR");
        }

//        List<Post> posts = postRepository.findAll();
//        System.out.println(posts.toString());
//        if(posts.size()==0){
//            model.addAttribute("message","Oops. No posts...");
//        }else{
//            model.addAttribute("postsList",posts);
//        }
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
