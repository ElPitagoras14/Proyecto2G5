/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import system.BT;

/**
 *
 * @author El Pitagoras
 */
public class VentanaPreguntas {
    private VBox root;
    private BT<String> arbolAnimal;
    private Button si;
    private Button no;
    private Button reiniciar;
    private boolean avanzar;
    private TextField texto;
    private Label pregunta;
    
    public VentanaPreguntas() {
        crearItems();
        arbolAnimal = new BT<>();
        arbolAnimal.crearArbolAnimal();
        actualizarInfo();
    }
    
    private void crearItems() {
        VBox v1 = new VBox();
        v1.setAlignment(Pos.CENTER);
        v1.setPadding(new Insets(10));
        v1.setSpacing(15);
        
        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(10);
        
        si = new Button("SI");
        no = new Button("NO");
        reiniciar = new Button("Reiniciar");
        si.setOnMouseClicked((ev) -> {
            arbolAnimal.decisionSi();
            actualizarInfo();
        });
        no.setOnMouseClicked((ev) -> {
            arbolAnimal.decisionNo();
            actualizarInfo();
        });
        reiniciar.setOnMouseClicked((ev) -> {
            arbolAnimal.reiniciarNodoActual();
            actualizarInfo();
        });
        h1.getChildren().add(si);
        h1.getChildren().add(no);
        h1.getChildren().add(reiniciar);
        
        pregunta = new Label();
        pregunta.setTextAlignment(TextAlignment.CENTER);
        texto = new TextField();
        texto.setMaxWidth(150);
        texto.setDisable(true);
        
        v1.getChildren().add(pregunta);
        v1.getChildren().add(texto);
        v1.getChildren().add(h1);
        root = v1;
    }
    
    private void actualizarInfo() {
        pregunta.setText(arbolAnimal.getNodoActualData());
    }
    
    public Pane getRoot() {
        return root;
    }
    
    private class HiloAñadir implements Runnable {

        @Override
        public void run() {
            while (!avanzar) {
                
            }
            avanzar = false;
            texto.getText();
            texto.setText("");
            while (!avanzar) {
                
            }
            avanzar = false;
            texto.getText();
            texto.setText("");
        }
        
    }
}