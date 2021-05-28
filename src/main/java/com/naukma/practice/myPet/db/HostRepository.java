package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.Host;
import com.naukma.practice.myPet.db.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HostRepository extends JpaRepository<Host, Long> {
    @Override
    <S extends Host> S save(S s);

    @Override
    Optional<Host> findById(Long id);

    Optional<Owner> findUserByLogin(String login);

    @Override
    List<Host> findAll();
}
