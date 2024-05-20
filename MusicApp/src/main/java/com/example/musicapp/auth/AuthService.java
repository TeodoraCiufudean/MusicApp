package com.example.musicapp.auth;

import com.example.musicapp.entity.Role;
import com.example.musicapp.entity.User;
import com.example.musicapp.repository.UserRepository;
import com.example.musicapp.service.JwtService;
import com.example.musicapp.validator.AgeValidator;
import com.example.musicapp.validator.EmailValidator;
import com.example.musicapp.validator.NameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.Name;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    public AuthentificationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .emailAddress(request.getEmailAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .age(request.getAge())
                .role(request.getRole())
                .build();
        //if(EmailValidator.isEmailValid(request.getEmailAddress()) && NameValidator.isNameValid(request.getFirstName())&&NameValidator.isNameValid(request.getLastName())&& AgeValidator.isAgeValid(request.getAge())) {
        //EmailValidator.isEmailValid(request.getEmailAddress());
        //NameValidator.isNameValid(request.getFirstName());
        //NameValidator.isNameValid(request.getLastName());
        repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthentificationResponse.builder()
                    .token(jwtToken)
                    .build();

        //return null;
    }

    public AuthentificationResponse authenticate(AuthenticationRequest request){
       manager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.getEmailAddress(),
                       request.getPassword()
               )
       );
       var user = repository.findByEmailAddress(request.getEmailAddress())
                .orElseThrow();
       var jwtToken = jwtService.generateToken(user);
       return AuthentificationResponse.builder()
               .token(jwtToken)
               .build();
    }

}
