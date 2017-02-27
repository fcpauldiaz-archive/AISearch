/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 5, 2017
**/

package aisearch;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author SDX
 */

public class AStar implements AIFramework {

    private final Grafo grafo;
    private final Nodo inicio;
    private final ArrayList<Nodo> destinos;
    private final ArrayList<Nodo> path = new ArrayList<>();
    private  Set<Nodo> nodosEvaluados;
    
    public AStar(int ancho, int alto, BufferedImage image) {
        this.grafo = new Grafo(ancho, alto);
        this.grafo.setColorsLogic(image);
        this.inicio = grafo.getInicio();
        this.destinos = grafo.getDestino();  
    }

    public void calcular(boolean diagonales,boolean dijkstra,boolean greedy, boolean h1) 
    {
        nodosEvaluados= new HashSet<>(); //set de nodos evaluados
        PriorityQueue<Nodo> nodosPorEvaluar = new PriorityQueue<>();//set de nodos por evaluar, contiene inicialmente al nodo inicial

        this.inicio.setFuncionG(0); //costo desde el inicio hasta el mejor camino conocido
        
        nodosPorEvaluar.add(inicio); //se evalúa el primer nodo
        
        int contadorIteraciones=0; //contador de iteraciones de algoritmo
        while (!nodosPorEvaluar.isEmpty()) {
            
            Nodo actual = nodosPorEvaluar.poll();//obtener el nodo con menor funcion f

            if (goalTest(actual, destinos)) {//en caso de encontrar el destino, termina el algoritmo  
                System.out.println("Iteraciones totales-> " + contadorIteraciones);
                System.out.println("Costo Total-> " + (pathCost(actual)));
                reconstruirCamino(actual);//se muestra el nuevo camino
                break;
            }
            //cambio de estructura para evaluar el nodo actual
           // nodosPorEvaluar.remove(actual);
            nodosEvaluados.add(actual);

            //función obtiene los nodos adyacentes del nodo actual
            for (Nodo adyacente : actions(actual)) {
            
                //en caso de que un nodo ya haya sido evaluado
                //se omite del ciclo
                if (nodosEvaluados.contains(adyacente))
                    continue; //se salta una iteracion
                
                //mientras no sea un obstaculo el nodo
                if (!adyacente.isObstaculo()) {
                    //costo real del nodo adyacente
                    double nuevoCosto = stepCost(actual, adyacente);
                    //se evalua si el nodoa adyacente tiene un menor costo
                    
                    if (!nodosPorEvaluar.contains(adyacente)|| nuevoCosto < adyacente.getFuncionG()){
                        adyacente.setRaiz(actual); //añadir el camino nuevo
                        adyacente.setFuncionG(nuevoCosto); 
                        adyacente.setFuncionHeuristica(calcularHeuristica(adyacente, destinos, diagonales, h1));
                        if (dijkstra)//si es dijsktra la heuristica es cero
                            adyacente.setFuncionHeuristica(0);
                        if (greedy)
                            adyacente.setFuncionG(0);
                        //la cola prioritaria ordena los nodos cada vez que se inserta un nodo
                        //el nodo con menor funcion f es el primero
                        nodosPorEvaluar.add(adyacente);//se añade hasta de último el nodos adyacente
                    }
                   
                }
            }//cierra for adyacente
            contadorIteraciones++;
        }//cierra while
    }//cierra calcular

    //método para mostrar el camino más corto encontrado
    public void reconstruirCamino(Nodo nodo)
    {
        //grafo.getGrafoGrafico(); //mostrar grafo en terminal
        while (!(nodo.getRaiz() == null)) {
            path.add(nodo);
            nodo = nodo.getRaiz();
        }
        path.add(nodo);//agregar el último nodo
        Collections.reverse(path); //cambiar el orden
        System.out.println("");
        System.out.println(path.toString() + " ->Camino más corto");
        
    }
   
