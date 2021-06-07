package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.*;
import com.naukma.practice.myPet.db.DTO.HostDTO;
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
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;


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

            model.addAttribute("numberOfAnimals", animalRepository.count());
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
    public String hostPostsEditPage(Model model, @PathVariable Long id) throws NotFoundException {
        Post post;
        if (postRepository.findById(id).isPresent()) {
            post = postRepository.findById(id).get();
        } else {
            //TODO add custom exception
            throw new NotFoundException("Post with this id doesn't exist");
        }
        model.addAttribute("animals", animalRepository.findAll());
        model.addAttribute("post", post);
//        return new ModelAndView("host-posts-edit", "post", post);
        return "host-posts-edit";
    }


    @PostMapping(path = {"/posts/edit/{id}"})
    public void hostPostsEditAction(@PathVariable Long id,
                                    @RequestParam(defaultValue = "1", name = "maxDays") String maxDaysId,
                                    HttpServletRequest request, HttpServletResponse response) throws NotFoundException, IOException {

        int maxDays = Integer.parseInt(maxDaysId);

        if (maxDays > 0) {
            //error to page
            //redirect to /posts/edit/{id}

            postRepository.setMaxDays(id, maxDays);
            response.sendRedirect(request.getContextPath() + "/host/posts");
        }
        //redirect to posts page
    }

    @GetMapping(path = {"/createPost"})
    public String hostCreatePostPage(HttpServletRequest request) throws NotFoundException {
        log.info("host create post");
        String hostLogin = (String) request.getSession().getAttribute("userLogin");
        List<Animal> postAnimals = postRepository.findAllByHostLogin(hostLogin)
                .stream().map(Post::getAnimal).collect(Collectors.toList());
        List<Animal> animals = animalRepository.findAll();
        animals = animals.stream().filter(a -> !postAnimals.contains(a)).collect(Collectors.toList());
        request.setAttribute("animals", animals);
        return "host-create-post";
    }

    @PostMapping(path = {"/createPost"})
    public void hostCreatePostPageAction(@RequestParam(name = "days", defaultValue = "1") String days,
                                         @RequestParam(name = "pet") String pet,
                                         HttpServletRequest request, HttpServletResponse response) throws NotFoundException, IOException {
        log.info("host created post");

        String hostLogin = (String) request.getSession().getAttribute("userLogin");
        List<String> postAnimals = postRepository.findAllByHostLogin(hostLogin)
                .stream().map(p -> p.getAnimal().getName()).collect(Collectors.toList());
        List<String> animals = animalRepository.findAll().stream().map(Animal::getName).collect(Collectors.toList());
        animals = animals.stream().filter(a -> !postAnimals.contains(a)).collect(Collectors.toList());
        int maxDays = Integer.parseInt(days);
        Optional<Host> host = hostRepository.findHostByLogin(hostLogin);
        if (host.isPresent() && pet != null && animals.contains(pet) && maxDays > 0 && maxDays < 15) {
            log.info("creating post");
            Post post = new Post();
            log.info("searching " + hostLogin);
            post.setHost(host.get());
            log.info("found host");
            post.setAnimal(animalRepository.findByName(pet).get());
            log.info("found animal");
            post.setMaxDays(maxDays);
            post.setStatus("ACTIVE");
            postRepository.save(post);
            request.getSession().setAttribute("getAlert", "Created Post");
            response.sendRedirect(request.getContextPath() + "/host/posts");
        } else {
            //TODO throw exception redirect to error page
        }

    }

    @GetMapping(path = {"/contracts"})
    public String hostContractsPage(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "3") int size,
                                    @RequestParam(defaultValue = "ALL") String status,
                                    Model model, HttpServletRequest request) throws Exception {
        String login = (String) request.getSession().getAttribute("userLogin");
        if (page > 0) {
            page -= 1;
        }
        try {
            List<Contract> contracts;
            Pageable paging = PageRequest.of(page, size);


            Page<Contract> pageContracts = null;
            if(status.equals("ALL")){
                pageContracts
                        = contractRepository.findAllByHostLoginOrderByStartDateAsc(login, paging);
            } else {
                pageContracts
                        = contractRepository.findAllByHostLoginAndStatusOrderByStartDateAsc(login, status.toUpperCase(), paging);
            }


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
