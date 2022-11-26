package com.naukma.practice.myPet.db;

import com.naukma.practice.myPet.db.entity.Contract;
import com.naukma.practice.myPet.db.entity.Host;
import com.naukma.practice.myPet.db.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


/**
 * Repository access interface for Contract
 */
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Override
    <S extends Contract> S save(S s);

    @Override
    Optional<Contract> findById(Long id);

    long countContractByStatusAndHostLogin(String status, String hostLogin);

    @Query("SELECT SUM(c.rating) FROM Contract c WHERE c.status = :status AND c.host.login = :hostLogin")
    int sumOfRatingByStatus(String status, String hostLogin);

    //for /host/contracts
    Page<Contract> findAllByHostLogin(String login, Pageable pageable);

    Page<Contract> findAllByHostLoginOrderByStartDateDesc(String login, Pageable pageable);

    //Page<Contract> findAllOrderByStartDateDescAndHostLogin(String login, Pageable pageable);

    Page<Contract> findAllByOwnerLoginOrderByStartDateDesc(String login, Pageable pageable);

    Page<Contract> findAllByHostLoginAndStatusOrderByStartDateDesc(String login, String status, Pageable pageable);

    Page<Contract> findAllByOwnerLoginAndStatusOrderByStartDateDesc(String login, String status, Pageable pageable);

    //for /owner/contracts
    Page<Contract> findAllByOwnerLogin(String login, Pageable pageable);
//    Page<Post> findAll(Pageable pageable);

    List<Contract> findAllByOwnerLogin(String login);

    List<Contract> findAllByHostLogin(String login);

    List<Contract> findAllDistinctByHostAndStatus(Host host, String status);

    List<Contract> findAllDistinctByOwnerAndStatus(Owner owner, String status);

    List<Contract> findAllDistinctByHostAndEndDateAfterOrStartDateBefore(Host host, Date start, Date end);

    @Modifying(flushAutomatically = true)
    @Transactional
    @Query("UPDATE Contract u set u.status = :status where u.id = :id")
    void updateStatus(@Param(value = "id") Long id, @Param(value = "status") String status);

    @Override
    List<Contract> findAll();
}
