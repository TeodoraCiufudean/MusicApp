package com.example.musicapp.controller;

import com.example.musicapp.auth.AuthService;
import com.example.musicapp.auth.AuthentificationResponse;
import com.example.musicapp.auth.RegisterRequest;
import com.example.musicapp.entity.Role;
import com.example.musicapp.entity.User;
import com.example.musicapp.service.UserService;
import com.example.musicapp.validator.AgeValidator;
import com.example.musicapp.validator.EmailValidator;
import com.example.musicapp.validator.NameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.xml.validation.Validator;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/getAllUsers")
    @ResponseBody
    public List<User> viewAllAlbums(){
        return this.userService.viewAllUsers();
    }

    @GetMapping("/getUserById")
    @ResponseBody
    public Optional<User> viewUserById(@RequestParam int id){ return this.userService.viewUserById(id); }

    @PostMapping("/insertUser")
    @ResponseBody
    public ResponseEntity <User> insertUser(@RequestBody User user) {
        if (hasAuthority()) {
            if (EmailValidator.isEmailValid(user.getEmailAddress()) && NameValidator.isNameValid(user.getFirstName()) && NameValidator.isNameValid(user.getLastName()) && AgeValidator.isAgeValid(user.getAge())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return ResponseEntity.ok(this.userService.insertUser(user));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/updateUser")
    @ResponseBody
    public User updateUser(@RequestBody User user){
        if(EmailValidator.isEmailValid(user.getEmailAddress())&& NameValidator.isNameValid(user.getFirstName())&& NameValidator.isNameValid(user.getLastName())&& AgeValidator.isAgeValid(user.getAge())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return this.userService.insertUser(user);
        }
        return null;
    }

    @DeleteMapping("/deleteUserById")
    @ResponseBody
    public ResponseEntity <User> deleteById(@RequestParam int id){
        if(hasAuthority()){
            this.userService.deleteUserById(id);
            ResponseEntity.ok(null);
            //this.userService.deleteUserById(id);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    private boolean hasAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(Role.ADMIN.name()));
    }
}

