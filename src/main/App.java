/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.VentanaPreguntas;

/**
 *
 * @author El Pitagoras
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        //VentanaPrincipal vi = new VentanaPrincipal();
        VentanaPreguntas vp = new VentanaPreguntas();
        Scene scene = new Scene(vp.getRoot(), 800, 600);
        scene.getStylesheets().addAll(this.getClass().getResource("/src/Estilos.css").toExternalForm());
        primaryStage.setTitle("Genio Politecnico");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
