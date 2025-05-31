// Main.java
package com.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introdueix una llista de números enters separats per comes:");
        String input = scanner.nextLine();
        List<Integer> numeros = Arrays.stream(input.split(","))
                                      .map(Integer::parseInt)
                                      .collect(Collectors.toList());

        int suma = calculaSuma(numeros);
        int maxim = calculaMaxim(numeros);
        int minim = calculaMinim(numeros);
        double mitjana = calculaMitjana(numeros);

        System.out.printf("Suma = %d, Màxim = %d, Mínim = %d, Mitjana = %.2f\n", suma, maxim, minim, mitjana);
    }

    public static int calculaSuma(List<Integer> numeros) {
    }

    public static int calculaMaxim(List<Integer> numeros) {
    }

    public static int calculaMinim(List<Integer> numeros) {
    }

    public static double calculaMitjana(List<Integer> numeros) {
    }
}
