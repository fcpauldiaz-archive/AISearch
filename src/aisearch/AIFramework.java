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
    
    public boolean goalTest(Nodo test);                  //check
    public double stepCost(Nodo s1, Accion a,  Nodo s2); //check
    public double pathCost(ArrayList<Nodo> path);        //check
    public ArrayList<Accion> actions(Nodo nodo);         //check
    public Nodo result(Nodo s, Accion a);                //check
    

}
