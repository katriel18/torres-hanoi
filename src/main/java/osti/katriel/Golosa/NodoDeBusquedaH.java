
package osti.katriel.Golosa;

import osti.katriel.Estado;

public class NodoDeBusquedaH implements Comparable<NodoDeBusquedaH> {

    private Estado estadoActual;
    private NodoDeBusquedaH padre;
    private double heuristica; //heurística del nodo

    /**
     * Constructor para la raíz NodoDeBusqueda
     * 
     * @param e
     *            el estado pasado
     */
    public NodoDeBusquedaH(Estado e, double h)
    {
            estadoActual = e;
            padre = null;
            heuristica = h;
    }

    /**
     * Constructor para todos los otros NodoDeBusqueda
     * 
     * @param padre
     *            el nodo padre
     * @param e
     *            el estado
     */
    public NodoDeBusquedaH(NodoDeBusquedaH padre, Estado e, double h)
    {
            this.padre = padre;
            estadoActual = e;
            heuristica = h;
    }

    /**
     * @return el estadoActual
     */
    public Estado getEstadoActual()
    {
            return estadoActual;
    }

    /**
     * @return el padre
     */
    public NodoDeBusquedaH getPadre()
    {
            return padre;
    }

    public double getHeuristica(){
        return heuristica;
    }
    
    @Override
    public int compareTo(NodoDeBusquedaH otro){
        if(this.getHeuristica() > otro.getHeuristica())       return 1;
        else if(this.getHeuristica() < otro.getHeuristica())  return -1;
        else                                                  return 0;
    }
}
