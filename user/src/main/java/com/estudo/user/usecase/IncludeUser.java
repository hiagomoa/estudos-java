package com.estudo.user.usecase;

import com.estudo.user.Entity.User;
import com.estudo.user.external.database.InsertUserInDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class IncludeUser {


    @Autowired
    ValidateInclusionUser validateInclusionUser;

    @Autowired
    InsertUserInDataBase insertUserInDataBase;

    public User execute(User user) throws Exception {

        boolean isPossible = validateInclusionUser.execute(user.getEmail());
        if(isPossible){
            user = insertUserInDataBase.execute(user);
            return user;
        }
        throw new Exception("Problemas na inserção do banco");
    }
}
