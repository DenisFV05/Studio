package com.exercici0104;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escriu un número decimal:");
        float numflo = scanner.nextFloat();
        int valor0 = (int) numflo;
        scanner.nextLine();
        System.out.println("Float convertit a Int (valor0): " + valor0);
        
        
        String numstr = Integer.toString(valor0);
        System.out.println("Int convertit a String: " + numstr);

        System.out.println("Escriu un sol caràcter numèric: ");
        char caracter = scanner.nextLine().charAt(0);
        System.out.println("Caràcter llegit: " + caracter);

        int valor1 = caracter - '0';
        System.out.println("Char convertit a Int: " + valor1);

        System.out.println("La suma de valor0 i valor1 és: " + (valor0 + valor1));
        scanner.close();





    }
}
