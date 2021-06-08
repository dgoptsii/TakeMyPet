package com.naukma.practice.myPet.db;


import com.naukma.practice.myPet.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * Repository access interface for User
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    <S extends User> S save(S s);

    @Override
    Optional<User> findById(Long id);

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByEmail(String email);

    @Override
    List<User> findAll();


}
