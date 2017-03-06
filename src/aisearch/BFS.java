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
public class BFS extends AIFramework {
    
    private final Grafo grafo;
    private final Nodo inicio;
    private final ArrayList<Nodo> destinos;
    private final  Set<Nodo> nodosEvaluados;
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
                System.out.println("Iteraciones totales BFS *-> " + contadorIteraciones);
                System.out.println(path);
                this.completePath = path;
                System.out.println("Costo total BFS *-> " + pathCost(path));
               // System.out.println("Costo Total-> " + (pathCost(last)));
                //reconstruirCamino(actual);//se muestra el nuevo camino
                break;
            }
          
            
            for (Accion accion : actions(last, grafo)) {
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
    public double pathCost(ArrayList<Nodo> path) {
        double costo = 0;
        for(int i = 0; i < path.size() - 1; i++) {
            Accion temp = new Accion(path.get(i), path.get(i+1));
            costo += stepCost(path.get(i), temp, path.get(i+1));
        }
        return costo;
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

    @Override
    public double stepCost(Nodo s1, Accion a, Nodo s2) {
       if (s1.equals(a.getOrigen()) && s2.equals(a.getDestino())) {
           return 1;
       }
       throw new UnsupportedOperationException("Step cost not valid"); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

}
