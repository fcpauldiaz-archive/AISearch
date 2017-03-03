/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Mar 3, 2017
**/

package aisearch;

/**
 *
 * @author SDX
 */
public class Accion {
    
    private Nodo origen;
    private Nodo destino;

    public Accion(Nodo origen, Nodo destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public Nodo getOrigen() {
        return origen;
    }

    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    public Nodo getDestino() {
        return destino;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Accion{" + "origen=" + origen + ", destino=" + destino + '}';
    }
    
    

}
