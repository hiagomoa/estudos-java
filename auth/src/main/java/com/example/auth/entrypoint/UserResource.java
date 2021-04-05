package com.example.auth.entrypoint;

import com.example.auth.services.UserService;
import com.example.auth.viewModel.ViewModelAutenticate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {
    @Autowired
    private UserService service;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void getVaccineByName() {
        System.out.println("Get method with code:");
    }

    @GetMapping(value = "/search")
    public ResponseEntity findByEmail(@RequestBody ViewModelAutenticate auth) {
        System.out.println("OPAAA");
        try {
            service.findByEmail(auth);
            return ResponseEntity.ok("OK");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
