package com.naukma.practice.myPet.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
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

    @Column(name="contract_status")
    private String status;

    public static Contract createContract(Host host, Owner owner, Date startDate, Date endDate,
                                          Integer days, Double rating, Animal animal){
        Contract contract = new Contract();
        contract.setOwner(owner);
        contract.setHost(host);
        contract.setStartDate(startDate);
        contract.setEndDate(endDate);
        contract.setDays(days);
        contract.setRating(rating);
        contract.setAnimal(animal);

        return contract;


    }

}
