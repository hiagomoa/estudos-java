package com.estudo.user.external;

import com.estudo.user.Entity.User;

public interface GetUserByEmail {
    User execute(String email);
}
