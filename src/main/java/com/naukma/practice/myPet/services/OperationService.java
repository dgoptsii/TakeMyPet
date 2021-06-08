package com.naukma.practice.myPet.services;

import com.naukma.practice.myPet.db.ContractRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Slf4j
public class OperationService {

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
                        request.getSession().setAttribute("SuccessMessage", "Send emergency message");
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
}
