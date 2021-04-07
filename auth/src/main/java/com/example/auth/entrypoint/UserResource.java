package com.example.auth.entrypoint;

import com.example.auth.services.UserService;
import com.example.auth.viewModel.ViewModelAutenticate;
import com.example.auth.viewModel.ViewModelToken;
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

    @CrossOrigin(value = "http://localhost:3000")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public void getVaccineByName() {
        System.out.println("Get method with code:");
    }

    @CrossOrigin(value = "http://localhost:3000")
    @PostMapping("/searcher")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity findByEmail(@RequestBody ViewModelAutenticate auth) {
        System.out.println("OPAAA" + auth.getEmail());

           ResponseEntity response = service.findByEmail(auth);
        System.out.println("RESPONSE: "+response.getStatusCodeValue());
            if(response.getStatusCodeValue() == 200){
                System.out.println("CODIGO 200" + response.getBody());
                ViewModelToken viewModelToken = new ViewModelToken();
                String token = "VIXXXXXXXXXXXXXXX";
                viewModelToken.setToken(token);
                viewModelToken.setData(response.getBody());
                return ResponseEntity.status(200).body(viewModelToken);
            }
            return ResponseEntity.status(400).build();

    }

    @CrossOrigin(value = "http://localhost:3000")
    @PostMapping("/search1")
    @ResponseStatus(HttpStatus.OK)
    public void teste() {
        System.out.println("SEARCH1");
        service.findByEmail1("hiagof@ciandt.com");
    }
}
