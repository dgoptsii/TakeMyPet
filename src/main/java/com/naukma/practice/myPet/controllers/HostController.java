package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.*;
import com.naukma.practice.myPet.db.DTO.HostDTO;
import com.naukma.practice.myPet.db.entity.*;
import com.naukma.practice.myPet.exceptions.InvalidDataException;
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

    private HostDTO getHostInfo(HttpServletRequest request) throws NotFoundException {
        String login = (String) request.getSession().getAttribute("userLogin");
        Host host;
        User user;
        if (!hostRepository.findHostByLogin(login).isPresent() || !userRepository.findUserByLogin(login).isPresent()) {
            throw new NotFoundException("Not found user with this login!");
        } else {
            host = hostRepository.findHostByLogin(login).get();
            user = userRepository.findUserByLogin(login).get();
        }
        return HostDTO.createHost(host, user);
    }

    @GetMapping(path = {"/profile"})
    public String hostProfilePage(Model model, HttpServletRequest request) throws NotFoundException {

        model.addAttribute("hostInfo", getHostInfo(request));
        return "host-profile";
    }

    @GetMapping(path = {"/profile/edit"})
    public ModelAndView hostProfileEditPage(Model model, HttpServletRequest request) throws NotFoundException {
        return new ModelAndView("host-profile-edit", "host", getHostInfo(request));
    }

    @PostMapping(path = {"/profile/edit"})
    public void hostProfileEditAction(@Valid @ModelAttribute("host") HostDTO hostNew,
                                      BindingResult result,
                                      HttpServletRequest request, HttpServletResponse response) throws IOException, NotFoundException {

        if (result.hasErrors()) {
            request.getSession().setAttribute("getAlert", "error");
            request.getSession().setAttribute("errorMessage", "Invalid input!");
            response.sendRedirect(request.getContextPath() + "/host/profile/edit");
        } else {
            editHostProfile(hostNew, request, response);
        }
    }

    private void editHostProfile(HostDTO hostNew, HttpServletRequest request, HttpServletResponse response) throws NotFoundException, IOException {
        String login = (String) request.getSession().getAttribute("userLogin");
        Host host;
        User user;
        if (!hostRepository.findHostByLogin(login).isPresent() || !userRepository.findUserByLogin(login).isPresent()) {
            throw new NotFoundException("Not found user with this login!");
        } else {
            host = hostRepository.findHostByLogin(login).get();
            user = userRepository.findUserByLogin(login).get();
        }

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

    @GetMapping(path = {"/posts"})
    public String hostPostsPage(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "3") int size,
                                Model model, HttpServletRequest request) throws Exception {
        String login = (String) request.getSession().getAttribute("userLogin");
        if (page > 0) {
            page -= 1;
        }
        getPostsInfo(page, size, model, login);
        return "host-posts";
    }

    private void getPostsInfo(int page, int size, Model model, String login) throws Exception {
        try {
            List<Post> posts;
            Pageable paging = PageRequest.of(page, size);

            Page<Post> pagePosts;

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
    }

    @GetMapping(path = {"/posts/edit/{id}"})
    public String hostPostsEditPage(Model model, @PathVariable Long id) throws NotFoundException {
        getPostInfo(model, id);
        return "host-posts-edit";
    }

    private void getPostInfo(Model model, Long id) throws NotFoundException {
        Post post;
        if (postRepository.findById(id).isPresent()) {
            post = postRepository.findById(id).get();
        } else {
            throw new NotFoundException("Post with this id doesn't exist");
        }
        model.addAttribute("animals", animalRepository.findAll());
        model.addAttribute("post", post);
    }

    @PostMapping(path = {"/posts/edit/{id}"})
    public void hostPostsEditAction(@PathVariable Long id,
                                    @RequestParam(defaultValue = "1", name = "maxDays") String maxDaysId,
                                    HttpServletRequest request, HttpServletResponse response) throws NotFoundException, IOException {

        int maxDays = Integer.parseInt(maxDaysId);
        editPost(id, request, response, maxDays);
    }

    private void editPost(Long id, HttpServletRequest request, HttpServletResponse response, int maxDays) throws NotFoundException, IOException {
        Post post;
        if (postRepository.findById(id).isPresent()) {
            post = postRepository.findById(id).get();
        } else {

            throw new NotFoundException("Post with this id doesn't exist");
        }

        if (maxDays > 0 && maxDays < 15 && post.getMaxDays() != maxDays) {
            //error to page
            //redirect to /posts/edit/{id}
            postRepository.setMaxDays(id, maxDays);
            request.getSession().setAttribute("getAlert", "success");
            request.getSession().setAttribute("message", "Post edited!");
            response.sendRedirect(request.getContextPath() + "/host/posts");
        } else {

            request.getSession().setAttribute("getAlert", "error");
            request.getSession().setAttribute("errorMessage", "You don't change nothing.");
            response.sendRedirect(request.getContextPath() + "/host/posts/edit/" + id);
        }
    }

    @GetMapping(path = {"/createPost"})
    public String hostCreatePostPage(HttpServletRequest request) {
        List<Animal> animals = getAnimalsList(request);
        request.setAttribute("animals", animals);
        return "host-create-post";
    }

    private List<Animal> getAnimalsList(HttpServletRequest request) {
        String hostLogin = (String) request.getSession().getAttribute("userLogin");
        List<Animal> postAnimals = postRepository.findAllByHostLogin(hostLogin)
                .stream().map(Post::getAnimal).collect(Collectors.toList());
        List<Animal> animals = animalRepository.findAll();
        animals = animals.stream().filter(a -> !postAnimals.contains(a)).collect(Collectors.toList());
        return animals;
    }

    @PostMapping(path = {"/createPost"})
    public void hostCreatePostPageAction(@RequestParam(name = "days", defaultValue = "1") String days,
                                         @RequestParam(name = "pet") String pet,
                                         HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidDataException {

        String hostLogin = (String) request.getSession().getAttribute("userLogin");

        List<String> animals = getAnimalsList(request).stream().map(Animal::getName).collect(Collectors.toList());

        createPost(days, pet, request, response, hostLogin, animals);

    }


    private void createPost(String days, String pet, HttpServletRequest request, HttpServletResponse response,
                            String hostLogin, List<String> animals) throws InvalidDataException, IOException {
        int maxDays = Integer.parseInt(days);
        Optional<Host> host = hostRepository.findHostByLogin(hostLogin);
        if (host.isPresent() && pet != null && animals.contains(pet) && maxDays > 0 && maxDays < 15) {
            log.info("creating post");
            Post post = new Post();
            log.info("searching " + hostLogin);
            post.setHost(host.get());
            log.info("found host");
            if (animalRepository.findByName(pet).isPresent()) {
                post.setAnimal(animalRepository.findByName(pet).get());
            } else {
                throw new InvalidDataException("Invalid animal");
            }
            log.info("found animal");
            post.setMaxDays(maxDays);
            post.setStatus("ACTIVE");
            postRepository.save(post);
            request.getSession().setAttribute("getAlert", "success");
            request.getSession().setAttribute("message", "Post created!");
            response.sendRedirect(request.getContextPath() + "/host/posts");
        } else {
            throw new InvalidDataException("Invalid number of days");
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
        hostContractsInfo(page, size, status, model, login);
        return "host-contracts";
    }


    private void hostContractsInfo(int page, int size, String status, Model model, String login) throws Exception {
        try {
            List<Contract> contracts;
            Pageable paging = PageRequest.of(page, size);


            Page<Contract> pageContracts;
            if (status.equals("ALL")) {
                pageContracts
                        = contractRepository.findAllByHostLoginOrderByStartDateDesc(login, paging);
            } else {
                pageContracts
                        = contractRepository.findAllByHostLoginAndStatusOrderByStartDateDesc(login, status.toUpperCase(), paging);
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
            model.addAttribute("status", status);

        } catch (Exception e) {
            //TODO add custom exception
            throw new Exception("ERROR");
        }
    }

    @GetMapping(path = {"/contracts/{id}"})
    public String hostContractsIdPage(@PathVariable Long id, Model model) throws NotFoundException {

        getContractInfo(id, model);

        return "host-contracts-id";
    }

    private void getContractInfo(Long id, Model model) throws NotFoundException {
        Contract contract;
        if (contractRepository.findById(id).isPresent()) {
            contract = contractRepository.findById(id).get();
        } else {
            //TODO add custom exception
            throw new NotFoundException("Error. Contract not exist!");
        }
        model.addAttribute("contractInfo", contract);
    }

}
