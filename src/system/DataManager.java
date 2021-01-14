/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author El Pitagoras
 */
public class DataManager {
    public static Queue<String> leerDatos(String path) {
        Queue<String> cola = new LinkedList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            String l = br.readLine();
            while (l != null) {
                cola.offer(l);
            }
        } catch (IOException ex) {
            
        }
        return cola;
    }
}
