/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author El Pitagoras
 */
public class DataManager {
    
    private DataManager(){
        // Sin código
    }
    
    public static Queue<String> leerDatos(String path) {
        Queue<String> cola = new LinkedList<>();
        try(BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String l = br.readLine();
            while (l != null) {
                cola.offer(l);
                l = br.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cola;
    }
}
