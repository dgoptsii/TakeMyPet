package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.Contract;
import com.naukma.practice.myPet.db.entity.ContractStatus;
import com.naukma.practice.myPet.db.entity.Host;
import com.naukma.practice.myPet.db.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Override
    <S extends Contract> S save(S s);

    @Override
    Optional<Contract> findById(Long id);

    Optional<Contract> findAllByOwner(Owner owner);

    List<Contract> findAllByHost(Host host);

    List<Contract> findAllDistinctByHostAndStatus(Host host, ContractStatus status);

    List<Contract> findAllDistinctByOwnerAndStatus(Owner owner, ContractStatus status);

    @Override
    List<Contract> findAll();
}
