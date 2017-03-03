/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 5, 2017
**/

package aisearch;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author SDX
 */

public class Grafo  {
    
    private int ancho;
    private int alto;
    private ArrayList<ArrayList<Nodo>> grafo; 
    private Nodo inicio;
    private ArrayList<Nodo> destinos;

    public Grafo(int w, int h) {
        this.ancho = w;
        this.alto = h;
        this.destinos = new ArrayList();
        crearGrafo();
    }

    public Nodo getNodo(int x, int y) {
        return grafo.get(y).get(x);
    }
    
    private void crearGrafo() 
    {
        Nodo nodo;
        grafo = new ArrayList<>();
        for (int y = 0; y < alto; y++) 
        {
            ArrayList temp= new ArrayList();
            grafo.add(temp);
            for (int x = 0; x < ancho; x++)
            {
                nodo = new Nodo(x, y, this);
                grafo.get(y).add(nodo);
            }
        }
        
        
    }

    public void getGrafoGrafico(){
       
        for (int y = 0; y < alto; y++) 
        {
            for (int x = 0; x < ancho; x++)
            {
                System.out.print(grafo.get(y).get(x));
                if (x==ancho-1){
                    System.out.println("");
                }
                
            }
        }
        
    }
    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public ArrayList<ArrayList<Nodo>> getGrafo() {
        return grafo;
    }

    public void setGrafo(ArrayList<ArrayList<Nodo>> grafo) {
        this.grafo = grafo;
    }
    
    public void setColorsLogic(BufferedImage image) {
        ImageColor imageColor = new ImageColor();
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
             
                int type =  imageColor.pixelRGB(image.getRGB(x, y));
                
                if (type == 0) { //black
                    this.grafo.get(y).get(x).setObstaculo();
                }
                else if (type == 2) { //red
                    this.inicio = this.grafo.get(y).get(x);
                }
                else if (type == 3) { //green
                    this.destinos.add(this.grafo.get(y).get(x)); 
                }
             
            }

        }
    }
    
    public ArrayList<Accion> getNeighbors(Nodo nodo) {
          //si es con diagonales se crea un número aleatorio con probabilidad 1/2
        int x = nodo.getX(); int y = nodo.getY();
        
        ArrayList<Accion> nodosAdyacentes = new ArrayList(); //lista para meter los nodos adyacentes
        //verificar los nodos con x constantes y y hacia abajo
        if ((y != 0)) {
            Nodo temp = this.getNodo(x, y - 1);
            Accion acction = new Accion(nodo, temp);
            nodosAdyacentes.add(acction);
        }
        
       //verificar los nodos con x hacia la derecha y y constante
        if ((x != (this.getAncho() - 1))) {
            Nodo temp = this.getNodo(x + 1,y);
            Accion acction = new Accion(nodo, temp);
            nodosAdyacentes.add(acction);

        }
           //verificar los nodos x hacia abajo y y constante
        if ((x != 0)) {
            Nodo temp = this.getNodo(x - 1, y);
            Accion acction = new Accion(nodo, temp);
            nodosAdyacentes.add(acction);

        }

        //verificar los nodos con x constante y y hacia arriba
        if ((y != (this.getAlto() - 1))) {
             Nodo temp = this.getNodo(x, y + 1);
            Accion acction = new Accion(nodo, temp);
            nodosAdyacentes.add(acction);

        }
        
    
        
        return nodosAdyacentes;
    }
    
    public Nodo getInicio() {
        return this.inicio;
    }
    
    public ArrayList<Nodo> getDestino() {
        return this.destinos;
    }
        

     
}

