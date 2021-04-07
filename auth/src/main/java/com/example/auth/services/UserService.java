package com.example.auth.services;

import com.example.auth.entity.User;
import com.example.auth.feignClient.UserFeignClient;
import com.example.auth.viewModel.ViewModelAutenticate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public ResponseEntity findByEmail(ViewModelAutenticate auth) {
        System.out.println("ENTROU" + auth.getEmail()+" " +auth.getPassword() );
        try{
            User user = userFeignClient.autentication(auth).getBody();
            System.out.println("USER: " + user.getEmail() +" " +user.getId() +" " +user.getName());
            return ResponseEntity.status(200).body(user);
        }catch (Exception e){
            return ResponseEntity.status(400).body("Erro");
        }
    }

    public void findByEmail1(String email){
        User user = new User();
        user.setEmail(email);
        try{
            System.out.println("ENTROUUUUUUUUUUUUUUUUUUUUUUUU: " + email);
            ResponseEntity userResponse = userFeignClient.getUserByEmail(user);
            System.out.println("USER: " + userResponse);
            user = (User) userResponse.getBody();
            
        }catch (Exception e){
            System.out.println("ERRO");
        }
    }
}
