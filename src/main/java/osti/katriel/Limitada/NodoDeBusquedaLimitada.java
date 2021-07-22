package osti.katriel.Limitada;

import java.util.ArrayList;
import java.util.Stack;

import osti.katriel.Estado;
import osti.katriel.EstadoHanoiTresDiscos;
import osti.katriel.ListaOrdenadaSE;
/**
 * 
 * Clase para representar un NodoDeBusqueda. Esta será una envoltura para un
 * Estado, y rastreara el costo para encontra ese estado y el nodo padre.
 */
public class NodoDeBusquedaLimitada {

        private Estado estadoActual;
        private NodoDeBusquedaLimitada padre;

        private int nivel;      // --------------------------

        /**
         * Constructor para la raíz NodoDeBusqueda
         * 
         * @param e el estado pasado
         */
        public NodoDeBusquedaLimitada(Estado e) {
                estadoActual = e;
                padre = null;
                nivel = 0;      // --------------------------
        }

        /**
         * Constructor para todos los otros NodoDeBusqueda
         * 
         * @param prev el nodo padre
         * @param e    el estado
         */
        public NodoDeBusquedaLimitada(NodoDeBusquedaLimitada prev, Estado e) {
                padre = prev;
                estadoActual = e;
                nivel = padre.getNivel() + 1;   // ---------------------
        }

        /**
         * @return el estadoActual
         */
        public Estado getEstadoActual() {
                return estadoActual;
        }

        /**
         * @return el padre
         */
        public NodoDeBusquedaLimitada getPadre() {
                return padre;
        }

        public int getNivel() {
                return nivel;
        }

}
