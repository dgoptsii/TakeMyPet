package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.DTO.OwnerDTO;
import com.naukma.practice.myPet.services.ContractServiceInterface;
import com.naukma.practice.myPet.services.OwnerServiceInterface;
import javassist.NotFoundException;
import lombok.SneakyThrows;
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

@Controller
@Slf4j
@RequestMapping("owner")
public class OwnerController {


    @Autowired
    private ContractServiceInterface contractService;

    @Autowired
    private OwnerServiceInterface ownerService;


    @GetMapping(path = {"/profile"})
    public String ownerProfilePage(Model model, HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute("userLogin");
        model.addAttribute("ownerInfo", ownerService.findOwnerDto(login));
        return "owner-profile";
    }


    @GetMapping(path = {"/profile/edit"})
    public ModelAndView ownerProfileEditPage(HttpServletRequest request) {
        log.info("owner profile edit");

        String login = (String) request.getSession().getAttribute("userLogin");

        return new ModelAndView("owner-profile-edit", "owner", ownerService.findOwnerDto(login));
    }

    @PostMapping(path = {"/profile/edit"})
    public void ownerProfileEditAction(@Valid @ModelAttribute("owner") OwnerDTO ownerNew,
                                       BindingResult result,
                                       HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (result.hasErrors()) {

            request.getSession().setAttribute("getAlert", "error");
            request.getSession().setAttribute("errorMessage", "Invalid input!");
            response.sendRedirect(request.getContextPath() + "/owner/profile/edit");
        } else {
            ownerService.editOwnerProfile(ownerNew, request, response);
        }
//        model.addAttribute("ownerInfo", OwnerDTO.createOwner(owner, user));
//        return "owner-profile-edit";

    }


    @GetMapping(path = {"/posts"})
    public String ownerPostsPage(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size,
                                 @RequestParam(defaultValue = "0", name = "animal") String animal,
                                 @RequestParam(defaultValue = "1", name = "maxDays") String maxDaysId,
                                 Model model, HttpServletRequest request) throws Exception {

        if (page > 0) {
            page -= 1;
        }
        long animalId = Long.parseLong(animal);
        int maxDays = Integer.parseInt(maxDaysId);

        if (maxDays < 0) {
            maxDays = 1;
        }
        if (animalId < 0) {
            animalId = 0;
        }

        ownerService.createPostsPageOwner(page, size, animal, model, request, animalId, maxDays);

        return "owner-posts";
    }


    @GetMapping(path = {"/posts/{id}"})
    public String ownerPostsIdPage(@PathVariable Long id, Model model) throws NotFoundException {
        ownerService.createPostPage(id, model);
        return "owner-posts-id";
    }


    @GetMapping(path = {"/createContract/{id}"})
    public String ownerCreateContractPage(@PathVariable Long id, Model model,
                                          @RequestParam(name = "startDate", required = false) String startDate,
                                          @RequestParam(name = "endDate", required = false) String endDate) throws NotFoundException {
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        log.info("owner create contract " + id);
        ownerService.createContractPage(id, model);
        return "owner-create-contract";
    }


    @SneakyThrows
    @PostMapping(path = {"/createContract/{id}"})
    public void ownerCreateContractAction(@RequestParam(name = "startDate") String startDate,
                                          @RequestParam(name = "endDate") String endDate,
                                          @PathVariable Long id,
                                          HttpServletRequest request, HttpServletResponse response) {
        if (startDate.isEmpty() || endDate.isEmpty()) {
            request.getSession().setAttribute("getAlert", "error");
            request.getSession().setAttribute("errorMessage", "You need to select dates.");
            response.sendRedirect(request.getContextPath() + "/owner/createContract/" + id + "/?startDate=" + startDate + "&endDate=" + endDate);
            return;
        }
        ownerService.createContract(startDate, endDate, id, request, response);

    }


    @GetMapping(path = {"/contracts"})
    public String ownerContractsPage(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "3") int size,
                                     @RequestParam(defaultValue = "ALL") String status,
                                     Model model, HttpServletRequest request) throws Exception {
        String login = (String) request.getSession().getAttribute("userLogin");
        if (page > 0) {
            page -= 1;
        }
        ownerService.createOwnerContractPage(page, size, status, model, login);
        return "owner-contracts";
    }


    @GetMapping(path = {"/contracts/{id}"})
    public String ownerContractsIdPage(@PathVariable Long id, Model model, HttpServletRequest request) throws NotFoundException {

        log.info("owner contracts " + id);

        model.addAttribute("contractInfo", contractService.getContractInfo(id));

        String login = (String) request.getSession().getAttribute("userLogin");

        model.addAttribute("ownerInfo", ownerService.findOwnerDto(login));
        return "owner-contracts-id";
    }


}