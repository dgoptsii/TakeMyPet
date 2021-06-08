package com.naukma.practice.myPet.services;

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
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HostService implements HostServiceInterface {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    public HostDTO getHostInfo(HttpServletRequest request) throws NotFoundException {
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

    public void editHostProfile(HostDTO hostNew,
                                HttpServletRequest request, HttpServletResponse response) throws NotFoundException, IOException {
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

    public void getPostsInfo(int page, int size, Model model, String login) throws Exception {
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

    public void getPostInfo(Model model, Long id) throws NotFoundException {
        Post post;
        if (postRepository.findById(id).isPresent()) {
            post = postRepository.findById(id).get();
        } else {
            throw new NotFoundException("Post with this id doesn't exist");
        }
        model.addAttribute("animals", animalRepository.findAll());
        model.addAttribute("post", post);
    }

    public void editPost(Long id, HttpServletRequest request, HttpServletResponse response, int maxDays) throws NotFoundException, IOException {
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

    public List<Animal> getAnimalsList(HttpServletRequest request) {
        String hostLogin = (String) request.getSession().getAttribute("userLogin");
        List<Animal> postAnimals = postRepository.findAllByHostLogin(hostLogin)
                .stream().map(Post::getAnimal).collect(Collectors.toList());
        List<Animal> animals = animalRepository.findAll();
        animals = animals.stream().filter(a -> !postAnimals.contains(a)).collect(Collectors.toList());
        return animals;
    }

    public void createPost(String days, String pet, HttpServletRequest request, HttpServletResponse response,
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

}
