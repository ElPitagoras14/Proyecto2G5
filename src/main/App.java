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

    public static Stage primaria;

    @Override
    public void start(Stage primaryStage) {
        VentanaPrincipal vi = new VentanaPrincipal();
        Scene scene = new Scene(vi.getRoot(), 800, 500);
        scene.getStylesheets().addAll(this.getClass().getResource("/src/Estilos.css").toExternalForm());
        primaryStage.setTitle("Genio Politecnico");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        setPrimaria(primaryStage);
    }

    public static Stage getPrimaria() {
        return primaria;
    }

    public static void setPrimaria(Stage primaria) {
        App.primaria = primaria;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
