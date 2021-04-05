package com.example.auth.services;

import com.example.auth.feignClient.UserFeignClient;
import com.example.auth.viewModel.ViewModelAutenticate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public void findByEmail(ViewModelAutenticate auth) {
        Object user = userFeignClient.autentication(auth).getBody();
        System.out.println("USER: " + user);
        if (user == null) {
            throw new IllegalArgumentException("Email not found");
        }
       // logger.info("Email found: " + email);
    }
}
