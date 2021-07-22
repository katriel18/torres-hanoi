package osti.katriel;
import java.util.ArrayList;

/**
 * 
 * Interfaz Estado desde el cual el estado del problema hereda. Define un método
 * para revisar si el estado actual es una meta, generar sucesores y comparar
 * si es igual con otro estado.
 */
public interface Estado
{
	// determina si el estado actual es una meta
	boolean esMeta();

	// generar sucesores para el estado actual
	ArrayList<Estado> generarSucesores();

	// mostrar el estado actual
	public void mostrarEstado();

	// comparar los datos del estado actual
	public boolean igual(Estado s);
}
