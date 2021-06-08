package com.naukma.practice.myPet.services;

import com.naukma.practice.myPet.db.ContractRepository;
import com.naukma.practice.myPet.db.entity.Contract;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
public class ContractService implements ContractServiceInterface{

    @Autowired
    private ContractRepository contractRepository;

    public void changeContractStatus(Long id,String state,HttpServletRequest request) throws NotFoundException {
        //delete canceled contracts
        if (contractRepository.findById(id).isPresent()) {
            if(state.equals("CANCEL")){
                if(request!=null)
                    request.getSession().setAttribute("SuccessMessage", "Contract cancelled");
                contractRepository.delete(contractRepository.findById(id).get());
            }else{
                if(!contractRepository.findById(id).get().getStatus().equalsIgnoreCase(state)) {
                    contractRepository.updateStatus(id,state);
                    if(state.equalsIgnoreCase("EMERGENCY"))
                        request.getSession().setAttribute("SuccessMessage", "Sent emergency message");
                    if(state.equalsIgnoreCase("WAITING"))
                        request.getSession().setAttribute("SuccessMessage", "Contract accepted!");
                    System.out.println("Contract #"+id+" state is changed to "+state.toUpperCase());
                }
            }
            if(request!=null)
                request.getSession().setAttribute("getAlert","success");
        } else {
            //TODO add custom exception
            throw new NotFoundException("Error. Contract not exist!");
        }

    }

    public void hostContractsInfo(int page, int size, String status, Model model, String login) throws Exception {
        try {
            List<Contract> contracts;
            Pageable paging = PageRequest.of(page, size);

            Page<Contract> pageContracts;
            if (status.equals("ALL")) {
                pageContracts
                        = contractRepository.findAllByHostLoginOrderByStartDateDesc(login, paging);
            } else {
                pageContracts
                        = contractRepository.findAllByHostLoginAndStatusOrderByStartDateDesc(login, status.toUpperCase(), paging);
            }

            contracts = pageContracts.getContent();
            if (contracts.size() == 0) {
                model.addAttribute("message", "Oops. No contracts...");
            } else {
                model.addAttribute("contractsList", contracts);
            }
            model.addAttribute("currentPage", page + 1);
            model.addAttribute("totalItems", pageContracts.getTotalElements());
            model.addAttribute("totalPages", pageContracts.getTotalPages());
            model.addAttribute("status", status);

        } catch (Exception e) {
            //TODO add custom exception
            throw new Exception("ERROR");
        }
    }

    public Contract getContractInfo(Long id) throws NotFoundException {
        Contract contract;
        if (contractRepository.findById(id).isPresent()) {
            contract = contractRepository.findById(id).get();
        } else {
            throw new NotFoundException("Error. Contract not exist!");
        }
        return contract;
    }
}
