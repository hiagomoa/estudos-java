package com.example.auth.entrypoint;

import com.example.auth.services.UserService;
import com.example.auth.viewModel.ViewModelAutenticate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {
    @Autowired
    private UserService service;

    @CrossOrigin

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void getVaccineByName() {
        System.out.println("Get method with code:");
    }

    @CrossOrigin
    @PostMapping(value = "/search")
    public ResponseEntity findByEmail(@RequestBody ViewModelAutenticate auth) {
        System.out.println("OPAAA");

            ResponseEntity response = service.findByEmail(auth);
            if(response.getStatusCodeValue() == 200){
                System.out.println("CODIGO 200");
                return ResponseEntity.status(200).body(response.getBody());
            }
            return ResponseEntity.status(400).build();

    }
}
