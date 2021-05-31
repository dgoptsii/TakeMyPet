package com.naukma.practice.myPet.db.DTO;

import com.naukma.practice.myPet.db.entity.Owner;
import com.naukma.practice.myPet.db.entity.User;
import lombok.Data;

@Data
public class OwnerDTO {

    private Long id;

    private String email;

    private String login;
    private String name;
    private String surname;
    private String phone;

    private String city;
    private String country;

    public static OwnerDTO createOwner(Owner owner, User user) {
        OwnerDTO ownerInfo = new OwnerDTO();

        ownerInfo.setId(owner.getId());
        ownerInfo.setLogin(owner.getLogin());
        ownerInfo.setEmail(user.getEmail());

        ownerInfo.setName(owner.getName());
        ownerInfo.setSurname(owner.getSurname());

        ownerInfo.setPhone(owner.getPhone());
        ownerInfo.setCountry(owner.getRegion());
        ownerInfo.setCity(owner.getCity());
        return ownerInfo;
    }

}
