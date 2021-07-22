package osti.katriel.Estrella;

import osti.katriel.Estado;

public class NodoDeBusquedaF implements Comparable<NodoDeBusquedaF> {

    private Estado estadoActual;
    private NodoDeBusquedaF padre;
    private double costo; //costo acumulado del nodo
    private double heuristica;
    private double f;

    /**
     * Constructor para la raíz NodoDeBusqueda
     * 
     * @param e
     *            el estado pasado
     */
    public NodoDeBusquedaF(Estado e, double h)
    {
            estadoActual = e;
            padre = null;
            costo = 0.0;
            heuristica = h;
            f = costo + heuristica;
    }

    /**
     * Constructor para todos los otros NodoDeBusqueda
     * 
     * @param prev
     *            el nodo padre
     * @param e
     *            el estado
     */
    public NodoDeBusquedaF(NodoDeBusquedaF prev, Estado e, double c, double h)
    {
            padre = prev;
            estadoActual = e;
            costo = c;
            heuristica = h;
            f = costo + heuristica;
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
    public NodoDeBusquedaF getPadre()
    {
            return padre;
    }

    public double getF(){
        return f;
    }

    public double getCosto() {
        return costo;
    }

    public double getHeuristica() {
        return heuristica;
    }
    
    public int compareTo(NodoDeBusquedaF otro){
        if(this.getF() > otro.getF())       return  1;
        else if(this.getF() < otro.getF())  return -1;
        else                                return  0;
    }    
}
