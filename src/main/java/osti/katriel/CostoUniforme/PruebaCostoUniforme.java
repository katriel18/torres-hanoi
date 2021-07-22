package osti.katriel.CostoUniforme;
import osti.katriel.EstadoHanoiTresDiscos;
import osti.katriel.Pila;

public class PruebaCostoUniforme {
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
        NodoDeBusquedaC nodo = new NodoDeBusquedaC(inicio);
		//Busqueda Costo Uniforme
		BusquedaCostoUniforme.buscar(nodo, true);
    }
}
