package com.estudo.user.external;

import com.estudo.user.Entity.User;

import java.util.List;

public interface GetAllUsersInDataBase {
    List<User> findAll();
}
