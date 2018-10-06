package com.example.dominik.firstapp;

import android.support.annotation.NonNull;

import java.util.Random;

public class Generator {
    private static boolean isStrong(String pass){
        boolean uppercase = false;
        boolean lowercase = false;
        boolean number = false;
        for(int i = 0;i<pass.length();i++){
            if(Character.isDigit(pass.charAt(i)))number = true;
            else if(Character.isUpperCase(pass.charAt(i)))uppercase = true;
            else if(Character.isLowerCase(pass.charAt(i)))lowercase = true;
            if(uppercase && lowercase && number) return true;
        }
        return false;
    }
    public static String generateStrongPassword(final int len){
        String password = generatePassword(len);
        while(!isStrong(password)){
            password = generatePassword(len);
        }
        return password;
    }
    @NonNull
    private static String generatePassword(final int len){
        Random randomizer = new Random();
        StringBuilder sb = new StringBuilder("");
        char next = 'a';
        for(int i = len;i>0;i--){
            switch(randomizer.nextInt(3)) {
                case 0:
                    next = (char)(randomizer.nextInt(9)+48);
                    break;
                case 1:
                    next = (char)(randomizer.nextInt(25)+65);
                    break;
                case 2:
                    next = (char)(randomizer.nextInt(25)+97);
                    break;
            }
            sb.append(next);
        }
        return sb.toString();
    }
}
