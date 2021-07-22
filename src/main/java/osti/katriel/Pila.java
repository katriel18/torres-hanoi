package osti.katriel;

/******************************************************************************
 *  Esta versión usa una clase anidada Nodo que es estática  (para ahorrar
 *  8 bytes por Nodo), mientras que la otra versión usa una clase anidada
 * (por simplicidad).
. ******************************************************************************/
import java.util.*;

public class Pila<Tipo> implements Iterable<Tipo>, Cloneable {
    private int N;
    private Nodo<Tipo> inicio;

    private static class Nodo<Item> {
        private Item item;
        private Nodo<Item> prox;
    }

    public Pila() {
        inicio = null;
        N = 0;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public int tamanno() {
        return N;
    }

    public void push(Tipo item) {
        Nodo<Tipo> inicioAnterior = inicio;
        inicio = new Nodo<Tipo>();
        inicio.item = item;
        inicio.prox = inicioAnterior;
        N++;
    }

    public Tipo pop() {
        if (estaVacia())
            throw new NoSuchElementException("Pila vacía");
        Tipo item = inicio.item;
        inicio = inicio.prox;
        N--;
        return item;
    }

    public Tipo cima() {
        if (estaVacia())
            throw new NoSuchElementException("Pila Vacía");
        return inicio.item;
    }

    public String toString() {
        /*StringBuilder s = new StringBuilder();
        if(this.estaVacia())
            return "0";
        for (Tipo item : this)
            s.append(item);
        return s.toString();*/
        StringBuilder s = new StringBuilder();
        for (Tipo item : this)
            s.append(item + " ");
        return s.toString();
    }

    public Iterator<Tipo> iterator() {
        return new IteradorDeLista<Tipo>(inicio);
    }

    private class IteradorDeLista<Item> implements Iterator<Item> {
        private Nodo<Item> actual;

        public IteradorDeLista(Nodo<Item> primero) {
            actual = primero;
        }

        public boolean hasNext() {
            return actual != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = actual.item;
            actual = actual.prox;
            return item;
        }
    }

    public Object clonar() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean evaluarEquals(Pila<?> pila) {
        if (this.tamanno() != pila.tamanno())
            return false;
        Iterator<?> thisIterator = this.iterator();
        Iterator<?> pilaIterator = pila.iterator();
        while (thisIterator.hasNext() && pilaIterator.hasNext()) {
            if (!thisIterator.next().equals(pilaIterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pila<?> pila = (Pila<?>) o;
        return this.evaluarEquals(pila);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inicio);
    }
}