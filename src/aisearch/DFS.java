/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 26, 2017
**/

package aisearch;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author SDX
 */
public class DFS implements AIFramework{

    private final Grafo grafo;
    private final Nodo inicio;
    private final ArrayList<Nodo> destinos;
    private final ArrayList<Nodo> path = new ArrayList<>();
    private  Set<Nodo> nodosEvaluados;
    
    public DFS(int ancho, int alto, BufferedImage image) {
        this.grafo = new Grafo(ancho, alto);
        this.grafo.setColorsLogic(image);
        this.inicio = grafo.getInicio();
        this.destinos = grafo.getDestino();  
    }

    @Override
    public boolean goalTest(Nodo test, ArrayList<Nodo> goalTrue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double stepCost(Nodo nodo, Nodo nodo2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double pathCost(Nodo nodo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList actions(Nodo nodo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Nodo result(Nodo a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
