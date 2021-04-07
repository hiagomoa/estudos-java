package com.example.auth.viewModel;


import org.springframework.http.ResponseEntity;

public class ViewModelToken {
 private String token;
 private Object data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
