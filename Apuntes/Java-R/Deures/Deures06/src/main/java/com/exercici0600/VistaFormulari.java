package com.exercici0600;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class VistaFormulari {
    private TextField nomField, activitatField, dataNaixField, rutaFotoField;
    private TextArea descripcioArea;

    public Scene crearVista(Personatge p, Runnable alDesar) {
        nomField = new TextField(p.getNom());
        activitatField = new TextField(p.getActivitat());
        dataNaixField = new TextField(p.getDataNaixement());
        descripcioArea = new TextArea(p.getDescripcio());
        rutaFotoField = new TextField(p.getRutaFoto());

        Button btnDesar = new Button("Desar");
        btnDesar.setOnAction(e -> {
            p.setNom(nomField.getText());
            p.setActivitat(activitatField.getText());
            p.setDataNaixement(dataNaixField.getText());
            p.setDescripcio(descripcioArea.getText());
            p.setRutaFoto(rutaFotoField.getText());
            alDesar.run();
        });

        VBox layout = new VBox(10, nomField, activitatField, dataNaixField, descripcioArea, rutaFotoField, btnDesar);
        return new Scene(layout, 400, 600);
    }
}