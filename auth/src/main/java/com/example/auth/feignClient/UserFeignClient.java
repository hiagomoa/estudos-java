package com.example.auth.feignClient;

import com.example.auth.entity.User;
import com.example.auth.viewModel.ViewModelAutenticate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@FeignClient(name = "user", url = "localhost:8001", path = "/users")
public interface  UserFeignClient {

    @GetMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<User> autentication(@RequestBody ViewModelAutenticate auth);

    @GetMapping("/searchByEmail")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity getUserByEmail(@RequestBody User userEmail);

}
