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
import javafx.scene.input.KeyCode;
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

    private Pane root;
    private BT<String> arbolAnimal;

    private Label animalNuevo;
    private TextField animal;
    private Label animalDiferencia;
    private TextField pregunta;
    private Label animalPregunta;
    private Button siAdd;
    private Button noAdd;

    private Label mensaje;
    private Label titulo;
    private String previo;
    private Button siDecision;
    private Button noDecision;

    public VentanaPreguntas() {
        crearItems();
        previo = "";
        arbolAnimal = new BT<>();
        arbolAnimal.crearArbolAnimal();
        actualizarInfo();
    }

    private void crearItems() {
        HBox contenedor = new HBox();
        contenedor.setAlignment(Pos.CENTER);
        root = contenedor;
        columnaUno();
        columnaDos();
        ocultarSegundaColumna();

    }

    private void columnaUno() {
        VBox columna = new VBox();
        columna.setAlignment(Pos.CENTER);
        columna.setPadding(new Insets(10));
        columna.setSpacing(15);

        mensaje = new Label();
        mensaje.setVisible(false);

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(10);

        siDecision = new Button("SI");
        noDecision = new Button("NO");
        Button reiniciar = new Button("Reiniciar");
        siDecision.setOnMouseClicked((ev) -> {
            System.out.println(previo);
            previo = arbolAnimal.getNodoActualData();
            arbolAnimal.decisionSi();
            actualizarInfo();
            if (previo.equals(arbolAnimal.getNodoActualData())) {
                mensaje.setVisible(true);
                mensaje.setText("Belleza flaco");
                setHabilitarBotones(false);
            }
        });
        noDecision.setOnMouseClicked((ev) -> {
            System.out.println(previo);
            previo = arbolAnimal.getNodoActualData();
            arbolAnimal.decisionNo();
            actualizarInfo();
            if (previo.equals(arbolAnimal.getNodoActualData())) {
                mensaje.setText("Habla bien oye");
                mensaje.setVisible(true);
                animalNuevo.setVisible(true);
                animal.setVisible(true);
                setHabilitarBotones(false);
            }
        });
        reiniciar.setOnMouseClicked((ev) -> {
            reiniciar();
        });
        h1.getChildren().add(siDecision);
        h1.getChildren().add(noDecision);
        h1.getChildren().add(reiniciar);

        titulo = new Label();
        titulo.setTextAlignment(TextAlignment.CENTER);

        columna.getChildren().add(titulo);
        columna.getChildren().add(mensaje);
        columna.getChildren().add(h1);
        root.getChildren().add(columna);
    }

    private void columnaDos() {
        VBox columna = new VBox();
        columna.setAlignment(Pos.CENTER);
        columna.setPadding(new Insets(10));
        columna.setSpacing(15);
        animalNuevo = new Label("Ayudame a mejorar mi prediccion\nQué animal estabas esperando");
        animalDiferencia = new Label();
        animalPregunta = new Label();

        animal = new TextField();
        animal.setMaxWidth(150);
        animal.setOnKeyPressed((ev) -> {
            if (ev.getCode().equals(KeyCode.ENTER)) {
                animalDiferencia.setVisible(true);
                animalDiferencia.setText("Escribe una pregunta que me permita diferenciar entre los animales");
                pregunta.setVisible(true);
                animal.setDisable(true);
            }
        });

        pregunta = new TextField();
        pregunta.setMaxWidth(150);
        pregunta.setOnKeyPressed((ev) -> {
            if (ev.getCode().equals(KeyCode.ENTER)) {
                animalPregunta.setVisible(true);
                animalPregunta.setText("Para un " + animal.getText() + " la respuesta a la pregunta: \"" + pregunta.getText() + "\", es si o no?");
                siAdd.setVisible(true);
                noAdd.setVisible(true);
                pregunta.setDisable(true);
            }
        });

        HBox h1 = new HBox();
        h1.setSpacing(15);
        h1.setAlignment(Pos.CENTER);
        siAdd = new Button("SI");
        siAdd.setOnMouseClicked((ev) -> {
            arbolAnimal.añadirFaltante(animal.getText(), pregunta.getText(), "SI");
            reiniciar();
        });
        noAdd = new Button("NO");
        noAdd.setOnMouseClicked((ev) -> {
            arbolAnimal.añadirFaltante(animal.getText(), pregunta.getText(), "NO");
            reiniciar();
        });
        h1.getChildren().add(siAdd);
        h1.getChildren().add(noAdd);

        columna.getChildren().add(animalNuevo);
        columna.getChildren().add(animal);
        columna.getChildren().add(animalDiferencia);
        columna.getChildren().add(pregunta);
        columna.getChildren().add(animalPregunta);
        columna.getChildren().add(h1);

        root.getChildren().add(columna);
    }

    private void reiniciar() {
        arbolAnimal.reiniciarNodoActual();
        actualizarInfo();
        setHabilitarBotones(true);
        ocultarSegundaColumna();
        limpiarCampos();
    }

    private void ocultarSegundaColumna() {
        animalNuevo.setVisible(false);
        animal.setVisible(false);
        animalDiferencia.setVisible(false);
        pregunta.setVisible(false);
        animalPregunta.setVisible(false);
        siAdd.setVisible(false);
        noAdd.setVisible(false);
    }

    private void setHabilitarBotones(boolean habilitar) {
        siDecision.setDisable(!habilitar);
        noDecision.setDisable(!habilitar);
        animal.setDisable(habilitar);
        pregunta.setDisable(habilitar);
    }

    private void limpiarCampos() {
        animal.setText("");
        pregunta.setText("");
    }

    private void actualizarInfo() {
        titulo.setText(arbolAnimal.getNodoActualData());
    }

    public Pane getRoot() {
        return root;
    }
}
