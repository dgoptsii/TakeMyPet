package com.naukma.practice.myPet.services;

import com.naukma.practice.myPet.db.entity.Contract;
import javassist.NotFoundException;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public interface ContractServiceInterface {

    public void changeContractStatus(Long id, String state, HttpServletRequest request) throws NotFoundException;

    Contract getContractInfo(Long id) throws NotFoundException;

    void hostContractsInfo(int page, int size, String status, Model model, String login) throws Exception;
}
