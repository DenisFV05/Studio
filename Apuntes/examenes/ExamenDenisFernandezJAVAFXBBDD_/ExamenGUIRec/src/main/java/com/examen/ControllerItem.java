package com.examen;

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ControllerItem {
    @FXML private AnchorPane anchor;
    @FXML private Label title, subtitle;
    @FXML private ImageView img;

    private String imageFile;

    public void setTitle(String t) { title.setText(t); }
    public void setSubtitle(String s) { subtitle.setText(s); }

    public void setImatge(String imagePath) {
        this.imageFile = imagePath;
        if (imagePath != null && !imagePath.isEmpty()) {
            File f = new File(System.getProperty("user.dir") + "/data/images/" + imagePath);
            img.setImage(new Image(f.toURI().toString()));
        } else {
            img.setImage(null);
        }
    }

    public String getTitle() {
        return title.getText();
    }

    public String getSubtitle() {
        return subtitle.getText();
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setSelected(boolean sel) {
        anchor.setStyle(sel
            ? "-fx-background-color: lightblue;"
            : "-fx-background-color: transparent;");
    }
}
