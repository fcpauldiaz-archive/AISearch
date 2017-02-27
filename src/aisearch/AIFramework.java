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
public interface AIFramework {
    
    public boolean goalTest(Nodo test, ArrayList<Nodo> goalTrue); //check
    public double stepCost(Nodo nodo, Nodo nodo2); //check
    public double pathCost(Nodo nodo);
    public ArrayList actions(Nodo nodo);
    public Nodo result(Nodo a);
    

}
