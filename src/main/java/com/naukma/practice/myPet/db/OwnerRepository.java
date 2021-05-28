package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.Owner;
import com.naukma.practice.myPet.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Override
    <S extends Owner> S save(S s);

    @Override
    Optional<Owner> findById(Long id);

    Optional<Owner> findUserByLogin(String login);

    @Override
    List<Owner> findAll();
}
