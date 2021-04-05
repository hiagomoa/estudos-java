package com.estudo.user.usecase;

import com.estudo.user.Entity.User;
import com.estudo.user.external.GetAllUsersInDataBase;
import com.estudo.user.external.GetUserByEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateInclusionUser {

    @Autowired
    GetUserByEmail getUserByEmail;

    public boolean execute(String email) {
        User user = getUserByEmail.execute(email);
        if (user.getEmail() == null) return true;
        return false;
    }
}
