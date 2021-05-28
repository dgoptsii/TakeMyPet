package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Override
    <S extends Animal> S save(S s);

    @Override
    Optional<Animal> findById(Long id);

    @Override
    List<Animal> findAll();
}
