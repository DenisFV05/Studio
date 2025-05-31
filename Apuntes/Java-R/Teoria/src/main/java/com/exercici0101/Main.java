package com.exercici0101;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Escribe algo: ");
        String loquehadixo = scanner.nextLine();

        
        if (loquehadixo.trim().isEmpty()) {
            System.out.println("No has escrito nada.");
        }
        
        else if (loquehadixo.matches("-?\\d+(\\.\\d+)?")) {
            double numero = Double.parseDouble(loquehadixo);
            System.out.println("Has escrito un número: " + numero);
        }
        
        else if (loquehadixo.equalsIgnoreCase("salir")) {
            System.out.println("Has escrito salir, el programa termina.");
        }
        
        else {
            System.out.println("Has escrito un texto: " + loquehadixo);
        }

        scanner.close();

        
    }
}
