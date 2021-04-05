package com.estudo.user.external;

import com.estudo.user.Entity.User;

public interface GetUserByID {
    User execute(String id);
}
