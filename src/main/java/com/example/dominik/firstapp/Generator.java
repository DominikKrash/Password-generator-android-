package com.example.dominik.firstapp;

import java.util.Random;
/*
pierwotna wersja generatora hasel
 */
public class Generator {
    //sprawdza czy haslo jest wystarczajaco silne
    //pierwotna wersja sprawdza tylko czy wsytepuje przynajmniej 1 mala litera, 1 duza i 1 cyfra
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

    //Generujemy ciag liczb o zadanej dlugosci dopoki nie bedzie zgodny
    //z kryteriami podanymi wyzej
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
    //funkcja zbiera w grupe powyzsze
    public static String generateStrongPassword(final int len){
        String password = generatePassword(len);
        while(!isStrong(password)){
            password = generatePassword(len);
        }
        return password;
    }
}
