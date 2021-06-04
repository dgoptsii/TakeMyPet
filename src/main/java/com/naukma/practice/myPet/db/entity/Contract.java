package com.naukma.practice.myPet.db.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@DynamicUpdate
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private Integer days;

    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @Column(name = "contract_status")
    private String status;

    public static Contract createContract(Post post, Owner owner, Date startDate, Date endDate, Integer days) {
        Contract contract = new Contract();
        contract.setOwner(owner);
        contract.setHost(post.getHost());
        contract.setStartDate(startDate);
        contract.setEndDate(endDate);
        contract.setDays(days);
        contract.setRating(post.getHost().getRating());
        contract.setAnimal(post.getAnimal());
        contract.setStatus("NEW");
        return contract;
    }

}
