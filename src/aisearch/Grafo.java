/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 5, 2017
**/

package aisearch;

import java.util.ArrayList;

/**
 *
 * @author SDX
 */

public class Grafo {
    
    private int ancho;
    private int alto;
    private int[] diagonalesNodos = null;
    private ArrayList<ArrayList<Nodo>> grafo; 

    public Grafo(int w, int h) {
        this.ancho = w;
        this.alto = h;
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

    public int[] getDiagonalesNodos() {
        return diagonalesNodos;
    }

    public void setDiagonalesNodos(int[] diagonalesNodos) {
        this.diagonalesNodos = diagonalesNodos;
    }
    
     
    public void crearOstaculo15x15() {
         
        grafo.get(2).get(9).setIsObstaculo(true);
        grafo.get(3).get(9).setIsObstaculo(true);
        grafo.get(4).get(9).setIsObstaculo(true);
        grafo.get(5).get(9).setIsObstaculo(true);
        grafo.get(6).get(9).setIsObstaculo(true);
        grafo.get(7).get(9).setIsObstaculo(true);
        grafo.get(8).get(9).setIsObstaculo(true);
        grafo.get(9).get(9).setIsObstaculo(true);
        grafo.get(10).get(9).setIsObstaculo(true);
        grafo.get(11).get(9).setIsObstaculo(true);
        grafo.get(12).get(9).setIsObstaculo(true);
        grafo.get(13).get(9).setIsObstaculo(true);
        grafo.get(13).get(8).setIsObstaculo(true);
        grafo.get(13).get(7).setIsObstaculo(true);
        grafo.get(13).get(6).setIsObstaculo(true);
        grafo.get(13).get(5).setIsObstaculo(true);
        grafo.get(2).get(8).setIsObstaculo(true);
        grafo.get(2).get(7).setIsObstaculo(true);
        grafo.get(2).get(6).setIsObstaculo(true);
        grafo.get(2).get(5).setIsObstaculo(true);
    }
     
    public void crearOstaculo20x20() {
        grafo.get(3).get(13).setIsObstaculo(true);
        grafo.get(4).get(13).setIsObstaculo(true);
        grafo.get(5).get(13).setIsObstaculo(true);
        grafo.get(6).get(13).setIsObstaculo(true);
        grafo.get(7).get(13).setIsObstaculo(true);
        grafo.get(8).get(13).setIsObstaculo(true);
        grafo.get(9).get(13).setIsObstaculo(true);
        grafo.get(10).get(13).setIsObstaculo(true);
        grafo.get(11).get(13).setIsObstaculo(true);
        grafo.get(12).get(13).setIsObstaculo(true);
        grafo.get(13).get(13).setIsObstaculo(true);
        grafo.get(14).get(13).setIsObstaculo(true);
        grafo.get(14).get(12).setIsObstaculo(true);
        grafo.get(14).get(11).setIsObstaculo(true);
        grafo.get(14).get(10).setIsObstaculo(true);
        grafo.get(14).get(9).setIsObstaculo(true);
        
        grafo.get(3).get(12).setIsObstaculo(true);
        grafo.get(3).get(11).setIsObstaculo(true);
        grafo.get(3).get(10).setIsObstaculo(true);
        grafo.get(3).get(9).setIsObstaculo(true);
        
    }
public void crearOstaculo25x25() {
         grafo.get(5).get(16).setIsObstaculo(true);
        grafo.get(6).get(16).setIsObstaculo(true);
        grafo.get(7).get(16).setIsObstaculo(true);
        grafo.get(8).get(16).setIsObstaculo(true);
        grafo.get(9).get(16).setIsObstaculo(true);
        grafo.get(10).get(16).setIsObstaculo(true);
        grafo.get(11).get(16).setIsObstaculo(true);
        grafo.get(12).get(16).setIsObstaculo(true);
        grafo.get(13).get(16).setIsObstaculo(true);
        grafo.get(14).get(16).setIsObstaculo(true);
        grafo.get(15).get(16).setIsObstaculo(true);
        grafo.get(16).get(16).setIsObstaculo(true);
        grafo.get(16).get(15).setIsObstaculo(true);
        grafo.get(16).get(14).setIsObstaculo(true);
        grafo.get(16).get(13).setIsObstaculo(true);
        grafo.get(16).get(12).setIsObstaculo(true);
        
        grafo.get(5).get(15).setIsObstaculo(true);
        grafo.get(5).get(14).setIsObstaculo(true);
        grafo.get(5).get(13).setIsObstaculo(true);
        grafo.get(5).get(12).setIsObstaculo(true);
        
    }
}

