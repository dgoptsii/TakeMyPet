package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.*;
import com.naukma.practice.myPet.db.DTO.HostDTO;
import com.naukma.practice.myPet.db.DTO.OwnerDTO;
import com.naukma.practice.myPet.db.entity.*;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@Slf4j
@RequestMapping("/host")
public class HostController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ContractRepository contractRepository;

    @GetMapping(path = {"/profile"})
    public String hostProfilePage(Model model, HttpServletRequest request) {

        String login = (String) request.getSession().getAttribute("userLogin");

        Host host = hostRepository.findHostByLogin(login).get();
        User user = userRepository.findUserByLogin(login).get();

        model.addAttribute("hostInfo", HostDTO.createHost(host, user));
        return "host-profile";
    }

    @GetMapping(path = {"/profile/edit"})
    public ModelAndView hostProfileEditPage(Model model, HttpServletRequest request) {
        log.info("owner profile edit");

        String login = (String) request.getSession().getAttribute("userLogin");
        Host host = hostRepository.findHostByLogin(login).get();
        User user = userRepository.findUserByLogin(login).get();

//        model.addAttribute("hostInfo", HostDTO.createHost(host, user));
        return new ModelAndView("host-profile-edit", "host", HostDTO.createHost(host, user));
//        return "host-profile-edit";
    }


    @PostMapping(path = {"/profile/edit"})
    public void hostProfileEditAction(@Valid @ModelAttribute("host") HostDTO hostNew,
                                      BindingResult result,
                                      HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("dodod");
        if (result.hasErrors()) {
            System.out.println("tototot");
            request.getSession().setAttribute("getAlert", "error");
            request.getSession().setAttribute("errorMessage", "Invalid input!");
            response.sendRedirect(request.getContextPath() + "/host/profile/edit");
        } else {
            System.out.println(hostNew);
            String login = (String) request.getSession().getAttribute("userLogin");

            Host host = hostRepository.findHostByLogin(login).get();
            User user = userRepository.findUserByLogin(login).get();

            HostDTO currentInfo = HostDTO.createHost(host, user);

            if (currentInfo.equals(hostNew)) {
                request.getSession().setAttribute("getAlert", "error");
                request.getSession().setAttribute("errorMessage", "You don't change nothing!");
                response.sendRedirect(request.getContextPath() + "/host/profile/edit");
            } else if (
                    (userRepository.findUserByLogin(hostNew.getLogin()).isPresent() && !currentInfo.getLogin().equals(hostNew.getLogin()))
                            || (userRepository.findUserByEmail(hostNew.getEmail()).isPresent() && !currentInfo.getEmail().equals(hostNew.getEmail()))
            ) {
                request.getSession().setAttribute("getAlert", "error");
                request.getSession().setAttribute("errorMessage", "User with this login/e-mail is already exist!");
                response.sendRedirect(request.getContextPath() + "/host/profile/edit");
            } else if (hostRepository.findHostByPhone(hostNew.getPhone()).isPresent() && !currentInfo.getPhone().equals(hostNew.getPhone())) {
                request.getSession().setAttribute("getAlert", "error");
                request.getSession().setAttribute("errorMessage", "User with this phone number is already exist!");
                response.sendRedirect(request.getContextPath() + "/host/profile/edit");
            } else {
                user.setLogin(hostNew.getLogin());
                user.setEmail(hostNew.getEmail());

                System.out.println("Updated user -> " + userRepository.save(user));
                host = HostDTO.createHostFromDTO(hostNew);
                System.out.println("Updated host -> " + hostRepository.save(host));
                request.getSession().setAttribute("userLogin", hostNew.getLogin());

                request.getSession().setAttribute("getAlert", "success");
                response.sendRedirect(request.getContextPath() + "/host/profile");
            }
        }
//        model.addAttribute("hostInfo", HostDTO.createHost(host, user));
//        return "host-profile-edit";

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

            pagePosts = postRepository.findAllByHostLogin(login, paging);

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

    @GetMapping(path = {"/posts/edit/{id}"})
    public ModelAndView hostPostsEditPage(Model model,@PathVariable Long id) throws NotFoundException {
        Post post;
        if (postRepository.findById(id).isPresent()) {
            post = postRepository.findById(id).get();
        } else {
            //TODO add custom exception
            throw new NotFoundException("Post with this id doesn't exist");
        }
        model.addAttribute("animals", animalRepository.findAll());
//        model.addAttribute("ownerInfo", OwnerDTO.createOwner(owner, user));
        return new ModelAndView("host-posts-edit", "post", post);
    }

    @GetMapping(path = {"/createPost"})
    public String hostCreatePostPage() throws NotFoundException {
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
            pageContracts = contractRepository.findAllByHostLogin(login, paging);

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
    public String hostContractsIdPage(@PathVariable Long id, Model model) throws NotFoundException {
        log.info("host contracts " + id);

        Contract contract;
        if (contractRepository.findById(id).isPresent()) {
            contract = contractRepository.findById(id).get();
        } else {
            //TODO add custom exception
            throw new NotFoundException("Error. Contract not exist! ");
        }
        model.addAttribute("contractInfo", contract);

        return "host-contracts-id";
    }
}
