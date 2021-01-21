/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.VentanaAddNodo;

/**
 *
 * @author El Pitagoras
 */
public class BT<E> {

    private Node<E> root;
    private Node<E> nodoActual;

    private enum Tipo {
        P, R
    }

    private class Node<E> {

        private E data;
        private Node<E> left;
        private Node<E> right;
        private Tipo tipo;

        public Node(E data, Tipo tipo) {
            this.data = data;
            this.tipo = tipo;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node<E> n) {
        if (n == null) {
            return 0;
        }
        return 1 + size(n.left) + size(n.right);
    }

    public int height() {
        return height(root);
    }

    private int height(Node<E> n) {
        if (n == null) {
            return 0;
        }

        return 1 + Math.max(height(n.left), height(n.right));
    }

    public void reiniciarNodoActual() {
        nodoActual = root;
    }

    public void decisionSi() {
        if (nodoActual.tipo == Tipo.P) {
            nodoActual = nodoActual.left;
        }
    }

    public void decisionNo() {
        if (nodoActual.tipo == Tipo.P) {
            nodoActual = nodoActual.right;
        }
    }

    

    public void añadirFaltante(String animal, String pregunta, String respuesta) {
        if (respuesta.equals("SI")) {
            nodoActual.left = new Node<>((E) animal, Tipo.R);
            nodoActual.right = new Node<>(nodoActual.data, Tipo.R);
        } else if (respuesta.equals("NO")) {
            nodoActual.left = new Node<>(nodoActual.data, Tipo.R);
            nodoActual.right = new Node<>((E) animal, Tipo.R);
        }
        nodoActual.data = (E) pregunta;
        nodoActual.tipo = Tipo.P;
    }

    public void crearArbolAnimal() {
        this.root = crearArbolAnimal(DataManager.leerDatos("datos.txt"), this.root);
        nodoActual = root;
    }

    private Node<E> crearArbolAnimal(Queue<String> cola, Node<E> n) {
        String l = cola.poll();
        char tipo = l.charAt(1);
        String frase = l.substring(3);
        if (tipo == 'P') {
            n = new Node<>((E) frase, Tipo.P);
        } else {
            n = new Node<>((E) frase, Tipo.R);
        }

        if (tipo == 'P') {
            n.left = crearArbolAnimal(cola, n.left);
            n.right = crearArbolAnimal(cola, n.right);
        }
        return n;
    }

    public E getNodoActualData() {
        return nodoActual.data;
    }

    public void guardarArbol() {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter("datos.txt"))) {

            guardarArbol(bw, root);
        } catch (IOException ex) {
            System.out.println("No se pudo encontrar el archivo");
        }
    }

    private void guardarArbol(BufferedWriter bw, Node<E> n) {
        if (n != null) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("#");
                sb.append(n.tipo);
                sb.append(" ");
                sb.append(n.data);
                bw.write(sb.toString());
                bw.newLine();
                guardarArbol(bw, n.left);
                guardarArbol(bw, n.right);
            } catch (IOException ex) {
                Logger.getLogger(BT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
