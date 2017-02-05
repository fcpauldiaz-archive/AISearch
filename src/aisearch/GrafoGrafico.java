/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 5, 2017
**/

package aisearch;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * @author SDX
 */
public class GrafoGrafico extends JPanel{
    private final Grafo grafo;
    private final int EscalaX;
    private final int EscalaY;
    private final int ancho;
    private final int alto;
    private final ArrayList<Nodo> path;
    private final Set<Nodo> nodosEvaluados;

    public GrafoGrafico(Grafo grafo, int ancho, int alto, ArrayList path, Set<Nodo> nodosEvaluados) {
        this.grafo = grafo;
        this.ancho = ancho;
        this.alto = alto;
        this.path = path;
        this.EscalaX=400/ancho;
        this.EscalaY=400/alto;
        this.nodosEvaluados=nodosEvaluados;
        setSize(EscalaX * ancho, EscalaY * alto);
        setVisible(true);
        
    }
    
    
    private void fillRect(Graphics graphics, int x, int y) {
          graphics.fill3DRect(EscalaX*x, EscalaY*y, EscalaX, EscalaY, true);
     }
     public void paintObstacles(Graphics graphics) {
          graphics.setColor(Color.BLACK);
          
          for (int x = 0; x < alto; ++x) {
               for (int y = 0; y < ancho; ++y) {
                    if (grafo.getNodo(x, y).isObstaculo()) {
                         fillRect(graphics, x, y);
                    }
               }
          }
     }
    public void paintGrafo(Graphics graphics){
        graphics.setColor(Color.gray);
        for (int y = 0; y < alto; y++) 
       {
           for (int x = 0; x < ancho; x++)
           {
             fillRect(graphics,x,y);

           }
       }
    }
      @Override
    public void paint(Graphics graphics) {

         graphics.setColor(Color.DARK_GRAY);
         paintGrafo(graphics);
         paintObstacles(graphics);
         paintPath(graphics);
         //paintEvaluatedNodes(graphics);
    }
      
    private void paintPath(Graphics graphics) {
       
       graphics.setColor(Color.red);
       for (Nodo n1: nodosEvaluados)
           fillRect(graphics,n1.getX(),n1.getY());
       
        graphics.setColor(Color.GREEN);
        for (Nodo n : path) {
           
           int x = n.getX(); int y = n.getY();
           fillRect(graphics, x, y);
           
           
       }
       
       
    }
   
}
