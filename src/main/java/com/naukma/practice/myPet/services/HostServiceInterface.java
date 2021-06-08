package com.naukma.practice.myPet.services;

import com.naukma.practice.myPet.db.DTO.HostDTO;
import com.naukma.practice.myPet.db.entity.Animal;
import com.naukma.practice.myPet.exceptions.InvalidDataException;
import javassist.NotFoundException;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface HostServiceInterface {

    HostDTO getHostInfo(HttpServletRequest request) throws NotFoundException;

    void editHostProfile(HostDTO hostNew, HttpServletRequest request, HttpServletResponse response)
            throws NotFoundException, IOException;

    void getPostsInfo(int page, int size, Model model, String login) throws Exception;

    void getPostInfo(Model model, Long id) throws NotFoundException;

    void editPost(Long id, HttpServletRequest request, HttpServletResponse response, int maxDays)
            throws NotFoundException, IOException;

    List<Animal> getAnimalsList(HttpServletRequest request);

    void createPost(String days, String pet, HttpServletRequest request, HttpServletResponse response,
                    String hostLogin, List<String> animals) throws InvalidDataException, IOException;

}
