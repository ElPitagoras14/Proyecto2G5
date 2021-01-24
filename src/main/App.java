/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.VentanaPrincipal;

/**
 *
 * @author El Pitagoras
 */
public class App extends Application {

    private static Scene scene;
    public static Stage primaria;

    @Override
    public void start(Stage primaryStage) {
        VentanaPrincipal vi = new VentanaPrincipal();
        scene = new Scene(vi.getRoot(), 800, 500);
        scene.getStylesheets().addAll(this.getClass().getResource("/src/Estilos.css").toExternalForm());
        primaryStage.setTitle("Genio Politecnico");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaria = primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
