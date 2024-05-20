package com.example.musicapp.validator;

public class NameValidator {
    public static boolean isNameValid(String name){
        if (!name.matches("^[a-zA-Z]+$")) {
            System.out.println("Name is invalid.");
            return false;
        }
        return true;
    }
}
