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
    private Scanner sc;
    
    public BT() {
        sc = new Scanner(System.in);
    }
    
    private class Node<E> {
        
        private E data;
        private Node<E> left;
        private Node<E> right;
        
        public Node(E data) {
            this.data = data;
        }
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public boolean add(E child, E parent) {
        Node<E> nchild = new Node<>(child);
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
    
    public Node<E> searchParent(E data) {
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
    
    public void decision(String lado) {
        Node<E> resultado = decision(root);
        System.out.println(resultado.data);
        String acierto = sc.nextLine();
        if (acierto.equals("SI")) {
            System.out.println("Está bien");
        } else if (acierto.equals("NO")) {
            añadirFaltante(resultado);
        }
    }
    
    private Node<E> decision(Node<E> n) {
        String lado = "";
        if (n.left != null && n.right != null) {
            System.out.println(n.data);
            lado = sc.nextLine();
        } 
        if (lado.equals("SI")) {
            return decision(n.right);
        } else if (lado.equals("NO")) {
            return decision(n.left);
        } else {
            return n;
        }
    }
    
    private void añadirFaltante(Node<E> n) {
        System.out.print("Ayudame, a mejorar mi predicción!\nQué animal estabas pensando?");
        String animal = sc.nextLine();
        System.out.println("Escribe una pregunta que me permita diferenciar entre un " + animal + " y un " + n.data);
        String pregunta = sc.nextLine();
        System.out.println("Para un " + animal + ", la respuesta a la pregunta: \"" + pregunta + "\", es si o no?");
        String respuesta = sc.nextLine();
        System.out.println("Gracias");
        
        if (respuesta.equals("SI")) {
            n.left = new Node<>((E) animal);
            n.right = new Node<>(n.data);
        } else if (respuesta.equals("NO")) {
            n.left = new Node<>(n.data);
            n.right = new Node<>((E) animal);
        }
        n.data = (E) pregunta;
    }
    
    public BT<E> crearArbolAnimal() {
        crearArbolAnimal(DataManager.leerDatos("datos.txt"), root);
        return this;
    }
    
    private void crearArbolAnimal(Queue<String> cola, Node<E> n) {
        String l = cola.poll();
        char tipo = l.charAt(1);
        String frase = l.substring(3);
        n = new Node<>((E) frase);
        
        if (tipo == 'P') {
            crearArbolAnimal(cola, n.left);
            crearArbolAnimal(cola, n.right);
        }
    }
}