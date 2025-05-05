package com.exercici0600;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Personatge personatge;
    private String rutaJson = "data/music.json";
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        personatge = GestorJson.carregar(rutaJson);
        mostrarFitxa();
    }

    private void mostrarFitxa() {
        VistaFitxa vista = new VistaFitxa();
        stage.setScene(vista.crearVista(personatge, this::mostrarFormulari));
        stage.setTitle("Fitxa del Personatge");
        stage.show();
    }

    private void mostrarFormulari() {
        VistaFormulari vista = new VistaFormulari();
        stage.setScene(vista.crearVista(personatge, () -> {
            try {
                GestorJson.desar(personatge, rutaJson);
                mostrarFitxa();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        stage.setTitle("Editar Personatge");
    }

    public static void main(String[] args) {
        launch(args);
    }
}