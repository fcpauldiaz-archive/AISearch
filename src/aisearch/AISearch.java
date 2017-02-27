/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aisearch;

import javax.swing.JFrame;

/**
 *
 * @author SDX
 */
public class AISearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int size = 25;
        ImageParser parse = new ImageParser(size);
        AStar astar = new AStar(size, size, parse.getResized());
        BFS bfs = new BFS(size, size, parse.getResized());
        bfs.run();
        astar.calcular(false,false,false, false);
        JFrame window = new JFrame();
 
        window.setSize(450, 450);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.add(
                new GrafoGrafico(
                    size,size, 
                    parse.getResized(), astar.getPath(), 
                    astar.getNodosEvaluados(), astar.getGrafo()
               )
        );
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JFrame window2 = new JFrame();
        window2.setSize(450, 450);
        window2.setLocationRelativeTo(null);
        window2.setVisible(true);
        window2.add(
                new GrafoGrafico(
                    size,size, 
                    parse.getResized(), bfs.getCompletePath(), 
                    bfs.getNodosEvaluados(), bfs.getGrafo()
               )
        );
        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
    }
    
    
    
}
