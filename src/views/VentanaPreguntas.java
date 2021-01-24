/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import system.BT;

/**
 *
 * @author El Pitagoras
 */
public class VentanaPreguntas {

    private Pane root;
    private VBox columnaInterfaz;
    private BT<String> arbolAnimal;
    private Text pregunta;
    private Text feedback;
    private String animalPrevio;

    public VentanaPreguntas() {
        root = new Pane();
        columnaInterfaz = new VBox();
        crearItems();
        arbolAnimal = new BT<>();
        arbolAnimal.crearArbolAnimal();
        actualizarInfo();
        animalPrevio = "";
    }

    private void crearItems() {
        crearImagenes();
        crearSeccionFeedback();
        crearSeccionPregunta();
        crearSeccionAdivinar();
    }

    private void crearSeccionFeedback() {
        feedback = new Text("Piensa en un animal que yo trataré de adivinarlo");
        feedback.setLayoutX(200);
        feedback.setLayoutY(30);
        feedback.setWrappingWidth(200);
        feedback.setFill(Color.WHITE);
        feedback.setScaleX(2.2);
        feedback.setScaleY(2.2);
        root.getChildren().add(feedback);
    }

    private void crearImagenes() {
        ImageView stage = new ImageView(new Image("/src/stage.jpg"));
        stage.setFitHeight(600);
        stage.setFitWidth(800);

        ImageView genio = new ImageView(new Image("/src/genioPregunta.png"));
        genio.setFitWidth(278);
        genio.setFitHeight(400);
        genio.setLayoutX(40);
        genio.setLayoutY(70);
        root.getChildren().addAll(stage, genio);
    }

    private void crearSeccionAdivinar() {
        columnaInterfaz.setAlignment(Pos.CENTER);
        columnaInterfaz.setPadding(new Insets(10));
        columnaInterfaz.setSpacing(15);

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(10);

        Button si = new Button("SI");
        Button no = new Button("NO");
        Button reiniciar = new Button("Reiniciar");
        
        si.setOnMouseClicked(ev -> botonSi());
        
        no.setOnMouseClicked(ev -> botonNo());
        
        reiniciar.setOnMouseClicked(ev -> botonReiniciar());
        
        h1.getChildren().add(si);
        h1.getChildren().add(no);
        h1.getChildren().add(reiniciar);

        columnaInterfaz.getChildren().add(h1);
        columnaInterfaz.setLayoutX(370);
        columnaInterfaz.setLayoutY(220);
        root.getChildren().add(columnaInterfaz);
    }

    private void crearSeccionPregunta() {
        pregunta = new Text();
        pregunta.setTextAlignment(TextAlignment.CENTER);
        pregunta.setFill(Color.WHITE);
        pregunta.setScaleX(2);
        pregunta.setScaleY(2);
        TextField texto = new TextField();
        texto.setMaxWidth(150);
        texto.setDisable(true);
        texto.setVisible(false);

        columnaInterfaz.getChildren().add(pregunta);
        columnaInterfaz.getChildren().add(texto);
    }

    private void botonSi() {
        animalPrevio = arbolAnimal.getNodoActualData();
        arbolAnimal.decisionSi();
        actualizarInfo();
        if (animalPrevio.equals(arbolAnimal.getNodoActualData())) {
            feedback.setText("He adivinado! ---- Reinicia si deseas\n jugar de Nuevo");
        }
    }

    private void botonNo() {
        animalPrevio = arbolAnimal.getNodoActualData();
        arbolAnimal.decisionNo();
        actualizarInfo();
        if (animalPrevio.equals(arbolAnimal.getNodoActualData())) {
            feedback.setText("No he podido adivinar, pero ayudame a mejorar");
            addFaltanteInterfaz();
        }
    }

    private void botonReiniciar() {
        arbolAnimal.reiniciarNodoActual();
        feedback.setText("Piensa en un animal que yo trataré de adivinarlo");
        actualizarInfo();
    }
    
    private void addFaltanteInterfaz() {
        VentanaAddNodo ventana = new VentanaAddNodo();
        ventana.setAnimalAnterior(arbolAnimal.getNodoActualData());
        Scene scene = new Scene(ventana.getRoot(), 400, 400);
        Stage escenario = new Stage();
        escenario.setScene(scene);
        escenario.setResizable(false);
        escenario.setTitle("boo!");
        escenario.show();
        ventana.setBT(arbolAnimal);
        escenario.setOnCloseRequest(ev -> botonReiniciar());
    }

    private void actualizarInfo() {
        pregunta.setText(arbolAnimal.getNodoActualData().toUpperCase());
    }

    public Pane getRoot() {
        return root;
    }
}
