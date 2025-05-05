package com.exercici0600;

import com.google.gson.Gson;
import java.io.*;

public class GestorJson {
    private static final Gson gson = new Gson();

    public static Personatge carregar(String ruta) throws IOException {
        try (Reader reader = new FileReader(ruta)) {
            return gson.fromJson(reader, Personatge.class);
        }
    }

    public static void desar(Personatge personatge, String ruta) throws IOException {
        try (Writer writer = new FileWriter(ruta)) {
            gson.toJson(personatge, writer);
        }
    }
}
