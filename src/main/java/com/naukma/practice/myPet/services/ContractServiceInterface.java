package com.naukma.practice.myPet.services;

import com.naukma.practice.myPet.db.entity.Contract;
import javassist.NotFoundException;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public interface ContractServiceInterface {

    void changeContractStatus(Long id, String state, HttpServletRequest request) throws NotFoundException;



    /**
     * Finds and creates entity of contract from database
     * @param id of your contract
     * @return DTO entity of host
     */
    Contract getContractInfo(Long id) throws NotFoundException;

    void hostContractsInfo(int page, int size, String status, Model model, String login) throws Exception;
}
