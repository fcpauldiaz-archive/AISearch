/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 26, 2017
**/

package aisearch;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author SDX
 */
public class DFS extends AIFramework {
    
    private final Grafo grafo;
    private final Nodo inicio;
    private final ArrayList<Nodo> destinos;
    private int contadorIteraciones = 0;
    private int cost = 0;
    private final Set<Nodo> nodosEvaluados;
    private ArrayList<Nodo> completePath;
    
    public DFS(int ancho, int alto, BufferedImage image) {
        this.grafo = new Grafo(ancho, alto);
        this.grafo.setColorsLogic(image);
        this.inicio = grafo.getInicio();
        this.destinos = grafo.getDestino();  
        this.nodosEvaluados = new HashSet();
        this.completePath = new ArrayList();
        this.completePath =  run(this.inicio, new ArrayList(), new ArrayList());
        System.out.println("Iteraciones totales DFS *-> " + this.contadorIteraciones);
        System.out.println("Costo total DFS *-> " + pathCost(this.completePath));
        System.out.println(this.completePath);
        
    }
    
    private ArrayList<Nodo> run(Nodo actual, ArrayList<Nodo> path,  ArrayList<Nodo> shortest ) {
        path.add(actual);
      
        if (goalTest(actual)) {//en caso de encontrar el destino, regresa en la recursividad  
           return path;
        }
        for (Accion accion: actions(actual, grafo)) {
            Nodo children = result(actual, accion);
            if (!children.isObstaculo()) {
                if (!nodosEvaluados.contains(children)) {
                    nodosEvaluados.add(children);
                    this.contadorIteraciones++;
                    //verificar camino más corto
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
    public double pathCost(ArrayList<Nodo> path) {
        double costo = 0;
        for(int i = 0; i < path.size() - 1; i++) {
            Accion temp = new Accion(path.get(i), path.get(i+1));
            costo += stepCost(path.get(i), temp, path.get(i+1));
        }
        return costo;
    }

    
    @Override
    public double stepCost(Nodo s1, Accion a, Nodo s2) {
        if (s1.equals(a.getOrigen()) && s2.equals(a.getDestino())) {
           return 1;
       }
       throw new UnsupportedOperationException("Step cost not valid"); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Nodo result(Nodo s, Accion a) {
        if (a.getOrigen().equals(s)) {
           return a.getDestino();
        }
        return null;
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
