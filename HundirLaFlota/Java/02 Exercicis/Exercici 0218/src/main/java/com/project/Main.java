package com.project;

import java.util.Arrays;

public class Main {

    public static boolean son_anagrama(String paraula1, String paraula2) {

        return false;
    }

    public static void main(String[] args) {
        String[][] paraules = {
            {"Mare", "Rema"},
            {"Porta", "Tropa"},
            {"Triangle", "Integral"},
            {"Sopa", "Posa"},
            {"Casa", "Sopa"},
            {"Gat", "Perro"}
        };

        for (String[] parell : paraules) {
            boolean resultat = son_anagrama(parell[0], parell[1]);
            System.out.printf("Són les paraules '%s' i '%s' anagrames? %b\n", parell[0], parell[1], resultat);
        }
    }
}
