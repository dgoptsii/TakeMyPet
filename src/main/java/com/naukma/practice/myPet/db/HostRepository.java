package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


/**
 * Repository access interface for Host
 */
public interface HostRepository extends JpaRepository<Host, Long> {
    @Override
    <S extends Host> S save(S s);

    @Override
    Optional<Host> findById(Long id);

    Optional<Host> findHostByLogin(String login);

    Optional<Host> findHostByPhone(String phone);


    @Modifying(flushAutomatically = true)
    @Transactional
    @Query("UPDATE Host h set h.rating = :rating where h.id = :id")
    void updateRating(@Param(value = "id") Long id, @Param(value = "rating") Double rating);

    @Override
    List<Host> findAll();
}
