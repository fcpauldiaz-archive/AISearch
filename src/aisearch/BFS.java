/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 26, 2017
**/

package aisearch;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author SDX
 */
public class BFS implements AIFramework {
    
    private final Grafo grafo;
    private final Nodo inicio;
    private final ArrayList<Nodo> destinos;
    //private final ArrayList<Nodo> path = new ArrayList<>();
    private  Set<Nodo> nodosEvaluados;
    private ArrayList<Nodo> completePath;
    
    public BFS(int ancho, int alto, BufferedImage image) {
        this.grafo = new Grafo(ancho, alto);
        this.grafo.setColorsLogic(image);
        this.inicio = grafo.getInicio();
        this.destinos = grafo.getDestino();  
        this.nodosEvaluados = new HashSet();
        
    }
    
    public void run() {
        Queue<ArrayList<Nodo>> frontera = new LinkedList();
        ArrayList inicial = new ArrayList();
        inicial.add(inicio);
        frontera.add(inicial);
        int contadorIteraciones = 0;
        while (!frontera.isEmpty()) {
            ArrayList<Nodo> path = frontera.poll(); //get first node
            
            Nodo last = path.get(path.size()-1);
           
            if (nodosEvaluados.contains(last)) {
                continue;
            }
            this.nodosEvaluados.add(last);
            
            
            if (goalTest(last)) {//en caso de encontrar el destino, termina el algoritmo  
                System.out.println("Iteraciones totales-> " + contadorIteraciones);
                System.out.println(path);
                System.out.println(destinos);
                System.out.println(last);
                this.completePath = path;
               // System.out.println("Costo Total-> " + (pathCost(last)));
                //reconstruirCamino(actual);//se muestra el nuevo camino
                break;
            }
          
            
            for (Accion accion : actions(last)) {
                Nodo adyacente = accion.getDestino();
                //en caso de que un nodo ya haya sido evaluado
                //se omite del ciclo
                
                //mientras no sea un obstaculo el nodo
                if (!adyacente.isObstaculo()) {
                    ArrayList<Nodo> newPath = new ArrayList();
                    //System.out.println(path);
                    newPath.addAll(path);
                    Nodo newNode = result(last, accion);
                    newPath.add(newNode);
                    frontera.add(newPath);
                    
                   
                }
             }
            contadorIteraciones++;
        }
        
    }

    //Test if objective is found
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
