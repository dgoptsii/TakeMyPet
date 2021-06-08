package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    <S extends Post> S save(S s);

    @Override
    Optional<Post> findById(Long id);


    Page<Post> findAllByHostLogin(String login, Pageable pageable);
    List<Post> findAllByHostLogin(String login);

    Page<Post> findAll(Pageable pageable);
    Page<Post> findAllByStatus(String status, Pageable pageable);

    Page<Post> findDistinctByAnimalIdAndStatus(Long animal_id, String status, Pageable pageable);

    Page<Post> findDistinctByAnimalIdAndMaxDaysGreaterThanEqualAndStatus(Long animal_id, Integer maxDays, String status, Pageable pageable);

    Page<Post> findByMaxDaysGreaterThanEqualAndStatus(Integer maxDays,String status, Pageable pageable);


    @Modifying(flushAutomatically = true)
    @Transactional
    @Query("UPDATE Post p set p.status = :status where p.id = :id")
    void setStatus(@Param(value = "id") Long id, @Param(value = "status") String status);

    @Modifying(flushAutomatically = true)
    @Transactional
    @Query("UPDATE Post p set p.maxDays = :maxDays where p.id = :id")
    void setMaxDays(@Param(value = "id") Long id, @Param(value = "maxDays") Integer maxDays);

}
