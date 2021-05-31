package com.naukma.practice.myPet.db;


import com.naukma.practice.myPet.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    <S extends User> S save(S s);

    @Override
    Optional<User> findById(Long id);

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByEmail(String email);

    @Override
    List<User> findAll();

    Optional<User> findDistinctByIdAndStatus(Long id, String status);

//    Integer countById();

//    @Query(value = "SELECT COUNT(id) from account WHERE role='USER' ", nativeQuery = true)
//    Integer countUsers();
//
//    @Query(value = "SELECT COUNT(id) from account", nativeQuery = true)
//    Integer countById();
//
//    @Query(value = "SELECT * FROM account WHERE role ='USER' limit ?1,?2", nativeQuery = true)
//    List<User> findUsersForAdmin(int one,int two);

}
