package com.naukma.practice.myPet;

import com.naukma.practice.myPet.db.entity.User;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;


@UtilityClass
public class UserGenerator {

    public static List<User> generateUsers(Integer count) {
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {

            User user =User.createUser("login"+i,"user@email" + i,"password"+i);
            user.setId(null);
            userList.add(user);
        }
        System.out.println(userList);
        return userList;
    }
}
