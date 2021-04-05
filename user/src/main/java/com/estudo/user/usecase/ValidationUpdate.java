package com.estudo.user.usecase;

import com.estudo.user.Entity.User;
import com.estudo.user.external.GetUserByID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationUpdate {

@Autowired
    GetUserByID getUserByID;

    public boolean execute(String email) {
        User user = getUserByID.execute(email);
        if (user.getEmail() == null) return false;
        return true;
    }
}
