package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository access interface for Owner
 */
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Override
    <S extends Owner> S save(S s);

    @Override
    Optional<Owner> findById(Long id);

    Optional<Owner> findOwnerByLogin(String login);

    Optional<Owner> findOwnerByPhone(String phone);

    @Override
    List<Owner> findAll();
}
