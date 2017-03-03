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
        
    }
    
    public void run(Nodo ) {
        Stack<Nodo> frontera = new Stack();
        frontera.add(inicio);
        int contadorIteraciones = 0;
        
        while (!frontera.isEmpty()) {
            
            Nodo evaluate = frontera.pop();//get first node
            System.out.println(evaluate);
            
            if (goalTest(evaluate)) {//en caso de encontrar el destino, termina el algoritmo  
                System.out.println("Iteraciones totales-> " + contadorIteraciones);
                //System.out.println(path);
                reconstruirCamino(evaluate);
                System.out.println(evaluate);
                //this.completePath = path;
               // System.out.println("Costo Total-> " + (pathCost(last)));
                //reconstruirCamino(actual);//se muestra el nuevo camino
                break;
            }
           
            ArrayList<Accion> f = actions(evaluate);
            for (Accion accion: f){
                Nodo nodo = result(evaluate, accion);
                if (!nodo.isObstaculo()) {
                    if (nodosEvaluados.contains(nodo)) {
                        //nodo.setRaiz(evaluate);
                        //System.out.println(evaluate);
                        continue;
                    }
                    this.nodosEvaluados.add(nodo);

                    //nodo.setRaiz(evaluate);
                    frontera.add(nodo);
                    break;
                }
            }
                
            
            contadorIteraciones++;
        }
        
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
