/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.util.Queue;
import java.util.Scanner;

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

    public boolean add(E child, E parent) {
        Node<E> nchild = new Node<>(child, Tipo.P);
        if (isEmpty() && parent == null) {
            root = nchild;
            return true;
        }
        Node<E> np = searchNode(parent);
        Node<E> nce = searchNode(child);
        if (nce == null && np != null) {
            if (np.left == null) {
                np.left = nchild;
                return true;
            } else if (np.right == null) {
                np.right = nchild;
                return true;
            }
        }
        return false;
    }

    public boolean remove(E data) {
        Node<E> p = searchNode(data);
        if (p == null) {
            return false;
        }
        if (p.left != null && p.left.data.equals(data)) {
            p.left = null;
        } else {
            p.right = null;
        }
        return true;
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

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node<E> n) {
        if (n == null) {
            return 0;
        } else if (n.left == null && n.right == null) {
            return 1;
        } else {
            return countLeaves(n.left) + countLeaves(n.right);
        }
    }

    private Node<E> searchNode(E data) {
        return searchNode(data, root);
    }

    private Node<E> searchNode(E data, Node<E> p) {
        if (p == null) {
            return p;
        } else if (p.data.equals(data)) {
            return p;
        } else {
            Node<E> rl = searchNode(data, p.left);
            if (rl != null) {
                return rl;
            }
            return searchNode(data, p.right);
        }
    }

    private Node<E> searchParent(E data) {
        return searchParent(data, root);
    }

    private Node<E> searchParent(E data, Node<E> n) {
        if (n == null) {
            return n;
        } else if ((n.left != null && n.left.data.equals(data)) || (n.right != null && n.right.data.equals(data))) {
            return n;
        } else {
            Node<E> rl = searchParent(data, n.left);
            if (rl != null) {
                return rl;
            }
            return searchParent(data, n.right);
        }
    }
    
    public void reiniciarNodoActual() {
        nodoActual = root;
    }
    
    public void decisionSi() {
        if (nodoActual.tipo == Tipo.P) {
            nodoActual = nodoActual.left;
        } else {
            System.out.println("OK");
        }
    }
    
    public void decisionNo() {
        if (nodoActual.tipo == Tipo.P) {
            nodoActual = nodoActual.right;
        } else {
            añadirFaltante(nodoActual);
        }
    }

    private void añadirFaltante(Node<E> n) {
        /*
        System.out.print("Ayudame, a mejorar mi predicción!\nQué animal estabas pensando?");
        String animal = sc.nextLine();
        System.out.println("Escribe una pregunta que me permita diferenciar entre un " + animal + " y un " + n.data);
        String pregunta = sc.nextLine();
        System.out.println("Para un " + animal + ", la respuesta a la pregunta: \"" + pregunta + "\", es si o no?");
        String respuesta = sc.nextLine();
        System.out.println("Gracias");

        if (respuesta.equals("SI")) {
            n.left = new Node<>((E) animal, Tipo.R);
            n.right = new Node<>(n.data, Tipo.R);
        } else if (respuesta.equals("NO")) {
            n.left = new Node<>(n.data, Tipo.R);
            n.right = new Node<>((E) animal, Tipo.R);
        }
        n.data = (E) pregunta;
        */
    }

    public void crearArbolAnimal() {
        this.root = crearArbolAnimal(DataManager.leerDatos("datos.txt"), this.root);
        nodoActual = root;
    }

    private Node<E> crearArbolAnimal(Queue<String> cola, Node<E> n) {
        String l = cola.poll();
        char tipo = l.charAt(1);
        String frase = l.substring(3);
        if(tipo == 'P') {
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
}
