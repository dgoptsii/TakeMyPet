package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.Animal;
import com.naukma.practice.myPet.db.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    <S extends Post> S save(S s);

    @Override
    Optional<Post> findById(Long id);

    List<Post> findAllPostsByAnimal(Animal animal);

    @Override
    List<Post> findAll();
}