    //distancia REAL entre nodos, costo dijkstra
    public double getDistanciaEntre(Nodo n1, Nodo n2) {
        if ((n1.getX() == n2.getX() ) || (n1.getY() == n2.getY()))
            return 1; //si estan a a la par el costo es constante
        else
            return Math.sqrt(2); //en otro caso estan en diagonal, costo = raiz de 2
    }
    
    
    //referencia: http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html#diagonal-distance
    public double calcularHeuristica(Nodo current, ArrayList<Nodo> goals, boolean diagonales, boolean h1) {
       
        double D = 1.0; //peso de aristas adyacentes
        double D2 = Math.sqrt(2); //peso de arista diagonales
        double tempLength = this.grafo.getAlto()*this.grafo.getAncho();
        Nodo goal = null;
        for (int i = 0; i < goals.size(); i++) {
            double dxTemp = Math.abs(current.getX() - goals.get(i).getX());
            double dyTemp = Math.abs(current.getY() - goals.get(i).getY());
            double calcLength = Math.sqrt(Math.pow(dxTemp, 2) + Math.pow(dyTemp, 2));
            if (calcLength < tempLength) {
                tempLength = calcLength;
                goal = goals.get(i);
            }
            
        }
        //manhattan de 4
        double dx = Math.abs(current.getX() - goal.getX());
        double dy = Math.abs(current.getY() - goal.getY());
        //cross breaking ties
        double dx1 = current.getX()-goal.getX();
        double dy1 = current.getY()-goal.getY();
        double p = 1/1000;//minimum cost of taking one step/expected maximum path length
        double dw = inicio.getX()-goal.getX(); //cross heuristics
        double dz = inicio.getY()-goal.getY(); //cross heuristics
    
        //se realiza un promedio de distancia manhattan de 8 movimientos y 4 movimientos
        double promedio = ((D*(dx+dy)+(D2-2*D)*Math.min(dx,dy))+(D*(dx+dy)))/2;
        if (diagonales)
            return promedio;//normal heuristics for diagonals
        if (h1) //sum of absolute differences. Manhattan distance
            return D*(dx + dy);
        return (D*(dx+dy))+Math.abs(dx1*dz-dw*dy1); //normal heuristic + cross breaking ties
       
    }

    //método para mostrar graficamente el camino encontrado
    public ArrayList<Nodo> getPath() {
        return path;
    }

    //Test if objective is found
    @Override
    public boolean goalTest(Nodo test, ArrayList<Nodo> goal) {
        return goal.contains(test);
    }

    @Override
    public Nodo result(Nodo a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //acumulated cost and actual cost
    @Override
    public double stepCost(Nodo actual, Nodo adyacente) {
        return actual.getFuncionG() + getDistanciaEntre(actual, adyacente);
    }

    @Override
    public ArrayList<Nodo> actions(Nodo nodo) {
        //si es con diagonales se crea un número aleatorio con probabilidad 1/2
        int x = nodo.getX(); int y = nodo.getY();
        
        ArrayList<Nodo> nodosAdyacentes = new ArrayList<>(); //lista para meter los nodos adyacentes
        //verificar los nodos con x constantes y y hacia abajo
        if ((y != 0)) 
        {
            nodosAdyacentes.add(grafo.getNodo(x, (y - 1)));
        }
        
       //verificar los nodos con x hacia la derecha y y constante
        if ((x != (grafo.getAncho() - 1))) {
            nodosAdyacentes.add(grafo.getNodo(x + 1, y));

        }
        //verificar los nodos con x constante y y hacia arriba
        if ((y != (grafo.getAlto() - 1)))
        {
            nodosAdyacentes.add(grafo.getNodo(x, y + 1));

        }
        
       //verificar los nodos x hacia abajo y y constante
        if ((x != 0)) 
        {
            nodosAdyacentes.add(grafo.getNodo(x - 1, y));

        }
        
        return nodosAdyacentes;
    }
    
    //Get the total cost of the path
    @Override
    public double pathCost(Nodo nodo) {
        return nodo.getFuncionG() + nodo.getFuncionHeuristica();
    }
    
        //método devuelve los nodos evaluados, para mostrar graficamente
    public Set<Nodo> getNodosEvaluados() {
        return nodosEvaluados;
    }

    public Grafo getGrafo() {
        return grafo;
    }
}