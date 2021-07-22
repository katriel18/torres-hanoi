package osti.katriel.Limitada;

import osti.katriel.EstadoHanoiTresDiscos;
import osti.katriel.Pila;

public class PruebaDFSLimitada {
    public static void main(String[] args) {

        // Torres de Hanoi
		Pila<Integer> torre1 = new Pila<Integer>();
		Pila<Integer> torre2 = new Pila<Integer>();
		Pila<Integer> torre3 = new Pila<Integer>();
        //Discos
        torre1.push(3);
		torre1.push(2);
		torre1.push(1);
        //Estado Hanoi
		EstadoHanoiTresDiscos inicio = new EstadoHanoiTresDiscos(torre1,torre2,torre3);
        // Nodo de busqueda
        NodoDeBusquedaLimitada nodo = new NodoDeBusquedaLimitada(inicio);
		//Busqueda DFS Limitada
		BusquedaDFSLimitada.buscar(nodo, true, 7);//nivel 7//nodos 100
    }
  
}
