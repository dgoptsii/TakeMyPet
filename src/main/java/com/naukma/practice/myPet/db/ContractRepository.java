package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.Contract;
import com.naukma.practice.myPet.db.entity.Host;
import com.naukma.practice.myPet.db.entity.Owner;
import com.naukma.practice.myPet.db.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Override
    <S extends Contract> S save(S s);

    @Override
    Optional<Contract> findById(Long id);


    //for /host/contracts
    Page<Contract> findAllByHostLogin(String login, Pageable pageable);

    //for /owner/contracts
    Page<Contract> findAllByOwnerLogin(String login, Pageable pageable);
//    Page<Post> findAll(Pageable pageable);

    List<Contract> findAllByOwnerLogin(String login);

    List<Contract> findAllByHostLogin(String login);

    List<Contract> findAllDistinctByHostAndStatus(Host host, String status);

    List<Contract> findAllDistinctByOwnerAndStatus(Owner owner, String status);

    List<Contract> findAllDistinctByHostAndEndDateAfterOrStartDateBefore(Host host, Date start, Date end);

    @Override
    List<Contract> findAll();
}
