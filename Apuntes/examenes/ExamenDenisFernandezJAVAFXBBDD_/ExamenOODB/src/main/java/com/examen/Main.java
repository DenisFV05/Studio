package com.examen;

import java.util.ArrayList;
import java.util.HashMap;

// Executa aquest programa amb:
// ./run.sh com.examen.Main

public class Main {

    private static ArrayList<Animal> animals = new ArrayList<Animal>();

    public static void main(String[] args) {
        AppData db = AppData.getInstance();
        db.connect("./data/animals.sqlite");

        loadAnimals();

        db.close();
    }


    public static void loadAnimals() {

        System.out.println("Carregant ocells");
        // TO DO: carregar la informació d'ocells de la base de dades a
        // ArrayList<Animal> animals
        
        String sql = String.format("SELECT * FROM aus");
        animals.add(db.query(sql)); 
        
        System.out.println("Carregant mamifers");
        // TO DO: carregar la informació de mamifers de la base de dades al
        // ArrayList<Animal> animals anterior

        String sql2 = String.format("SELECT * FROM mamifers");
        animals.add(db.query(sql2)); 
         

        System.out.println("Animals:");
        for (Animal animal : animals) {
            if (animal instanceof AnimalAu) {
                System.out.println("Au: " + animal);
            } else if (animal instanceof AnimalMamifer) {
                System.out.println("Mamífer: " + animal);
            } else {
                System.out.println("Animal: " + animal);
            }
        }        
    }  
}
