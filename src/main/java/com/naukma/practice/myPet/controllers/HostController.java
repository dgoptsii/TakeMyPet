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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;


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
    public String hostPostsPage(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "3") int size,
                                Model model, HttpServletRequest request) throws Exception {
        String login = (String) request.getSession().getAttribute("userLogin");
        if (page > 0) {
            page -= 1;
        }
        try {
            List<Post> posts;
            Pageable paging = PageRequest.of(page, size);

            Page<Post> pagePosts = null;

            pagePosts = postRepository.findAllByHostLogin(login,paging);

            posts = pagePosts.getContent();
            if (posts.size() == 0) {
                model.addAttribute("message", "Oops. No posts...");
            } else {
                model.addAttribute("postsList", posts);
            }
            model.addAttribute("currentPage", page + 1);
            model.addAttribute("totalItems", pagePosts.getTotalElements());
            model.addAttribute("totalPages", pagePosts.getTotalPages());

        } catch (Exception e) {
            //TODO add custom exception
            throw new Exception("ERROR");
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
    public String hostContractsPage(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "3") int size,
                                    Model model, HttpServletRequest request) throws Exception {
        String login = (String) request.getSession().getAttribute("userLogin");
        if (page > 0) {
            page -= 1;
        }
        try {
            List<Contract> contracts;
            Pageable paging = PageRequest.of(page, size);

            Page<Contract> pageContracts = null;
            pageContracts = contractRepository.findAllByHostLogin(login,paging);

            contracts = pageContracts.getContent();
            if (contracts.size() == 0) {
                model.addAttribute("message", "Oops. No contracts...");
            } else {
                model.addAttribute("contractsList", contracts);
            }
            model.addAttribute("currentPage", page + 1);
            model.addAttribute("totalItems", pageContracts.getTotalElements());
            model.addAttribute("totalPages", pageContracts.getTotalPages());

        } catch (Exception e) {
            //TODO add custom exception
            throw new Exception("ERROR");
        }
        return "host-contracts";
    }


    @GetMapping(path = {"/contracts/{id}"})
    public String hostContractsIdPage(@PathVariable Long id){
        log.info("host contracts "+id);
        return "host-contracts-id";
    }
}
