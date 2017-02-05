/**
* Universidad Del Valle de Guatemala
* Pablo Diaz 13203
* Feb 5, 2017
**/

package aisearch;

import java.util.ArrayList;

/**
 *
 * @author SDX
 */

public class Nodo implements Comparable<Nodo> {
    private boolean Obstaculo;
    private Nodo raiz;
    private Grafo grafo;
    private int x;
    private int y;
    private int distribucion;
    private double funcionHeuristica;//costo del camino nodo actual al final -> Greedy
    private double distanciaDesdeInicio; //funcion G:costo del mejor camino encontrado->Dijsktra
 
    public Nodo(int x, int y, Grafo grafo) {
        this.x = x;
        this.y = y;
        this.Obstaculo = false;
        this.distanciaDesdeInicio=Integer.MAX_VALUE; //al principio los nodos tienen costo infinito
        this.grafo = grafo;
    }
    //método para comparar igualdad entre nodos
    public boolean equals(Nodo nodo) {
        return (this.x == nodo.x) && (this.y == nodo.y);
    }
    //método para calcular los nodos adyacentes de un nodo
    
    public void asignarDiagonal(int diagonal){
        distribucion = diagonal;
    }
    
    public ArrayList<Nodo> getNodosAdyacente(boolean diagonales) 
    {
    //si es con diagonales se crea un número aleatorio con probabilidad 1/2
        
        
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
        //verificar los nodos en diagonal
        if (diagonales)
        {
            if (distribucion ==1)
            {
                //diagonales hacia derecha y arriba
                 if ((y != 0) && !(x == (grafo.getAncho() - 1))) 
                {
                    nodosAdyacentes.add(grafo.getNodo(x + 1, y - 1));

                }
                //diagonales hacia  izquierda y abajo
                if ((x != 0) && (y != (grafo.getAlto() - 1)))
                {
                    nodosAdyacentes.add(grafo.getNodo(x - 1, y + 1));

                } 
            }
            if (distribucion ==2)
            {
                //diagonales hacia abajo y derecha
                if ((x != (grafo.getAncho()- 1)) && !(y == (grafo.getAlto() - 1))) 
                {
                    nodosAdyacentes.add(grafo.getNodo(x + 1, y + 1));
                }
                //diagonales hacia izquierda y arriba
                if ((x != 0) && (y != 0)) 
                {
                    nodosAdyacentes.add(grafo.getNodo(x - 1, y - 1));

                }
            }
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
    public boolean isObstaculo() {
        return Obstaculo;
    }

    public void setIsObstaculo(boolean Obstaculo) {
        this.Obstaculo = Obstaculo;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getFuncionHeuristica() {
        return funcionHeuristica;
    }

    public void setFuncionHeuristica(double funcionHeursitica) {
        this.funcionHeuristica = funcionHeursitica;
    }

    public double getFuncionG() {
        return distanciaDesdeInicio;
    }

    public void setFuncionG(double funcionG) {
        this.distanciaDesdeInicio = funcionG;
    }

    @Override//método que sirve para ordenar la cola prioritaria según esta comparación
    public int compareTo(Nodo other) {
        double totalDistanceFromGoal = this.distanciaDesdeInicio + this.funcionHeuristica;
        double otherDistanceFromGoal = other.distanciaDesdeInicio + other.funcionHeuristica;
        if (totalDistanceFromGoal < otherDistanceFromGoal)
                return -1;
        if (otherDistanceFromGoal < totalDistanceFromGoal)
                return 1;
        return 0;
    }
    @Override
    public String toString()
    {
        return "("+this.x +" ," + this.y +")";
    }

    public void setDistribucion(int distribuicion) {
        this.distribucion = distribuicion;
    }
}
