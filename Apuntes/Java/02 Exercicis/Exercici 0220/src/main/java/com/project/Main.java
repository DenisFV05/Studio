package com.project;

public class Main {

    public static double calculaOperacio(double num1, double num2, String operacio) {
        return 0.0;
    }

    public static void main(String[] args) {
        double[] num1 = {2, 3, 5, 10};
        double[] num2 = {3, 4, 2, 2};
        String[] operacions = {"suma", "multiplicació", "resta", "divisió"};

        for (int i = 0; i < operacions.length; i++) {
            double resultat = calculaOperacio(num1[i], num2[i], operacions[i]);
            System.out.printf("El resultat de %s entre %.2f i %.2f és %.2f\n", operacions[i], num1[i], num2[i], resultat);
        }
    }
}
