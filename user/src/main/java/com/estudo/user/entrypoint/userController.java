package com.estudo.user.entrypoint;

import com.estudo.user.Entity.User;
import com.estudo.user.external.database.GetUserByEmailAndPasswordImpl;
import com.estudo.user.usecase.IncludeUser;
import com.estudo.user.usecase.SearchAllUsers;
import com.estudo.user.usecase.UpdateUser;
import com.estudo.user.viewModel.ViewModelAutenticate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class userController {
    @Autowired
    SearchAllUsers searchAllUsers;

    @Autowired
    IncludeUser includeUser;

    @Autowired
    UpdateUser updateUser;

    @Autowired
    GetUserByEmailAndPasswordImpl getUserByEmailAndPassword;



    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getVaccineByName() {
        System.out.println("Get method with code:");
        final List<User> userList = searchAllUsers.execute();
        return ResponseEntity.ok().body(userList);
    }

    @PostMapping("/all1")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity insertUser(@RequestBody User userParam) {
        System.out.println("Get method INSERT");
        System.out.println("EMAIL: "+userParam.getEmail());
        try {
            User user = includeUser.execute(userParam);
            return ResponseEntity.ok().body(user);

        }catch (Exception e){
            return ResponseEntity.status(500).body("ERRO");
        }
    }


    @PostMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity updateUser(@RequestBody User userParam, @PathVariable String id) {
        System.out.println("VAR: "+id);
        try {
            updateUser.execute(id, userParam);
            return ResponseEntity.status(200).body("OK");

        }catch (Exception e){
            return ResponseEntity.status(500).body("ERRO");
        }
    }

    @GetMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity autentication(@RequestBody ViewModelAutenticate auth) {
        System.out.println("Get method with code:");
        User user = null;
        user = getUserByEmailAndPassword.execute(auth.getEmail(), auth.getPassword());
        if(user.getId() != null) {
            return ResponseEntity.ok().body(user);
        }
        else{
            return ResponseEntity.status(500).body("Usuario não encontrado");
        }
    }
}
