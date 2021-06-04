package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.db.ContractRepository;
import com.naukma.practice.myPet.db.PostRepository;
import com.naukma.practice.myPet.services.AuthenticationServiceInterface;
import com.naukma.practice.myPet.services.OperationServer;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private OperationServer operationServer;

    @GetMapping(path = {"/hello"})
    public String helloPage(){
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
        response.sendRedirect(request.getContextPath() +"/login");
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello() {
        return "homepage";
    }

    @GetMapping(path = {"/host/info"})
    public String hostPage(){
        log.info("host");
        return "host";
    }

    @GetMapping(path = {"/owner/info"})
    public String ownerPage(){
        log.info("owner");
        return "owner";
    }

    @GetMapping(path = {"/contracts/{state}/{id}"})
    public void ownerChangeContractStateAction(@PathVariable Long id, @PathVariable String state,
                                               HttpServletRequest request, HttpServletResponse response) throws IOException, NotFoundException {

        operationServer.changeContractStatus(id,state,request);
        response.sendRedirect(request.getContextPath()+"/owner/contracts");
    }

    @GetMapping(path = {"/posts/delete/{id}"})
    public void deletePost(@PathVariable Long id,HttpServletRequest request, HttpServletResponse response) throws IOException {
        postRepository.delete(postRepository.getOne(id));
        response.sendRedirect(request.getContextPath()+"/host/posts");
    }

}
