/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 5, 2017
**/

package aisearch;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * @author SDX
 */
public class GrafoGrafico extends JPanel {
    private final int EscalaX;
    private final int EscalaY;
    private final int ancho;
    private final int alto;
    private final ArrayList<Nodo> path;
    private final Grafo grafo;
    private final Set<Nodo> nodosEvaluados;
    private final BufferedImage image;

    
    public GrafoGrafico(int ancho, int alto, BufferedImage image, ArrayList path, Set<Nodo> nodosEvaluados, Grafo grafo) {
        this.EscalaX=400/ancho;
        this.EscalaY=400/alto;
        this.ancho = ancho;
        this.alto = alto;
        this.image = image;
        this.path = path;
        this.nodosEvaluados = nodosEvaluados;
        this.grafo = grafo;
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
        boolean red = false;
        ImageColor imageColor = new ImageColor();
        for (int y = 0; y < alto; y++) {
           for (int x = 0; x < ancho; x++) {
             graphics.setColor(Color.white);
             int type =  imageColor.pixelRGB(this.image.getRGB(x, y));
             
             if (type == 0) {
                graphics.setColor(Color.black);
             }
             else if (type == 1) {
                graphics.setColor(Color.white);
             }
             else if (type == 2 && red == false) {
                red = true;
                graphics.setColor(Color.red);
             }
             else if (type == 3) {
                graphics.setColor(Color.green);
             }
             
             fillRect(graphics,x,y);
           }
       }
    }
    
    
      @Override
    public void paint(Graphics graphics) {

        graphics.setColor(Color.DARK_GRAY);
        paintGrafo(graphics);
        //paintObstacles(graphics);
        paintPath(graphics);
        //paintEvaluatedNodes(graphics);
    }
    
    private void paintPath(Graphics graphics) {
       
        graphics.setColor(Color.ORANGE);
        for (Nodo n1: nodosEvaluados) {
            if (!this.grafo.getDestino().contains(n1) && n1 != this.grafo.getInicio()) {
                fillRect(graphics,n1.getX(),n1.getY());
            }
       }
       
        graphics.setColor(Color.BLUE);
        for (Nodo n : path) {
            if (!this.grafo.getDestino().contains(n) && n != this.grafo.getInicio()) {
                int x = n.getX(); int y = n.getY();
                fillRect(graphics, x, y);   
            }
       }
       
       
    }
   
}
