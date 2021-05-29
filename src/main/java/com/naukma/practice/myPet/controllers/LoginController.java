package com.naukma.practice.myPet.controllers;

import com.naukma.practice.myPet.services.AuthenticationServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private AuthenticationServiceInterface service;

    @GetMapping(path = "/redirect_after_login")
    public void test(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

        String role = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication().
                getAuthorities().stream().
                findAny().orElse(null)).
                toString();

        System.out.println(role);
        String route = service.loginUser(name, request);
        response.sendRedirect(route);
    }

    @GetMapping(path = {"/login"})
    public String loginPage() {
        return "login";
    }

}