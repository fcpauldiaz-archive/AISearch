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
            Nodo last = path.remove(path.size()-1);
            this.nodosEvaluados.add(last);
            if (goalTest(last, destinos)) {//en caso de encontrar el destino, termina el algoritmo  
                System.out.println("Iteraciones totales-> " + contadorIteraciones);
                System.out.println(path);
                System.out.println(last);
               // System.out.println("Costo Total-> " + (pathCost(last)));
                //reconstruirCamino(actual);//se muestra el nuevo camino
                break;
            }
            
            
            for (Nodo adyacente : actions(last)) {
            
                //en caso de que un nodo ya haya sido evaluado
                //se omite del ciclo
                if (nodosEvaluados.contains(adyacente))
                    continue; //se salta una iteracion
                
                //mientras no sea un obstaculo el nodo
                if (!adyacente.isObstaculo()) {
                    ArrayList<Nodo> newPath = new ArrayList();
                    newPath.addAll(path);
                    Nodo newNode = result(last, adyacente);
                    if (!newPath.contains(newNode)) {
                        newPath.add(newNode);
                    }
                    if (!frontera.contains(newPath))
                        frontera.add(newPath);
                   
                }
             }
            contadorIteraciones++;
        }
        
    }

    @Override
    public boolean goalTest(Nodo test, ArrayList<Nodo> goal) {
        return goal.contains(test);
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
    public ArrayList<Nodo> actions(Nodo nodo) {
        return this.grafo.getNeighbors(nodo);
    }

    @Override
    public Nodo result(Nodo a, Nodo s) {
        return s;
    }
    
    

}
