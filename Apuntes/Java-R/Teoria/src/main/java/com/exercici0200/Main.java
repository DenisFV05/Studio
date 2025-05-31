package com.exercici0200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        // 1. STRING (cadena simple)
        String saludo = "Hola mundo";
        System.out.println("String: " + saludo);
        
        // 2. STRING[] (array de Strings)
        String[] colores = {"Rojo", "Verde", "Azul"};
        System.out.println("Array de colores:");
        for (String color : colores) {
            System.out.println("- " + color);
        }

        // 3. STRING[][] (array de arrays de Strings)
        String[][] calendario = {
            {"Lunes", "Martes"},
            {"Miércoles", "Jueves"}
        };
        System.out.println("Matriz de días:");
        for (int i = 0; i < calendario.length; i++) {
            System.out.println(Arrays.toString(calendario[i]));
        }

        // 4. ARRAYLIST (lista dinámica)
        ArrayList<String> listaColores = new ArrayList<>();
        listaColores.add("Rojo");
        listaColores.add("Verde");
        listaColores.add("Azul");
        listaColores.add(1, "Amarillo"); // Inserta en índice 1
        System.out.println("ArrayList: " + listaColores);

        // 5. MÉTODOS DE ARRAYLIST

        // .get()
        System.out.println("Color en posición 2: " + listaColores.get(2));

        // .set()
        listaColores.set(0, "Negro");
        System.out.println("Después de set(): " + listaColores);

        // .remove(index)
        listaColores.remove(1);
        System.out.println("Después de remove(1): " + listaColores);

        // .remove(object)
        listaColores.remove("Azul");
        System.out.println("Después de remove(\"Azul\"): " + listaColores);

        // .contains()
        System.out.println("¿Contiene Verde?: " + listaColores.contains("Verde"));

        // .indexOf() y .lastIndexOf()
        listaColores.add("Verde");
        System.out.println("IndexOf Verde: " + listaColores.indexOf("Verde"));
        System.out.println("LastIndexOf Verde: " + listaColores.lastIndexOf("Verde"));

        // .size()
        System.out.println("Tamaño de la lista: " + listaColores.size());

        // .isEmpty()
        System.out.println("¿Está vacía?: " + listaColores.isEmpty());

        // .subList()
        List<String> subLista = listaColores.subList(0, 2); // del 0 al 1
        System.out.println("Sublista (0 a 1): " + subLista);

        // .removeIf()
        listaColores.removeIf(color -> color.equals("Verde"));
        System.out.println("Después de removeIf(): " + listaColores);

        // .clear()
        listaColores.clear();
        System.out.println("Después de clear(): " + listaColores);

        // .toArray()
        ArrayList<String> listaNueva = new ArrayList<>(List.of("Rojo", "Verde", "Azul"));
        String[] arrayDesdeLista = listaNueva.toArray(new String[0]);
        System.out.println("Array desde lista: " + Arrays.toString(arrayDesdeLista));
    }
}
