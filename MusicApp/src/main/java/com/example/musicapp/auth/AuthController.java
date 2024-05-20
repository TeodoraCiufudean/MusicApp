package com.example.musicapp.auth;

import com.example.musicapp.validator.AgeValidator;
import com.example.musicapp.validator.EmailValidator;
import com.example.musicapp.validator.NameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthentificationResponse> register(
            @RequestBody RegisterRequest request
    ){
        if(EmailValidator.isEmailValid(request.getEmailAddress()) && NameValidator.isNameValid(request.getFirstName())&&NameValidator.isNameValid(request.getLastName())&& AgeValidator.isAgeValid(request.getAge())) {
            return ResponseEntity.ok(service.register(request));
            //return ResponseEntity.ok("Registration completed.");
        }
        //return ResponseEntity.ok("Invalid input.");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
