package com.naukma.practice.myPet.db.DTO;

import com.naukma.practice.myPet.db.entity.Host;
import com.naukma.practice.myPet.db.entity.User;
import lombok.Data;

@Data
public class HostDTO {

    private Long id;

    private String login;
    private String name;
    private String surname;
    private String email;

    private String city;
    private String region; // WARNING ???? here was country
    private String address;
    private Double rating;

    private Integer maxAnimals;
    private String phone;


    public static HostDTO createHost(Host host, User user) {
        HostDTO hostInfo = new HostDTO();

        hostInfo.setId(host.getId());
        hostInfo.setLogin(host.getLogin());
        hostInfo.setEmail(user.getEmail());

        hostInfo.setName(host.getName());
        hostInfo.setSurname(host.getSurname());

        hostInfo.setRegion(host.getRegion());
        hostInfo.setCity(host.getCity());
        hostInfo.setAddress(host.getAddress());

        hostInfo.setRating(host.getRating());
        hostInfo.setMaxAnimals(host.getMaxAnimals());

        hostInfo.setPhone(host.getPhone());
        return hostInfo;
    }

    public static Host createHostFromDTO(HostDTO host) {
        Host newHost = new Host();

        newHost.setId(host.getId());
        newHost.setLogin(host.getLogin());
        newHost.setName(host.getName());
        newHost.setSurname(host.getSurname());

        newHost.setPhone(host.getPhone());
        newHost.setRegion(host.getRegion());
        newHost.setCity(host.getCity());
        return newHost;
    }
}
