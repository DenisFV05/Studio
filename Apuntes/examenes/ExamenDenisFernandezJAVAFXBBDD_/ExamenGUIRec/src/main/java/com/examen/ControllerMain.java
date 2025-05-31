package com.examen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControllerMain implements Initializable {

    @FXML private VBox list;
    @FXML private Button buttonSelectFile, buttonAdd, buttonUpdate, buttonDelete;
    @FXML private ImageView imgForm;
    @FXML private TextField album, author;

    private List<ControllerItem> items = new ArrayList<>();
    private String imagePath = "";
    private int selectedIndex = -1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[][] data = {
            {"Sgt. Pepper's Lonely Hearts Club Band","The Beatles","peeper.png"},
            {"Pet Sounds","The Beach Boys","pet.png"},
            {"Rumours","Fleetwood Mac","rumours.png"},
            {"Hotel California","The Eagles","hotelcalifornia.png"},
            {"Thriller","Michael Jackson","thriller.png"},
            {"Like a Prayer","Madonna","like.png"},
            {"Sounds of distant earth","Mike Oldfield","sounds.png"},
            {"Hotel","Moby","hotel.png"},
            {"1989","Taylor Swift","1989.png"},
            {"Future Nostalgia","Dua Lipa","future.png"},
            {"÷ (Divide)","Ed Sheeran","divide.png"}
        };
        for (String[] d : data) addItem(d[0], d[1], d[2]);
        refreshButtons();
    }

    private void addItem(String t, String s, String imgFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/viewItem.fxml"));
            Parent node = loader.load();
            ControllerItem ci = loader.getController();
            ci.setTitle(t);
            ci.setSubtitle(s);
            ci.setImatge(imgFile);
            items.add(ci);
            int idx = items.size() - 1;
            node.setOnMouseClicked(e -> selectItem(idx));
            list.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectItem(int idx) {
        // TO DO: Escollir només l'element 'idx' a la llista de l'esquerra
        // si 'idx' coincideix amb l'element seleccionat aleshores es 'desselecciona'
        
        // Seleccionar solo el elemento idx de la izquierda, si idx coincide con el elemento actualmente seleccionado se deselecciona

        // setSelected() / selectedIndex ???
    
    }

    @FXML void add(javafx.event.ActionEvent ev) {
        // TO DO: Si no hi ha cap element seleccionat
        // afegir la informació del formulari com un nou element a la llista de l'esquerra

        // Si no hay ningun elemento seleccionado, añadir la información del formulario como nuevo elemento en la lista de la izquierda
        if (selectedIndex == -1) {
            addItem(getTitle(), getSubtitle(), getImageFile());
            clearForm();
            refreshButtons();   }
    
    }
    
    @FXML void update(javafx.event.ActionEvent ev) {
        // TO DO: Si hi ha un element seleccionat
        // modificar l'element seleccionat amb les noves dades del formulari

        // Si hay un elemento seleccionado, modificar el elemento seleccionado con los nuevos datos
        if (selectedIndex != -1) {
            // Modificar un elemento seleccionado ???
            clearForm();
            refreshButtons();   }
    }
    
    @FXML void delete(javafx.event.ActionEvent ev) {
        // TO DO: Si hi ha un element seleccionat
        // esborrar-lo l'element seleccionat de la llista de l'esquerra

        // Si hay un elemento seleccionado, borrarlo de la lista de la izquierda
        if (selectedIndex != -1) {
            // Eliminar el elemento seleccionado ???
            clearForm();
            refreshButtons();   }
    }

    /**
     * Opens a {@link FileChooser} so the user can pick an image file.<br>
     * The chosen file is copied to <code>data/images/</code>, its relative path
     * saved to {@code imagePath}, and a preview is shown in {@code imgForm}.
     *
     * @param ev the action fired by the “Select File” button
     */
    @FXML void selectFile(javafx.event.ActionEvent ev) {
        Stage st = (Stage) buttonSelectFile.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files","*.png","*.jpg","*.jpeg"));
        File sf = fc.showOpenDialog(st);
        if (sf != null) {
            try {
                File df = new File(System.getProperty("user.dir") + "/data/images/" + sf.getName());
                Files.copy(sf.toPath(), df.toPath(), StandardCopyOption.REPLACE_EXISTING);
                imagePath = "data/images/" + sf.getName();
                imgForm.setImage(new Image(df.toURI().toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Resets all form fields and image preview and clears the current
     * selection state by setting {@code selectedIndex} to –1.
     */
    private void clearForm() {
        album.clear();
        author.clear();
        imgForm.setImage(null);
        imagePath = "";
        selectedIndex = -1;
    }

    /**
     * Shows or hides the Add, Update and Delete buttons so the UI reflects
     * whether an item is currently selected (tracked by {@code selectedIndex}).
     */
    private void refreshButtons() {
        buttonAdd.setVisible(selectedIndex == -1);
        buttonUpdate.setVisible(selectedIndex != -1);
        buttonDelete.setVisible(selectedIndex != -1);
    }
}
