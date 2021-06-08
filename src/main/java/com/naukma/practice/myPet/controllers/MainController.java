package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.ContractRepository;
import com.naukma.practice.myPet.db.HostRepository;
import com.naukma.practice.myPet.db.PostRepository;
import com.naukma.practice.myPet.db.entity.Contract;
import com.naukma.practice.myPet.exceptions.InvalidDataException;
import com.naukma.practice.myPet.services.OperationServer;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@Controller
@Slf4j
public class MainController {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private HostRepository hostRepository;


    @Autowired
    private OperationServer operationServer;

    @GetMapping(path = {"/hello"})
    public String helloPage() {
        log.info("hello");
        return "index";
    }

    @GetMapping(path = {"/logout"})
    public void execute(HttpSession session, HttpServletResponse response, HttpServletRequest request)
            throws IOException {

        if (session != null)
            session.invalidate();
        else {
            throw new AuthenticationException("No started sessions found");
        }

        log.debug("Command finished");
        response.sendRedirect(request.getContextPath() + "/login");
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello() {
        return "homepage";
    }

    @GetMapping(path = {"/host/info"})
    public String hostPage() {
        log.info("host");
        return "host";
    }

    @GetMapping(path = {"/owner/info"})
    public String ownerPage() {
        log.info("owner");
        return "owner";
    }

    @GetMapping(path = {"/owner/contracts/{state}/{id}"})
    public void ownerChangeContractStateAction(@PathVariable Long id, @PathVariable String state,
                                               HttpServletRequest request, HttpServletResponse response) throws IOException, NotFoundException {
        System.out.println("OWNER EDIT CONTRACT STATUS TO "+state);
        operationServer.changeContractStatus(id, state.toUpperCase(Locale.ROOT), request);
        response.sendRedirect(request.getContextPath() + "/owner/contracts");

    }

    @GetMapping(path = {"/host/contracts/{state}/{id}"})
    public void hostChangeContractStateAction(@PathVariable Long id, @PathVariable String state,
                                               HttpServletRequest request, HttpServletResponse response) throws IOException, NotFoundException {
        System.out.println("HOST EDIT CONTRACT STATUS TO "+state);

        operationServer.changeContractStatus(id, state.toUpperCase(Locale.ROOT), request);

//        request.getSession().setAttribute("getAlert", "success");
//        request.getSession().setAttribute("message", "Post blocked!");

        response.sendRedirect(request.getContextPath() + "/host/contracts");
    }
    @GetMapping(path = {"/posts/edit/{id}"})
    public void postChangeStatus(@PathVariable Long id,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(name = "status") String status,
                                 @RequestParam(defaultValue = "0", name = "animal") String animal,
                                 @RequestParam(defaultValue = "1", name = "maxDays") String maxDaysId,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException, NotFoundException, InvalidDataException {
        System.out.println("edit post status");
        if (postRepository.findById(id).isPresent()) {
            if (status.equals("active")) {
                postRepository.setStatus(id, "BLOCKED");
                request.getSession().setAttribute("message", "Post blocked!");
            } else if (status.equals("blocked")) {
                postRepository.setStatus(id, "ACTIVE");
                request.getSession().setAttribute("message", "Post now is active!");
            } else {
                throw new InvalidDataException("Invalid status!");
            }
            request.getSession().setAttribute("getAlert", "success");
            response.sendRedirect(request.getContextPath() + "/host/posts?page=" + page + "&animal=" + animal + "&maxDays=" + maxDaysId);
        } else {
            throw new NotFoundException("No post with this id! ");
        }
    }

    @GetMapping(path = {"/posts/delete/{id}"})
    public void deletePost(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException, NotFoundException {
        if (postRepository.findById(id).isPresent()) {
            postRepository.delete(postRepository.getOne(id));

            request.getSession().setAttribute("getAlert", "success");
            request.getSession().setAttribute("message", "Post deleted!");

            response.sendRedirect(request.getContextPath() + "/host/posts");
        } else {
            throw new NotFoundException("No post with this id! ");
        }
        request.getSession().setAttribute("getAlert", "success");

    }


    @PostMapping(path = {"/contract/rate/{id}"})
    public void rateContract(@PathVariable Long id, @RequestParam(name = "rating") String rating,
                             HttpServletRequest request, HttpServletResponse response) throws NotFoundException, IOException {
        System.out.println("rating");
        int rate = 0;
        //add catch if rate not number (ariphmetic error or smth)
        try {
            rate = Integer.parseInt(rating);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException();
        }
        Contract contract;
        if (contractRepository.findById(id).isPresent()) {
            contract = contractRepository.findById(id).get();
            contract.setRating(rate);
            contract.setStatus("RATED");
            contractRepository.save(contract);
            int amountOfRatedContracts
                    = (int) contractRepository.countContractByStatusAndHostLogin("RATED", contract.getHost().getLogin());
            int sumOfRatings
                    = contractRepository.sumOfRatingByStatus("RATED", contract.getHost().getLogin());

            double resRating = Math.round(((double) (sumOfRatings) / (double) (amountOfRatedContracts)) * 100.) / 100.;
            hostRepository.updateRating(contract.getHost().getId(), resRating);
            request.getSession().setAttribute("getAlert", "success");
        } else {
            throw new NotFoundException("No contract with this id! ");
        }
        response.sendRedirect(request.getContextPath() + "/owner/contracts");
    }

}
