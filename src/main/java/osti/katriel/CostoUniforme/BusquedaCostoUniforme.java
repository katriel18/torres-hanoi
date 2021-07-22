package osti.katriel.CostoUniforme;
import java.util.ArrayList;
import java.util.Stack;

import osti.katriel.Estado;
import osti.katriel.EstadoHanoiTresDiscos;
import osti.katriel.ListaOrdenadaSE;

public class BusquedaCostoUniforme {
    public static void buscar(NodoDeBusquedaC raiz, boolean d)
    {
        ListaOrdenadaSE<NodoDeBusquedaC> cola = 
                new ListaOrdenadaSE<NodoDeBusquedaC>();
        cola.insertar(raiz);
        realizarBusqueda(cola, d);
    }

    /*
     * Método de ayuda para revisar si un NodoDeBusqueda ya fue evaluado.
     * Returns true si ya fue evaluado, false en caso contrario.
     */
    private static boolean revisarRepetidos(NodoDeBusquedaC n)
    {
        boolean resultado = false;
        NodoDeBusquedaC nodoRevisado = n;

        // Mientras el padre de n no sea nulo, revisar si es igual al nodo
        // que estamos buscando.
        while (n.getPadre() != null && !resultado)
        {
            if (n.getPadre().getEstadoActual().igual(nodoRevisado.getEstadoActual()))
            {
                resultado = true;
            }
            n = n.getPadre();
        }

        return resultado;
    }

    /**
     * Realiza la Busqueda BFS usando la cola como el espacio a buscar
     */
    public static void realizarBusqueda(ListaOrdenadaSE<NodoDeBusquedaC> cola, boolean d)
    {
        int contadorBusqueda = 1; // contador para el número de iteraciones

        while (!cola.estaVacia()) // mientras la cola no este vacía
        {
            System.out.print("Sale de la cola: ");
            NodoDeBusquedaC nodoTemp = (NodoDeBusquedaC) cola.eliminarAlInicio();
            System.out.print("(costo = " + nodoTemp.getCosto()+")");
            nodoTemp.getEstadoActual().mostrarEstado();
            // si el nodoTemp no es el estado meta
            if (!nodoTemp.getEstadoActual().esMeta())
            {
                //costo acumulado del padre
                double costoAcumPadre = nodoTemp.getCosto();
                
                // generar los sucesores inmediatos a nodoTemp
                ArrayList<Estado> sucesoresTemp = nodoTemp.getEstadoActual()
                                .generarSucesores();

                /*
                 * Iterar a través de los sucesores, envolverlo en un 
                 * NodoDeBusqueda, revisar si ya fue evaluado, y si no
                 * agregarlo a la cola.
                 */
                for (int i = 0; i < sucesoresTemp.size(); i++)
                {
                    // segundo parametro que suma el costo del nuevo nodo al 
                    // costo total actual en el NodoDeBusqueda
                    double costoCambio = 0;
                    costoCambio = ((EstadoHanoiTresDiscos)nodoTemp.getEstadoActual()).
                        costoCambio((EstadoHanoiTresDiscos)sucesoresTemp.get(i));
                    
                    double costoAcumSucesor = costoAcumPadre + costoCambio;
                    
                    NodoDeBusquedaC nuevoNodo = new NodoDeBusquedaC(nodoTemp,
                        sucesoresTemp.get(i),costoAcumSucesor);

                    if (!revisarRepetidos(nuevoNodo))
                    {
                            cola.insertar(nuevoNodo);
                    }
                }
                contadorBusqueda++;
            }
            else
            // El estado meta se encontro. Imprimir el camino que lleva a este
            {
                // Use una pila para seguir el camino desde el estado inicial
                // al estado meta
                Stack<NodoDeBusquedaC> caminoSolucion = new Stack<NodoDeBusquedaC>();
                caminoSolucion.push(nodoTemp);
                nodoTemp = nodoTemp.getPadre();

                while (nodoTemp.getPadre() != null)
                {
                    caminoSolucion.push(nodoTemp);
                    nodoTemp = nodoTemp.getPadre();
                }
                caminoSolucion.push(nodoTemp);

                // El tamaño de la pila antes de iterarla y vaciarla.
                int iteraciones = caminoSolucion.size();

                for (int i = 0; i < iteraciones; i++)
                {
                    nodoTemp = caminoSolucion.pop();
                    nodoTemp.getEstadoActual().mostrarEstado();
                    System.out.println();
                    System.out.println();
                }
                //System.out.println("El costo fue: " + nodoTemp.getCosto());
                if (d)
                {
                    System.out.println("El número de nodos examinados: "
                                    + contadorBusqueda);
                }

                System.exit(0);
            }
        }

        // No debería ocurrir nunca.
        System.out.println("Error! Solución no encontrada!");
    } 
}


