/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import system.BT;

/**
 *
 * @author GeovannyRojas
 */
public class VentanaAñadirNodo {
   private Pane root;
   private TextField campoUser;
   private Text textoPresentar1;
   private Text textoPresentar2;
   private Button enviar;
   private Button enviar2;
   private Button si;
   private Button no;
   private VBox v1;
   private HBox hb;
   private String animal;
   private String pregunta;
   private String animalAnterior;
   private Stage escenario;
   private BT<String> arbol;
   public VentanaAñadirNodo(){
       crearItems();
       
   }

    private void crearItems() {
        root = new Pane();
        campoUser = new TextField();
        textoPresentar1 = new Text("Ayudame a mejorar mi predicción!");
        textoPresentar2 = new Text("¿Qué animal estabas pensando?"); 
        enviar = new Button("Enviar");
        enviar.setOnMouseClicked(new EventHandler(){
            @Override
            public void handle(Event event) {
                if(!(campoUser.getText().equals("") || campoUser.getText().equals(" "))){
                     animal = campoUser.getText();
                     enviar2 = new Button("Enviar");
                     cambiarVbox(enviar2);
                     textoPresentar2.setText("Escribe una pregunta que me\npermita diferenciar\nentre un "+animal+ " y "+animalAnterior);
                     v1.getChildren().remove(enviar);
                     v1.getChildren().add(enviar2);
                }
                    
            }
        });
        v1 = new VBox();
        v1.setAlignment(Pos.CENTER);
        v1.setPadding(new Insets(10));
        v1.setSpacing(15);
        v1.setLayoutX(100);
        v1.setLayoutY(100);
        v1.setScaleX(1.2);
        v1.setScaleY(1.2);
        v1.getChildren().addAll(textoPresentar1,textoPresentar2,campoUser,enviar);
        root.getChildren().add(v1);
        
    }
    
    public Parent getRoot(){
        return root;
    }
   
    public void setAnimalAnterior(String data){
        this.animalAnterior = data;
    }

    public void setStage(Stage escenario) {
        this.escenario = escenario;
    }
        
    public void cambiarVbox(Button btn){
        btn.setOnMouseClicked(new EventHandler(){
            @Override
            public void handle(Event event) {
                pregunta = campoUser.getText();
                si = new Button("Si");
                no = new Button("No");
                hb = new HBox();
                hb.setSpacing(20);
                hb.setAlignment(Pos.CENTER);
                hb.getChildren().addAll(si,no);
                v1.getChildren().remove(enviar2);
                v1.getChildren().add(hb);
                v1.getChildren().remove(campoUser);
                textoPresentar2.setText("Para el animal "+animal+", la respuesta a la pregunta:\n "+pregunta+", es si o no?");
                textoPresentar2.setTextOrigin(VPos.CENTER);
                si.setOnMouseClicked(new EventHandler(){
                    @Override
                    public void handle(Event event) {
                        
                            textoPresentar2.setText("Gracias, he aprendido algo nuevo!");
                            arbol.añadirFaltante(animal, pregunta, "SI");
                            v1.getChildren().remove(hb);
                            arbol.guardarArbol();

                    }
                });
                
                no.setOnMouseClicked(new EventHandler(){
                    @Override
                    public void handle(Event event) {
                            textoPresentar2.setText("Gracias, he aprendido algo nuevo!");
                            arbol.añadirFaltante(animal,pregunta,"NO");
                            v1.getChildren().remove(hb);
                            arbol.guardarArbol();
                    }
                });

            }
        });
    }
    
    public void setBT(BT<String> arbol){
        this.arbol = arbol;
    }
   
}
