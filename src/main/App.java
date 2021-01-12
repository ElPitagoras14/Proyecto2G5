/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import views.ventanaPrincipal;

/**
 *
 * @author El Pitagoras
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        ventanaPrincipal vi= new ventanaPrincipal();          
      
       
        Scene scene = new Scene(vi.getRoot(), 800, 500);
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
