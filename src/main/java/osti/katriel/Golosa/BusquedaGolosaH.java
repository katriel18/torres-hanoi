
package osti.katriel.Golosa;

import java.util.ArrayList;
import java.util.Stack;

import osti.katriel.Estado;
import osti.katriel.EstadoHanoiTresDiscos;
import osti.katriel.ListaOrdenadaSE;

public class BusquedaGolosaH {
    
    public static void buscar(NodoDeBusquedaH raiz, boolean d)
    {
        ListaOrdenadaSE<NodoDeBusquedaH> cola = new ListaOrdenadaSE<NodoDeBusquedaH>();
        cola.insertar(raiz);
        realizarBusqueda(cola, d);
    }

    /*
     * Método de ayuda para revisar si un NodoDeBusqueda ya fue evaluado.
     * Returns true si ya fue evaluado, false en caso contrario.
     */
    private static boolean revisarRepetidos(NodoDeBusquedaH n)
    {
        boolean resultado = false;
        NodoDeBusquedaH nodoRevisado = n;

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
    public static void realizarBusqueda(ListaOrdenadaSE<NodoDeBusquedaH> cola, boolean d)
    {
        int contadorBusqueda = 1; // contador para el número de iteraciones

        while (!cola.estaVacia()) // mientras la cola no este vacía
        {
            System.out.print("Sale de la cola: ");
            NodoDeBusquedaH nodoTemp = (NodoDeBusquedaH) cola.eliminarAlInicio();
            System.out.print("(heurística = " + 
                    nodoTemp.getHeuristica()+") ");
            nodoTemp.getEstadoActual().mostrarEstado();
            // si el nodoTemp no es el estado meta
            if (!nodoTemp.getEstadoActual().esMeta())
            {
                
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
                    // calculo heurística nuevo nodo 
                    double h = 
                            ((EstadoHanoiTresDiscos)sucesoresTemp.get(i)).
                                    calcularHeuristica();
                  
                    NodoDeBusquedaH nuevoNodo = new NodoDeBusquedaH(nodoTemp,
                        sucesoresTemp.get(i),h);

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
                Stack<NodoDeBusquedaH> caminoSolucion = new Stack<NodoDeBusquedaH>();
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
