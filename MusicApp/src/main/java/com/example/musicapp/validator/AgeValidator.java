package com.example.musicapp.validator;

public class AgeValidator {
    public static boolean isAgeValid (int age){
        if (age < 13){
            System.out.println("You are too young.");
            return false;
        }
        return true;
    }
}
