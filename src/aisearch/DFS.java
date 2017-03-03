/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 26, 2017
**/

package aisearch;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author SDX
 */
public class DFS implements AIFramework {
    
    private final Grafo grafo;
    private final Nodo inicio;
    private final ArrayList<Nodo> destinos;
    //private final ArrayList<Nodo> path = new ArrayList<>();
    private  Set<Nodo> nodosEvaluados;
    private ArrayList<Nodo> completePath;
    
    public DFS(int ancho, int alto, BufferedImage image) {
        this.grafo = new Grafo(ancho, alto);
        this.grafo.setColorsLogic(image);
        this.inicio = grafo.getInicio();
        this.destinos = grafo.getDestino();  
        this.nodosEvaluados = new HashSet();
        this.completePath = new ArrayList();
        this.completePath =  run(this.inicio, new ArrayList(), new ArrayList());
        
    }
    
    public ArrayList<Nodo> run(Nodo actual, ArrayList<Nodo> path,  ArrayList<Nodo> shortest ) {
        path.add(actual);
      
        if (goalTest(actual)) {//en caso de encontrar el destino, termina el algoritmo  
           //System.out.println("Iteraciones totales-> " + contadorIteraciones);
           //System.out.println("Costo Total-> " + (pathCost(actual)));
           //reconstruirCamino(actual);//se muestra el nuevo camino
           //break;
           return path;
        }
        for (Accion accion: actions(actual)) {
            Nodo children = result(actual, accion);
            if (!children.isObstaculo()) {
                if (!nodosEvaluados.contains(children)) {
                    nodosEvaluados.add(children);
                    if (shortest.isEmpty() || path.size() < shortest.size()) {
                        ArrayList<Nodo> newPath = run(children, path, shortest);
                        if (!newPath.isEmpty()) {
                            shortest = newPath;
                        }
                    }
                }
            }
           
        }
        return shortest;
    }

    @Override
    public boolean goalTest(Nodo test) {
        return this.destinos.contains(test);
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
    public ArrayList<Accion> actions(Nodo nodo) {
        return this.grafo.getNeighbors(nodo);
    }

    @Override
    public Nodo result(Nodo a, Accion s) {
        if (s.getOrigen().equals(a)) {
           return s.getDestino();
        }
        return null;
    }
    
       //método para mostrar el camino más corto encontrado
    public void reconstruirCamino(Nodo nodo)
    {
        //grafo.getGrafoGrafico(); //mostrar grafo en terminal
        while (!(nodo.getRaiz() == null)) {
            completePath.add(nodo);
            nodo = nodo.getRaiz();
        }
        completePath.add(nodo);//agregar el último nodo
        Collections.reverse(completePath); //cambiar el orden
        System.out.println("");
        System.out.println(completePath.toString() + " ->Camino más corto");
        
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public Set<Nodo> getNodosEvaluados() {
        return nodosEvaluados;
    }

    public ArrayList<Nodo> getCompletePath() {
        return completePath;
    }
    
    
    

}
