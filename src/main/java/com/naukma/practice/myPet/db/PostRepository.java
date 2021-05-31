package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    <S extends Post> S save(S s);

    @Override
    Optional<Post> findById(Long id);


    Page<Post> findAllByHostLogin(String login, Pageable pageable);
    Page<Post> findAll(Pageable pageable);

    Page<Post> findDistinctByAnimalId(Long animal_id, Pageable pageable);
    Page<Post> findDistinctByAnimalIdAndMaxDaysGreaterThanEqual(Long animal_id, Integer maxDays, Pageable pageable);
    Page<Post> findByMaxDaysGreaterThanEqual(Integer maxDays, Pageable pageable);
//    List<Post> findAllPostsByAnimal(Animal animal);

//    List<Post> findAllDistinctByHostAndStatus(Host host, PostStatus status);

//    List<Post> findAllDistinctByStatus(PostStatus status);

}
