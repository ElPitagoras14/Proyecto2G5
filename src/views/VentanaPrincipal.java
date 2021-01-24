/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.App;
import system.DataManager;

/**
 *
 * @author User
 */
public class VentanaPrincipal {

    private Pane root;
    private Font f = cargarFuente(100);

    public VentanaPrincipal() {
        crearItems();
    }

    private void crearItems() {
        root = new Pane();
        HBox cajita = new HBox();
        ImageView fondo = new ImageView(new Image("/src/stageInicio.jpg"));
        fondo.setFitWidth(800);
        fondo.setFitHeight(500);
        ImageView genio = new ImageView(new Image("/src/genioPregunta.png"));
        genio.setFitWidth(278);
        genio.setFitHeight(400);
        genio.setLayoutX(40);
        genio.setLayoutY(60);
        ImageView lampara = new ImageView(new Image("/src/lampara.png"));
        lampara.setLayoutX(180);
        lampara.setLayoutY(420);
        lampara.setFitWidth(137);
        lampara.setFitHeight(77);

        Label titulo = new Label("GENIO");
        Label titulo1 = new Label("POLITECNICO");
        titulo.setTextFill(Color.YELLOW);
        titulo.setFont(f);
        titulo.setLayoutX(500);
        titulo.setLayoutY(150);
        titulo1.setFont(f);
        titulo1.setTextFill(Color.YELLOW);
        titulo1.setLayoutX(400);
        titulo1.setLayoutY(225);
        ImageView startBoton = new ImageView(new Image("/src/startButton.png"));
        startBoton.setFitWidth(100);
        startBoton.setFitHeight(100);

        startBoton.setOnMouseClicked((Event event) -> {
            Scene scene = new Scene(new VentanaPreguntas().getRoot(), 800, 600);
            Stage escenario = new Stage();
            escenario.setTitle("GENIO POLITÉCNICO");
            escenario.setScene(scene);
            escenario.setResizable(false);
            escenario.show();
            App.getPrimaria().close();
        });

        ImageView exitBoton = new ImageView(new Image("/src/exitButton2.png"));
        exitBoton.setFitWidth(110);
        exitBoton.setFitHeight(100);

        exitBoton.setOnMouseClicked((Event event) ->  App.primaria.close());

        cajita.getChildren().addAll(startBoton, exitBoton);
        cajita.setLayoutX(500);
        cajita.setLayoutY(300);
        root.getChildren().addAll(fondo, genio, lampara, titulo, titulo1, cajita);
    }

    public Pane getRoot() {
        return root;
    }

    public static Font cargarFuente(int num) {
        Font f = null;
        try {
            f = Font.loadFont(new FileInputStream(new File("Minangkabau.TTF")), num);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);

        }
        return f;
    }
}
