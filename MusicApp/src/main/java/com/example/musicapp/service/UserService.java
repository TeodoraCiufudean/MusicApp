package com.example.musicapp.service;

import com.example.musicapp.entity.User;
import com.example.musicapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> viewAllUsers(){
        return (List<User>) this.userRepository.findAll();
    }

    public Optional<User> viewUserById(int id){
        return this.userRepository.findById(id);
    }
    public User insertUser(User user){
        return this.userRepository.save(user);
    }

    public void deleteUserById(int id){
        try{
            this.userRepository.deleteById(id);
            System.out.println("User with id " + id + " was deleted.");
        }catch (Exception e){
            System.out.println("Failed to delete user with id " + id);
        }
    }

}
