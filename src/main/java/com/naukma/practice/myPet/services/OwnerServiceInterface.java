package com.naukma.practice.myPet.services;

import com.naukma.practice.myPet.db.DTO.OwnerDTO;
import com.naukma.practice.myPet.db.entity.Contract;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface OwnerServiceInterface {

    void createPostsPageOwner(int page, int size, String animal,
                              Model model, HttpServletRequest request, long animalId, int maxDays) throws Exception;

    void createOwnerContractPage(int page, int size, String status, Model model, String login) throws Exception;

    Page<Contract> getContracts(String status, String login, Pageable paging);

    void createContractPage(@PathVariable Long id, Model model) throws NotFoundException;

    void createPostPage(@PathVariable Long id, Model model) throws NotFoundException;

    void createContract(String startDate, String endDate, Long id,
                        HttpServletRequest request, HttpServletResponse response) throws NotFoundException, IOException;

    OwnerDTO findOwnerDto(String login);

    void editOwnerProfile(OwnerDTO ownerNew, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
