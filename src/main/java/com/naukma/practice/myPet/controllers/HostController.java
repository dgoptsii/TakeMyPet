package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.DTO.HostDTO;
import com.naukma.practice.myPet.db.entity.Animal;
import com.naukma.practice.myPet.exceptions.InvalidDataException;
import com.naukma.practice.myPet.services.ContractServiceInterface;
import com.naukma.practice.myPet.services.HostServiceInterface;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;


@Controller
@Slf4j
@RequestMapping("/host")
public class HostController {

    @Autowired
    private HostServiceInterface hostService;

    @Autowired
    private ContractServiceInterface contractService;

    @GetMapping(path = {"/profile"})
    public String hostProfilePage(Model model, HttpServletRequest request) throws NotFoundException {

        model.addAttribute("hostInfo", hostService.getHostInfo(request));
        return "host-profile";
    }

    @GetMapping(path = {"/profile/edit"})
    public ModelAndView hostProfileEditPage(Model model, HttpServletRequest request) throws NotFoundException {
        return new ModelAndView("host-profile-edit", "host", hostService.getHostInfo(request));
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
            hostService.editHostProfile(hostNew, request, response);
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

        hostService.getPostsInfo(page, size, model, login);
        return "host-posts";
    }

    @GetMapping(path = {"/posts/edit/{id}"})
    public String hostPostsEditPage(Model model, @PathVariable Long id) throws NotFoundException {
        hostService.getPostInfo(model, id);
        return "host-posts-edit";
    }

    @PostMapping(path = {"/posts/edit/{id}"})
    public void hostPostsEditAction(@PathVariable Long id,
                                    @RequestParam(defaultValue = "1", name = "maxDays") String maxDaysId,
                                    HttpServletRequest request, HttpServletResponse response) throws NotFoundException, IOException {

        int maxDays = Integer.parseInt(maxDaysId);
        hostService.editPost(id, request, response, maxDays);
    }

    @GetMapping(path = {"/createPost"})
    public String hostCreatePostPage(HttpServletRequest request) {
        List<Animal> animals = hostService.getAnimalsList(request);
        request.setAttribute("animals", animals);
        return "host-create-post";
    }

    @PostMapping(path = {"/createPost"})
    public void hostCreatePostPageAction(@RequestParam(name = "days", defaultValue = "1") String days,
                                         @RequestParam(name = "pet") String pet,
                                         HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidDataException {

        String hostLogin = (String) request.getSession().getAttribute("userLogin");

        List<String> animals = hostService.getAnimalsList(request).stream().map(Animal::getName).collect(Collectors.toList());

        hostService.createPost(days, pet, request, response, hostLogin, animals);

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
        contractService.hostContractsInfo(page, size, status, model, login);
        return "host-contracts";
    }

    @GetMapping(path = {"/contracts/{id}"})
    public String hostContractsIdPage(@PathVariable Long id, Model model) throws NotFoundException {

        model.addAttribute("contractInfo", contractService.getContractInfo(id));
        return "host-contracts-id";
    }

}
