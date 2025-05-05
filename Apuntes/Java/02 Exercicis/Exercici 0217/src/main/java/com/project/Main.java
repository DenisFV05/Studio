package com.project;

public class Main {

    public static boolean es_palindrom(String text) {
        return false;
    }

    public static void main(String[] args) {
        String[] frases = {
            "Anul·la la lluna",
            "Atrapa'l o l'aparta",
            "No sap pas on",
            "Tramaran anar a Mart",
            "Un pop nu",
            "Aquesta frase no és un palíndrom",
            "Aquest tampoc no ho és"
        };

        for (String frase : frases) {
            boolean resultat = es_palindrom(frase);
            System.out.printf("És la frase '%s' un palindrom? %b\n", frase, resultat);
        }
    }
}
