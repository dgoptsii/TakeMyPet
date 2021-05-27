package com.naukma.practice.myPet;

import com.naukma.practice.myPet.db.UserRepository;
import com.naukma.practice.myPet.db.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback
public class UserRepositoryTestIT{

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private UserRepository sut;

    @Test
    public void shouldFindAllUsers() {
        //GIVEN
        List<User> users = UserGenerator.generateUsers(5);
        users.forEach(u-> entityManager.persist(u));
        //WHEN
        List<User> userList = sut.findAll();

        //THEN
//        assertEquals(sut.countAllById(), userList.size());
    }

    @Test
    public void shouldFindUserById() {
        //GIVEN
        User user = UserGenerator.generateUsers(1).get(0);
        entityManager.persist(user);
        //WHEN
        User expectedUser = sut.findById(user.getId()).get();
        //THEN
        assertNotNull(expectedUser);
        assertEquals(expectedUser.getId(), user.getId());
    }

    @Test
    public void shouldThrowUserNotFoundException() {
        //GIVEN
        //WHEN
        System.out.println(sut.findById((long) 1000).get());
        //THEN
        assertNull( sut.findById((long) 1000).get());
    }

}
