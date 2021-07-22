package osti.katriel;

/**
 * 
 * Clase para representar un NodoDeBusqueda. Esta será una envoltura para un
 * Estado, y rastreara el costo para encontra ese estado y el nodo padre.
 */
public class NodoDeBusqueda
{

    private Estado estadoActual;
    private NodoDeBusqueda padre;

    /**
     * Constructor para la raíz NodoDeBusqueda
     * 
     * @param e
     *            el estado pasado
     */
    public NodoDeBusqueda(Estado e)
    {
            estadoActual = e;
            padre = null;
    }

    /**
     * Constructor para todos los otros NodoDeBusqueda
     * 
     * @param prev
     *            el nodo padre
     * @param e
     *            el estado
     */
    public NodoDeBusqueda(NodoDeBusqueda prev, Estado e)
    {
            padre = prev;
            estadoActual = e;
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
    public NodoDeBusqueda getPadre()
    {
            return padre;
    }
}
