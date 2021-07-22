package osti.katriel;

import java.util.ArrayList;

public class EstadoHanoiTresDiscos implements Estado {

	private Pila<Integer> torre1;
	private Pila<Integer> torre2;
	private Pila<Integer> torre3;
	private final Pila<Integer> META = new Pila<Integer>();
	private int costoCambio = 0;

	public EstadoHanoiTresDiscos() {
	}

	public EstadoHanoiTresDiscos(Pila<Integer> tower1, Pila<Integer> torre2, Pila<Integer> torre3) {
		add(tower1, torre2, torre3);
		setMeta();
	}

	// Crea sucesores con costo de cambio
	public EstadoHanoiTresDiscos(Pila<Integer> tower1, Pila<Integer> torre2, Pila<Integer> torre3, int costoCambio) {
		add(tower1, torre2, torre3);
		setMeta();
		this.costoCambio = costoCambio;
	}

	@Override
	public boolean esMeta() {
		return this.torre3.equals(META);
	}

	@Override
	public ArrayList<Estado> generarSucesores() {
		ArrayList<Estado> sucesores = new ArrayList<>();
		/*
		 * Orden	Operador		  Costo
		 * 1.	Mover de 1 a 2		    1 
		 * 2.	Mover de 1 a 3 			2 
		 * 3.	Mover de 2 a 1 			2 
		 * 4.	Mover de 2 a 3 			1 
		 * 5.	Mover de 3 a 1 			3 
		 * 6.	Mover de 3 a2 			2
		 */
		for (int i = 1; i <= 6; i++) {
			hacerMovimientosLegales(i, sucesores);
		}
		return sucesores;
	}

	private void hacerMovimientosLegales(int n, ArrayList<Estado> sucesores) {
		Pila<Integer> t1 = clone(torre1);
		Pila<Integer> t2 = clone(torre2);
		Pila<Integer> t3 = clone(torre3);

		/*
		 * Orden	Operador		  Costo
		 * 1.	Mover de 1 a 2		    1 
		 * 2.	Mover de 1 a 3 			2 
		 * 3.	Mover de 2 a 1 			2 
		 * 4.	Mover de 2 a 3 			1 
		 * 5.	Mover de 3 a 1 			3 
		 * 6.	Mover de 3 a2 			2
		 */

		switch (n) {
			case 1:
				if (this.torre1.tamanno() > 0) {
					// comprobar que un disco de mayor tamano no
					// este encima de uno de menor tamano
					if (this.torre2.tamanno() > 0) {
						if (esMayorQue(this.torre2.cima(), this.torre1.cima())) {
							t2.push(t1.pop());
							sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 1));//Sucesor con costo de cambio
						}
					} else {
						t2.push(t1.pop());
						sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 1));//Sucesor con costo de cambio
					}
				}
				break;
			case 2:
				if (this.torre1.tamanno() > 0) {
					// comprobar que un disco de mayor tamano no
					// este encima de uno de menor tamano
					if (this.torre3.tamanno() > 0) {
						if (esMayorQue(this.torre3.cima(), this.torre1.cima())) {
							t3.push(t1.pop());
							sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 2));//Sucesor con costo de cambio
						}
					} else {
						t3.push(t1.pop());
						sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 2));//Sucesor con costo de cambio
					}
				}
				break;
			case 3:
				if (this.torre2.tamanno() > 0) {
					// comprobar que un disco de mayor tamano no
					// este encima de uno de menor tamano
					if (this.torre1.tamanno() > 0) {
						if (esMayorQue(this.torre1.cima(), this.torre2.cima())) {
							t1.push(t2.pop());
							sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 2));//Sucesor con costo de cambio
						}
					} else {
						t1.push(t2.pop());
						sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 2));//Sucesor con costo de cambio
					}
				}
				break;
			case 4:
				if (this.torre2.tamanno() > 0) {
					// comprobar que un disco de mayor tamano no
					// este encima de uno de menor tamano
					if (this.torre3.tamanno() > 0) {
						if (esMayorQue(this.torre3.cima(), this.torre2.cima())) {
							t3.push(t2.pop());
							sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 1));//Sucesor con costo de cambio
						}
					} else {
						t3.push(t2.pop());
						sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 1));//Sucesor con costo de cambio
					}

				}
				break;
			case 5:
				if (this.torre3.tamanno() > 0) {
					// comprobar que un disco de mayor tamano no
					// este encima de uno de menor tamano
					if (this.torre1.tamanno() > 0) {
						if (esMayorQue(this.torre1.cima(), this.torre3.cima())) {
							t1.push(t3.pop());
							sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 3));//Sucesor con costo de cambio
						}
					} else {
						t1.push(t3.pop());
						sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 3));//Sucesor con costo de cambio
					}
				}
				break;
			case 6:
				if (this.torre3.tamanno() > 0) {
					// comprobar que un disco de mayor tamano no
					// este encima de uno de menor tamano
					if (this.torre2.tamanno() > 0) {
						if (esMayorQue(this.torre2.cima(), this.torre3.cima())) {
							t2.push(t3.pop());
							sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 2));//Sucesor con costo de cambio
						}
					} else {
						t2.push(t3.pop());
						sucesores.add(new EstadoHanoiTresDiscos(t1, t2, t3, 2));//Sucesor con costo de cambio
					}

				}
				break;
			default:
				break;
		}

	}

	@Override
	public void mostrarEstado() {
		System.out.println("( " + "Torre1 [" + this.torre1.toString() + "], Torre2 [" + this.torre2.toString()
				+ "], Torre3 [" + this.torre3.toString() + "] )");
	}

	@Override
	public boolean igual(Estado s) {
		return ((this.torre1.equals(((EstadoHanoiTresDiscos) s).getTorre1()))
				&& (this.torre2.equals(((EstadoHanoiTresDiscos) s).getTorre2()))
				&& (this.torre3.equals(((EstadoHanoiTresDiscos) s).getTorre3()))) ? true : false;
	}

	public Pila<Integer> getTorre1() {
		return torre1;
	}

	public void setTorre1(Pila<Integer> torre1) {
		this.torre1 = torre1;
	}

	public Pila<Integer> getTorre2() {
		return torre2;
	}

	public void setTorre2(Pila<Integer> torre2) {
		this.torre2 = torre2;
	}

	public Pila<Integer> getTorre3() {
		return torre3;
	}

	public void setTorre3(Pila<Integer> torre3) {
		this.torre3 = torre3;
	}

	private void setMeta() {
		this.META.push(3);
		this.META.push(2);
		this.META.push(1);
	}

	public int getCostoCambio() {
		return costoCambio;
	}

	@SuppressWarnings("unchecked")
	private Pila<Integer> clone(Pila<Integer> c) {
		Pila<Integer> n = new Pila<Integer>();
		n = (Pila<Integer>) c.clonar();
		return n;
	}

	private void add(Pila<Integer> t1, Pila<Integer> t2, Pila<Integer> t3) {
		this.torre1 = t1;
		this.torre2 = t2;
		this.torre3 = t3;
	}

	private boolean esMayorQue(Integer A, Integer B) {
		return (A > B) ? true : false;
	}

	// Costo de Cambio
	public double costoCambio(EstadoHanoiTresDiscos otro) {

		return otro.getCostoCambio();
	}

	// Heuristica
	public double calcularHeuristica() {
		return 3 - this.torre3.tamanno();
	}

}
