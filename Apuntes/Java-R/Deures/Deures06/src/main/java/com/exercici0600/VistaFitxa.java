package com.exercici0600;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;

public class VistaFitxa {
    public Scene crearVista(Personatge p, Runnable alEditar) {
        Label nom = new Label("Nom: " + p.getNom());
        Label act = new Label("Activitat: " + p.getActivitat());
        Label data = new Label("Data de naixement: " + p.getDataNaixement());
        Label desc = new Label(p.getDescripcio());

        ImageView foto = new ImageView(new Image(getClass().getResourceAsStream("/assets/images0600/einstein.png")));
        foto.setFitWidth(200);
        foto.setPreserveRatio(true);

        Button btnEditar = new Button("Editar");
        btnEditar.setOnAction(e -> alEditar.run());

        VBox layout = new VBox(10, foto, nom, act, data, desc, btnEditar);
        return new Scene(layout, 400, 600);
    }
}