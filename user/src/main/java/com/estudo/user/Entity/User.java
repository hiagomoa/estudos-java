package com.estudo.user.Entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;


    public User() {
        super();
    }

    public String  getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
