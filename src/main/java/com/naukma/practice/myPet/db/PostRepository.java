package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    <S extends Post> S save(S s);

    @Override
    Optional<Post> findById(Long id);


    List<Post> findAllPostsByAnimal(Animal animal);

    List<Post> findAllDistinctByHostAndStatus(Host host, PostStatus status);

    List<Post> findAllDistinctByStatus(PostStatus status);

    @Override
    List<Post> findAll();
}
