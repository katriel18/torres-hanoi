package osti.katriel.Golosa;

import osti.katriel.EstadoHanoiTresDiscos;
import osti.katriel.Pila;

/**
 * PruebaGolosa
 */
public class PruebaGolosa {
    public static void main(String[] args) {
        // Torres de Hanoi
        Pila<Integer> torre1 = new Pila<Integer>();
        Pila<Integer> torre2 = new Pila<Integer>();
        Pila<Integer> torre3 = new Pila<Integer>();
        // Discos
        torre1.push(3);
        torre1.push(2);
        torre1.push(1);
        // Estado Hanoi
        EstadoHanoiTresDiscos inicio = new EstadoHanoiTresDiscos(torre1, torre2, torre3);
        // Nodo de busqueda
        NodoDeBusquedaH nodo = new NodoDeBusquedaH(inicio,inicio.calcularHeuristica());
        // Busqueda Golosa
        BusquedaGolosaH.buscar(nodo, true);
    }

}