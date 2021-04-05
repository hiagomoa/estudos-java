package com.estudo.user.usecase;

import com.estudo.user.Entity.User;
import com.estudo.user.external.database.InsertUserInDataBase;
import com.estudo.user.external.database.UpdateUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UpdateUser {


    @Autowired
    ValidationUpdate validationUpdate;
    @Autowired
    UpdateUserImpl updateUser;

    public void execute(String id, User user) throws Exception {

        boolean isPossible = validationUpdate.execute(id);
        System.out.println("O VALOR DE POSSIVEL EH: "+isPossible);
        if(isPossible){
            user.setId(id);
            updateUser.execute(user);
            return;
        }
        throw new Exception("Problemas na inserção do banco");
    }
}
