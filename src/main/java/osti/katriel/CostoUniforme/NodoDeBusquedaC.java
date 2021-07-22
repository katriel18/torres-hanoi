package osti.katriel.CostoUniforme;

import osti.katriel.Estado;

public class NodoDeBusquedaC implements Comparable<NodoDeBusquedaC> {

    private Estado estadoActual;
    private NodoDeBusquedaC padre;
    private double costo; //costo acumulado del nodo

    /**
     * Constructor para la raíz NodoDeBusqueda
     * 
     * @param e
     *            el estado pasado
     */
    public NodoDeBusquedaC(Estado e)
    {
            estadoActual = e;
            padre = null;
            costo = 0.0;
    }

    /**
     * Constructor para todos los otros NodoDeBusqueda
     * 
     * @param prev
     *            el nodo padre
     * @param e
     *            el estado
     */
    public NodoDeBusquedaC(NodoDeBusquedaC prev, Estado e, double c)
    {
            padre = prev;
            estadoActual = e;
            costo = c;
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
    public NodoDeBusquedaC getPadre()
    {
            return padre;
    }

    public double getCosto(){
        return costo;
    }
    
    @Override
    public int compareTo(NodoDeBusquedaC otro){
        if(this.getCosto() > otro.getCosto())       return 1;
        else if(this.getCosto() < otro.getCosto())  return -1;
        else                                        return 0;
    }
}
