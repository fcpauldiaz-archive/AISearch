/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 26, 2017
**/

package aisearch;

import java.util.ArrayList;

/**
 *
 * @author SDX
 */
abstract public class AIFramework {
    
    abstract public boolean goalTest(Nodo test);                  //check
    abstract public double stepCost(Nodo s1, Accion a,  Nodo s2); //check
    abstract public double pathCost(ArrayList<Nodo> path);        //check
   // abstract public ArrayList<Accion> actions(Nodo nodo);          //check
    abstract public Nodo result(Nodo s, Accion a);                //check
    
      
    public ArrayList<Accion> actions(Nodo nodo, Grafo grafo) {
          //si es con diagonales se crea un número aleatorio con probabilidad 1/2
        int x = nodo.getX(); int y = nodo.getY();
        
        ArrayList<Accion> nodosAdyacentes = new ArrayList(); //lista para meter los nodos adyacentes
        //verificar los nodos con x constantes y y hacia abajo
        if ((y != 0)) {
            Nodo temp = grafo.getNodo(x, y - 1);
            Accion acction = new Accion(nodo, temp);
            nodosAdyacentes.add(acction);
        }
        
       //verificar los nodos con x hacia la derecha y y constante
        if ((x != (grafo.getAncho() - 1))) {
            Nodo temp = grafo.getNodo(x + 1,y);
            Accion acction = new Accion(nodo, temp);
            nodosAdyacentes.add(acction);

        }
           //verificar los nodos x hacia abajo y y constante
        if ((x != 0)) {
            Nodo temp = grafo.getNodo(x - 1, y);
            Accion acction = new Accion(nodo, temp);
            nodosAdyacentes.add(acction);

        }

        //verificar los nodos con x constante y y hacia arriba
        if ((y != (grafo.getAlto() - 1))) {
             Nodo temp = grafo.getNodo(x, y + 1);
            Accion acction = new Accion(nodo, temp);
            nodosAdyacentes.add(acction);

        }
        
    
        
        return nodosAdyacentes;
    }
    

}
