package com.naukma.practice.myPet.db.DTO;

import com.naukma.practice.myPet.db.UserRepository;
import com.naukma.practice.myPet.db.entity.Owner;
import com.naukma.practice.myPet.db.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Data
public class OwnerDTO {

    @Autowired
    private UserRepository userRepository;

    private Long id;

    private String email;

    private String login;
    private String name;
    private String surname;
    private String phone;

    private String city;
    private String region;

    public static OwnerDTO createOwner(Owner owner, User user) {
        OwnerDTO ownerInfo = new OwnerDTO();

        ownerInfo.setId(owner.getId());
        ownerInfo.setLogin(owner.getLogin());
        ownerInfo.setEmail(user.getEmail());

        ownerInfo.setName(owner.getName());
        ownerInfo.setSurname(owner.getSurname());

        ownerInfo.setPhone(owner.getPhone());
        ownerInfo.setRegion(owner.getRegion());
        ownerInfo.setCity(owner.getCity());
        return ownerInfo;
    }

    public static Owner createOwnerFromDTO(OwnerDTO owner) {
        Owner newUser = new Owner();

        newUser.setId(owner.getId());
        newUser.setLogin(owner.getLogin());
        newUser.setName(owner.getName());
        newUser.setSurname(owner.getSurname());

        newUser.setPhone(owner.getPhone());
        newUser.setRegion(owner.getRegion());
        newUser.setCity(owner.getCity());
        return newUser;
    }

    public User createUserFromDTO(OwnerDTO owner,User oldUser) {
        oldUser.setLogin(owner.getLogin());
        oldUser.setEmail(owner.getEmail());
        return oldUser;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDTO ownerDTO = (OwnerDTO) o;
        return id.equals(ownerDTO.id) && email.equals(ownerDTO.email) && login.equals(ownerDTO.login)
                && name.equals(ownerDTO.name) && surname.equals(ownerDTO.surname)
                && phone.equals(ownerDTO.phone) && city.equals(ownerDTO.city) && region.equals(ownerDTO.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, surname);
    }
}
