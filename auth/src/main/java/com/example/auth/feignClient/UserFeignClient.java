package com.example.auth.feignClient;

import com.example.auth.viewModel.ViewModelAutenticate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@FeignClient(name = "user", path = "/users")
public interface  UserFeignClient {

    @GetMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity autentication(@RequestBody ViewModelAutenticate auth);

}
