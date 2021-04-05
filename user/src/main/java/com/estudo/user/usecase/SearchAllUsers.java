package com.estudo.user.usecase;

import com.estudo.user.Entity.User;
import com.estudo.user.external.database.GetAllUsersInDataBaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchAllUsers {


    @Autowired
    GetAllUsersInDataBaseImpl getAllUsersInDataBase;

    public List<User> execute() {

        List<User> userListEmpty = new ArrayList<>();

        List<User> userList = getAllUsersInDataBase.findAll();

        if(userList.isEmpty()) {
            return userListEmpty;
        }
        return userList;
    }

}
