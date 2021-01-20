/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import system.BT;

/**
 *
 * @author El Pitagoras
 */
public class VentanaPreguntas {
    private Pane root;
    private VBox vbox;
    private BT<String> arbolAnimal;
    private Button si;
    private Button no;
    private Button reiniciar;
    private boolean avanzar;
    private TextField texto;
    private Text pregunta;
    public static Text txtAux;
    
    public VentanaPreguntas() {
        crearItems();
        arbolAnimal = new BT<>();
        arbolAnimal.crearArbolAnimal();
        actualizarInfo();
    }
    
    private void crearItems() {
        root = new Pane();
        txtAux = new Text("Piensa en un animal que yo trataré de adivinarlo");
        txtAux.setLayoutX(200);
        txtAux.setLayoutY(30);
        txtAux.setWrappingWidth(200);
        txtAux.setFill(Color.WHITE);
        txtAux.setScaleX(2.2);
        txtAux.setScaleY(2.2);
        ImageView stage = new ImageView(new Image("/src/stage.jpg"));
        stage.setFitHeight(600);
        stage.setFitWidth(800);
        
        ImageView genio = new ImageView( new Image("/src/genioPregunta.png"));
        genio.setFitWidth(278);
        genio.setFitHeight(400);    
        genio.setLayoutX(40);
        genio.setLayoutY(70);
        
        
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
        
        pregunta = new Text();
        pregunta.setTextAlignment(TextAlignment.CENTER);
        pregunta.setFill(Color.WHITE);
        pregunta.setScaleX(2);
        pregunta.setScaleY(2);
        texto = new TextField();
        texto.setMaxWidth(150);
        texto.setDisable(true);
        texto.setVisible(false);
        
        v1.getChildren().add(pregunta);
        v1.getChildren().add(texto);
        v1.getChildren().add(h1);
        v1.setLayoutX(370);
        v1.setLayoutY(220);
        root.getChildren().addAll(stage,genio,v1,txtAux);
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