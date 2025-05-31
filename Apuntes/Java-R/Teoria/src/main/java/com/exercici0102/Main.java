package com.exercici0102;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Di un número:");
        int num = scanner.nextInt();

        System.out.println("El doble del número és:" + (num * 2) + "\nEl triple és:" + (num * 3) + "\nLa mitad és:" + (num / 2));
        scanner.close();
    }
}
