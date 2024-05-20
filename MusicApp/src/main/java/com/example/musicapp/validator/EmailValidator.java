package com.example.musicapp.validator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    public static boolean isEmailValid(String email) {
        String[] parts = email.split("@");
        if (parts.length != 2) {
            //System.out.println("Email invalid.");
            return false;
        }
        String[] usernameDomain = parts[1].split("\\.");
        if (usernameDomain.length < 2) {
            //System.out.println("Email invalid.");
            return false;
        }
        return email.matches("^[a-zA-Z][a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
}
