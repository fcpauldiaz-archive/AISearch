/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aisearch;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author SDX
 */
public class AISearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("./"));
        chooser.setDialogTitle("Escoger imagen");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        System.out.println(chooser.showOpenDialog(null));
        
        
        // Con caja de texto
        String seleccion = JOptionPane.showInputDialog(
           null,
           "Size of matrix","",
           JOptionPane.QUESTION_MESSAGE);  // el icono sera un iterrogante

            // Con JCombobox
        Object type = JOptionPane.showInputDialog(
           null,
           "Seleccione opcion",
           "Selector de opciones",
           JOptionPane.QUESTION_MESSAGE,
           null,  // null para icono defecto
           new Object[] { "Manhattan", "Cross Breaking Ties" }, 
           "Manhattan");

        
        int size = Integer.parseInt(seleccion);
        ImageParser parse = new ImageParser(size, chooser.getSelectedFile());
        AStar astar = new AStar(size, size, parse.getResized());
        BFS bfs = new BFS(size, size, parse.getResized());
        bfs.run();
        DFS dfs = new DFS(size, size, parse.getResized());
        astar.calcular(false,false,false, type.equals("Manhattan"));
        
        JFrame window = new JFrame();
        window.setLocation(2,150);
        window.setSize(450, 450);
        window.setVisible(true);
        window.add(
                new GrafoGrafico(
                    size,size, 
                    parse.getResized(), astar.getPath(), 
                    astar.getNodosEvaluados(), astar.getGrafo()
               )
        );
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JFrame windowBFS = new JFrame();
        windowBFS.setSize(450, 450);
        windowBFS.setLocationRelativeTo(null);
        windowBFS.setVisible(true);
        windowBFS.add(
                new GrafoGrafico(
                    size,size, 
                    parse.getResized(), bfs.getCompletePath(), 
                    bfs.getNodosEvaluados(), bfs.getGrafo()
               )
        );
        windowBFS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JFrame windowDFS = new JFrame();
        windowDFS.setSize(450, 450);
        windowDFS.setLocation(900, 150);
        windowDFS.setVisible(true);
        windowDFS.add(
                new GrafoGrafico(
                    size,size, 
                    parse.getResized(), dfs.getCompletePath(), 
                    dfs.getNodosEvaluados(), dfs.getGrafo()
               )
        );
        windowDFS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
    }
    
    
    
}
