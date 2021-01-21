/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import system.BT;

/**
 *
 * @author GeovannyRojas
 */
public class VentanaAddNodo {

    private Pane root;
    private TextField campoUser;
    private Text tituloCampos;
    private Text textoGuia;
    private HBox opcionRespuesta;
    private String animal;
    private String pregunta;
    private String animalAnterior;
    private BT<String> arbol;

    public VentanaAddNodo() {
        crearItems();
    }

    private void crearItems() {
        crearSeccionGuia();
        crearConfirmarRespuesta();
    }
    
    private void crearSeccionGuia() {
        campoUser = new TextField();
        campoUser.setMaxWidth(150);
        tituloCampos = new Text("Ayudame a mejorar mi predicción!");
        textoGuia = new Text("¿Qué animal estabas pensando?");
        campoUser.setOnKeyReleased((ev) -> {
            if (ev.getCode().equals(KeyCode.ENTER)) {
                confirmarAnimal();
            }
        });
        
        VBox v1 = new VBox();
        v1.setAlignment(Pos.CENTER);
        v1.setPadding(new Insets(10));
        v1.setSpacing(15);
        v1.getChildren().addAll(tituloCampos, textoGuia, campoUser);
        root = v1;
    }
    
    
    private void crearConfirmarRespuesta() {
        Button si = new Button("Si");
        Button no = new Button("No");
        si.setOnMouseClicked((ev) -> {
            botonSi();
        });
        no.setOnMouseClicked((ev) -> {
            botonNo();
        });
        opcionRespuesta = new HBox();
        opcionRespuesta.setSpacing(20);
        opcionRespuesta.setAlignment(Pos.CENTER);
        opcionRespuesta.getChildren().addAll(si, no);
    }

    private void confirmarAnimal() {
        if (!(campoUser.getText().trim().equals(""))) {
            animal = campoUser.getText();
            campoUser.setText("");
            textoGuia.setText("Escribe una pregunta que me permita diferenciar\nentre un " + animal + " y " + animalAnterior);
            campoUser.setOnKeyPressed((ev) -> {
                if(ev.getCode().equals(KeyCode.ENTER)) {
                    actualizarCampos();
                }
            });
        }
    }

    public Parent getRoot() {
        return root;
    }

    public void actualizarCampos() {
        pregunta = campoUser.getText();
        root.getChildren().add(opcionRespuesta);
        root.getChildren().remove(campoUser);
        textoGuia.setText("Para el animal " + animal + ", la respuesta a la pregunta:\n " + pregunta + ", es si o no?");
        textoGuia.setTextOrigin(VPos.CENTER);
        
    }

    private void botonSi() {
        textoGuia.setText("Gracias, he aprendido algo nuevo!");
        arbol.añadirFaltante(animal, pregunta, "SI");
        root.getChildren().remove(opcionRespuesta);
        arbol.guardarArbol();
    }

    private void botonNo() {
        textoGuia.setText("Gracias, he aprendido algo nuevo!");
        arbol.añadirFaltante(animal, pregunta, "NO");
        root.getChildren().remove(opcionRespuesta);
        arbol.guardarArbol();
    }

    public void setAnimalAnterior(String animalAnterior) {
        this.animalAnterior = animalAnterior;
    }

    public void setBT(BT<String> arbol) {
        this.arbol = arbol;
    }

}
